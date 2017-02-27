package uk.ac.port.SUMS.ProjectIdeas.presentation;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.ejb.*;
import javax.inject.*;
import javax.faces.application.*;
import javax.faces.context.*;
import javax.faces.view.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;
import uk.ac.port.SUMS.ProjectIdeas.application.*;

@ViewScoped @Named(value="SAI") //Submit-Amend-Idea
public class SubmitAmendProjectIdeaController extends ProjectIdeaControllerBase{
 @EJB
 private SubmitProjectIdea ApplicationSubmit;
 @EJB
 private AmendProjectIdea ApplicationAmend;
 private transient SubmitAmendProjectIdea Application=null;
 private Map<String,ProjectCategory> AvailableCategories=null;
 private boolean amending;
 @Inject
 private transient Logger Log;
 
 public SubmitAmendProjectIdeaController(){}
 @PostActivate
 private void onActivated(){
  if(!amending){
   this.Application=ApplicationSubmit;
  }else{
   this.Application=ApplicationAmend;
  }
  this.Log=Logger.getLogger(this.getClass().getPackage().getName());
 }
 
 /**
 Preconditions: LoadModel has been called
 */
 public boolean isAmending(){
  return amending;
 }
 
 /**
 Preconditions: LoadModel has been called, and LoadFailure is false
 */
 public boolean getUserCanChangeStatus(){
  return getModel().canChangeStatus(getCurrentUser());
 }
 
 public List<ProjectIdea.Statuses> getProjectIdeaStatuses(){
  return Arrays.asList(ProjectIdea.Statuses.values());
 }
 public Collection<ProjectCategory> getAvailableCategories(){
  return AvailableCategories.values();
 }
 
 /**
 Preconditions: LoadModel has been called, and LoadFailure is false
 */
 public Collection<String> getSelectedCategories(){
  Collection<String> Result=new ArrayList<>(getModel().getCategories().size());
  for(ProjectCategory thisCategory : getModel().getCategories()){
   Result.add(thisCategory.getName());
  }
  return Result;
 }
 /**
 Preconditions: LoadModel has been called, and LoadFailure is false
 */
 public void setSelectedCategories(Collection<String> value){
  Set<ProjectCategory> Categories=new HashSet<>(value.size());
  for(String CategoryName : value){
   ProjectCategory SelectedCategory=AvailableCategories.get(CategoryName);
   if(SelectedCategory==null){
    Log.warning(MessageFormat.format("Unknown ProjectCategory \"{0}\" selected in view",CategoryName));
    continue;
   }
   Categories.add(SelectedCategory);
  }
  getModel().setCategories(Categories);
 }
 
 /**
 Preconditions: LoadModel has been called
 */
 public boolean getLoadFailure(){
  return /*!super.isPostBack()&&*/(super.Model==null||this.AvailableCategories==null);
 }
 /**
 Preconditions: The request is not a post-back
 */
 public @Override void LoadModel(){
  this.amending=isTitleSpecified();
  if(!amending){
   super.Model=new ProjectIdea();
   this.Application=ApplicationSubmit;
  }else{
   super.LoadModel();
   this.Application=ApplicationAmend;
   if(this.Model==null){return;}
  }
  Set<ProjectCategory> AvailableCategories=Collections.unmodifiableSet(Application.RetrieveAvailableCategories());
  this.AvailableCategories=new HashMap<>(AvailableCategories.size());
  for(ProjectCategory AvailableCategory : AvailableCategories){
   this.AvailableCategories.put(AvailableCategory.getName(),AvailableCategory);
  }
 }
 protected @Override ProjectIdea ReadModel()throws NoEntityFoundException,NotAuthorizedException{
  if(!isAmending()){throw new IllegalStateException();}
  return ApplicationAmend.RetrieveForAmending(getTitle(),getCurrentUser());
 }
 
 /**
 Preconditions: LoadFailure is false
 */
 public String SubmitProjectIdea(){
  if(isAmending()||getLoadFailure()){throw new IllegalStateException();}
  try{
   ApplicationSubmit.Execute(getModel(),getCurrentUser());
  }catch(AlreadyExistsException Error){
   String duplicate;
   FacesContext.getCurrentInstance().addMessage("Main:Title",new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    duplicate="A Project Idea with this Title already exists; specify a different Title",
    duplicate
   ));
   return null;
  }
  return FacesContext.getCurrentInstance().getExternalContext().encodeRedirectURL(
   "ProjectIdea.xhtml?faces-redirect=true",
   Collections.singletonMap("title",Collections.singletonList(getModel().getTitle()))
  );
 }
 
 /**
 Preconditions: LoadFailure is false
 */
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
   return null;
  }catch(NotAuthorizedException Error){
   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
    FacesMessage.SEVERITY_ERROR,
    "Access Denied",
    MessageFormat.format("The currently logged in user ({0}) is not authorized to amend this Project Idea",getCurrentUser().getID())
   ));
   super.Model=null;
   return null;
  }
  //super.Redirect("ProjectIdea.xhtml?includeViewParams=true");
  return "ProjectIdea.xhtml?includeViewParams=true&faces-redirect=true";
 }
}