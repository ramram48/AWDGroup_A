package uk.ac.port.SUMS.ProjectIdeas.presentation;
import java.text.*;
import java.util.*;
import javax.annotation.*;
import javax.inject.*;
import javax.ejb.*;
import javax.enterprise.context.*;
import javax.faces.application.*;
import javax.faces.context.*;
import javax.validation.*;
import uk.ac.port.SUMS.kernel.presentation.*;
import uk.ac.port.SUMS.ProjectIdeas.application.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;

/**
@author Reciprocal
*/
@RequestScoped @Named(value="MC") //Manage Categories
public class ManageCategoriesController extends AuthenticatedController{
 @EJB
 private ViewCategories ApplicationView;
 @EJB
 private AddCategory ApplicationAdd;
 @EJB
 private RemoveCategory ApplicationRemove;
 private Set<ProjectCategory> Model=null;
 private String ModelAddName;
 @Inject
 private Validator ValidationService;
 public ManageCategoriesController(){}
 @PostConstruct
 private void onConstructed(){
  this.Model=ApplicationView.Execute(getCurrentUser());
 }
 
 public boolean getLoadFailure(){
  return this.Model==null;
 }
 
 public Set<ProjectCategory> getModel(){
  return Model;
 }
 public String getModelAddName(){
  return ModelAddName;
 }
 public void setModelAddName(String value){
  this.ModelAddName=value;
 }
 
 public String AddCategory(){
  ProjectCategory ToAdd=new ProjectCategory(this.ModelAddName);
  if(!ValidateCategory(ToAdd)){
   return null;
  }
  try{
   ApplicationAdd.Execute(ToAdd,getCurrentUser());
  }catch(AlreadyExistsException Error){
   String duplicate;
   FacesContext.getCurrentInstance().addMessage("Add:Name",new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    duplicate="A Category with this Name already exists; specify a different Name",
    duplicate
   ));
   return null;
  }catch(NotAuthorizedException Error){
   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    "Access Denied",
    MessageFormat.format("The currently logged in user ({0}) is not authorized to add this Category",getCurrentUser().getID())
   ));
   return null;
  }
  return "ManageCategories.xhtml?faces-redirect=true";
 }
 
 public String RemoveCategory(ProjectCategory ToRemove){
  try{
   ApplicationRemove.Execute(ToRemove,getCurrentUser());
  }catch(NotAuthorizedException Error){
   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    "Access Denied",
    MessageFormat.format("The currently logged in user ({0}) is not authorized to remove this Category",getCurrentUser().getID())
   ));
   return null;
  }
  return "ManageCategories.xhtml?faces-redirect=true";
 }
 
 protected boolean ValidateCategory(ProjectCategory ToValidate){
  Set<ConstraintViolation<ProjectCategory>> Errors=ValidationService.validate(ToValidate);
  if(Errors.size()<=0){
   return true;
  }
  FacesContext Response=FacesContext.getCurrentInstance();
  Response.validationFailed();
  for(ConstraintViolation<ProjectCategory> thisError : Errors){
   Response.addMessage("Add:Name",new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    thisError.getMessage(),
    thisError.getMessage()
   ));
  }
  Response.renderResponse();
  return false;
 }
}