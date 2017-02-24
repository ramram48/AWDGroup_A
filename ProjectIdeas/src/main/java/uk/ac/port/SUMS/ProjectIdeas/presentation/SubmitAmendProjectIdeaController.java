package uk.ac.port.SUMS.ProjectIdeas.presentation;
import java.text.*;
import uk.ac.port.SUMS.kernel.model.exceptions.NoEntityFoundException;
import java.util.*;
import java.util.logging.*;
import javax.ejb.*;
import javax.inject.*;
import javax.enterprise.context.*;
import javax.faces.application.*;
import javax.faces.context.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.ProjectIdeas.application.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;

@Named(value="SAI") //Submit-Amend-Idea
@RequestScoped
public class SubmitAmendProjectIdeaController extends ProjectIdeaControllerBase{
 @EJB
 private SubmitProjectIdea ApplicationSubmit;
 @EJB
 private AmendProjectIdea ApplicationAmend;
 private SubmitAmendProjectIdea Application=null;
 private Set<ProjectCategory> AvailableCategories=null;
 public SubmitAmendProjectIdeaController(){}
 
 public boolean isAmending(){
  return super.isTitleSpecified();
 }
 public boolean getLoadFailure(){
  return (isAmending()&&!super.getLoadSuccess())||AvailableCategories==null;
 }
 
 public boolean getUserCanChangeStatus(){
  return Model.canChangeStatus(getCurrentUser());
 }
 
 public List<ProjectIdea.Statuses> getProjectIdeaStatuses(){
  return Arrays.asList(ProjectIdea.Statuses.values());
 }
 public Set<ProjectCategory> getAvailableCategories(){
  return AvailableCategories;
 }
 
 public @Override void LoadModel(){
  if(this.Application!=null){throw new IllegalStateException();}
  if(!isTitleSpecified()){
   super.Model=new ProjectIdea();
   this.Application=ApplicationSubmit;
  }else{
   super.LoadModel();
   this.Application=ApplicationAmend;
   if(!super.getLoadSuccess()){return;}
  }
  this.AvailableCategories=Collections.unmodifiableSet(Application.RetrieveAvailableCategories());
 }
 protected @Override ProjectIdea ReadModel()throws NoEntityFoundException,NotAuthorizedException{
  if(!isAmending()){throw new IllegalStateException();}
  return ApplicationAmend.RetrieveForAmending(getTitle(),getCurrentUser());
 }
 
 public String SubmitProjectIdea(){
  if(isAmending()){throw new IllegalStateException();}
  try{
   ApplicationSubmit.Execute(getModel(),getCurrentUser());
  }catch(AlreadyExistsException Error){
   String duplicate;
   FacesContext.getCurrentInstance().addMessage("Title",new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    duplicate="A Project Idea with this Title already exists; specify a different Title",
    duplicate
   ));
   return "";
  }
  return "ProjectIdea.xhtml";
 }
 
 public String AmendProjectIdea(){
  if(!isAmending()||getLoadFailure()){throw new IllegalStateException();}
  try{
   super.Model=ApplicationAmend.Execute(getModel(),getCurrentUser());
  }catch(ConcurrencyException Error){
   super.setHTTPStatusCode(409);
   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    "Modified By Other User",
    "This Project Idea has been concurrently modified, or possibly deleted, by another user; copy the changes made to an external application, and refresh the page without re-submitting any data"
   ));
   return "";
  }catch(NotAuthorizedException Error){
   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    "Access Denied",
    MessageFormat.format("The currently logged in user ({0}) is not authorized to amend this Project Idea",getCurrentUser().getID())
   ));
   return "ProjectIdea.xhtml";
  }
  return "ProjectIdea.xhtml";
 }
}