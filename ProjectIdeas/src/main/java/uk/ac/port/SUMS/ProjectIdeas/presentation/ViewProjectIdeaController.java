package uk.ac.port.SUMS.ProjectIdeas.presentation;
import uk.ac.port.SUMS.kernel.model.exceptions.NoEntityFoundException;
import javax.ejb.*;
import javax.inject.*;
import javax.enterprise.context.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.ProjectIdeas.application.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;

/*
Does not currently support post-backs,
due to the preconditions on the can–navigate predicates
*/
@RequestScoped @Named(value="I") //Idea
public class ViewProjectIdeaController extends ProjectIdeaControllerBase{
 @EJB
 private ViewProjectIdea Application;
 public ViewProjectIdeaController(){}
 
 /**
 Preconditions: LoadModel has been called, and LoadFailure is false
 */
 public boolean getCanNavigateToAmendIdea(){
  return getModel().canEdit(getCurrentUser());
 }
 /**
 Preconditions: LoadModel has been called, and LoadFailure is false
 */
 public boolean getCanNavigateToStatusChanges(){
  return getModel().canViewStatusChanges(getCurrentUser());
 }
 
 /**
 Preconditions: LoadModel has been called, or the request is a post-back
 @return true if there was an error whilst loading the model, otherwise false
 */
 public boolean getLoadFailure(){
  return !super.isPostBack()&&this.Model==null;
 }
 /**
 Will issue a redirect if the query string does not identify a ProjectIdea to retrieve,
 otherwise as for the base method.
 Preconditions: The request is not a post-back
 */
 public @Override void LoadModel(){
  if(!isTitleSpecified()){
   //TODO Test
   super.Redirect("ProjectIdeas.xhtml");
   return;
  }
  super.LoadModel();
 }
 protected @Override ProjectIdea ReadModel()throws NoEntityFoundException,NotAuthorizedException{
  return Application.Execute(getTitle(),getCurrentUser());
 }
}