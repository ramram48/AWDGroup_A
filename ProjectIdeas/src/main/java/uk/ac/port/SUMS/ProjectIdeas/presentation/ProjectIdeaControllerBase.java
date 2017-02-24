package uk.ac.port.SUMS.ProjectIdeas.presentation;
import uk.ac.port.SUMS.kernel.model.exceptions.NoEntityFoundException;
import java.text.*;
import javax.faces.application.*;
import javax.faces.context.*;
import uk.ac.port.SUMS.kernel.infrastructure.*;
import uk.ac.port.SUMS.kernel.presentation.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;

abstract public class ProjectIdeaControllerBase extends AuthenticatedController{
 private String Title=null;
 protected ProjectIdea Model=null;
 private boolean loadsuccess=false;
 protected final StringSanitizer StringSanitizerService=new StringSanitizer();
 protected ProjectIdeaControllerBase(){}
 
 public String getTitle(){
  return Title;
 }
 public void setTitle(String Title){
  this.Title=StringSanitizerService.ProcessLine(Title);
 }
 public boolean isTitleSpecified(){
  return !(this.Title==null||this.Title.isEmpty());
 }
 public boolean getLoadSuccess(){
  return loadsuccess;
 }
 public ProjectIdea getModel(){
  return this.Model;
 }
 
 public void LoadModel(){
  if(!isTitleSpecified()){
   return;
  }
  ProjectIdea Model;
  try{
   Model=ReadModel();
  }catch(NoEntityFoundException Error){
   super.setHTTPStatusCode(404);
   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    "Project Idea not found",
    MessageFormat.format("No Project Idea with the Title \"{0}\" exists",Title)
   ));
   return;
  }catch(NotAuthorizedException Error){
   super.setHTTPStatusCode(403);
   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    "Access Denied",
    MessageFormat.format("The currently logged in user ({0}) is not authorized to view or edit this Project Idea",getCurrentUser().getID())
   ));
   return;
  }
  this.Model=Model;
  this.loadsuccess=true;
 }
 
 abstract protected ProjectIdea ReadModel()throws NoEntityFoundException,NotAuthorizedException;
}