package uk.ac.port.SUMS.ProjectIdeas.presentation;
import java.text.*;
import javax.ejb.*;
import javax.inject.*;
import javax.enterprise.context.*;
import javax.faces.application.*;
import javax.faces.context.*;
import uk.ac.port.SUMS.kernel.infrastructure.*;
import uk.ac.port.SUMS.kernel.presentation.*;
import uk.ac.port.SUMS.kernel.persistence.exceptions.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.ProjectIdeas.application.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;

@Named(value="I")
@RequestScoped
public class ProjectIdeaController extends AuthenticatedController{
 private String Title=null;
 private ProjectIdea Model=null;
 @EJB
 private ViewProjectIdea Application;
 private final StringSanitizer StringSanitizerService=new StringSanitizer();
 public ProjectIdeaController(){}
 
 public String getTitle(){
  return Title;
 }
 public void setTitle(String Title){
  this.Title=StringSanitizerService.ProcessLine(Title);
 }
 public boolean getSuccess(){
  return this.Model!=null;
 }
 public ProjectIdea getModel(){
  return this.Model;
 }
 
 public boolean getCanNavigateToAmendIdea(){
  return Model.canEdit(getCurrentUser());
 }
 public boolean getCanNavigateToStatusChanges(){
  return Model.canViewStatusChanges(getCurrentUser());
 }
 
 public void LoadModel(){
  if(this.Title==null||this.Title.isEmpty()){
   super.Redirect("ProjectIdeas.xhtml");
   return;
  }
  ProjectIdea Model;
  try{
   Model=Application.Execute(Title,getCurrentUser());
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
    MessageFormat.format("The currently logged in user ({0}) is not authorized to view this Project Idea",getCurrentUser().getID())
   ));
   return;
  }
  this.Model=Model;
 }
}