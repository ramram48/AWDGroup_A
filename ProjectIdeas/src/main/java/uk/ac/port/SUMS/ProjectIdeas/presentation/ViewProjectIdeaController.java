package uk.ac.port.SUMS.ProjectIdeas.presentation;
import uk.ac.port.SUMS.kernel.model.exceptions.NoEntityFoundException;
import javax.ejb.*;
import javax.inject.*;
import javax.enterprise.context.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.ProjectIdeas.application.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;

@Named(value="I") //Idea
@RequestScoped
public class ViewProjectIdeaController extends ProjectIdeaControllerBase{
 @EJB
 private ViewProjectIdea Application;
 public ViewProjectIdeaController(){}
 
 public boolean getCanNavigateToAmendIdea(){
  return getModel().canEdit(getCurrentUser());
 }
 public boolean getCanNavigateToStatusChanges(){
  return getModel().canViewStatusChanges(getCurrentUser());
 }
 
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