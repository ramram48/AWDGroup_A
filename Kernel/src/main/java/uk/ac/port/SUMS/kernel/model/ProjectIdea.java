package uk.ac.port.SUMS.kernel.model;
import java.util.*;
import java.text.*;
import java.time.*;
import java.util.logging.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import uk.ac.port.SUMS.kernel.infrastructure.*;
import uk.ac.port.SUMS.kernel.infrastructure.constraints.*;

/**
Model layer Entity.
A ProjectIdea represents an idea a RegisteredUser has had for a Project.
A ProjectIdea is identified by its Title.
Alongside various properties describing the nature of the ProjectIdea,
it has a Status, defining whether it has been approved as a prospective Project or not.
@author Reciprocal
*/
@Entity @Access(AccessType.FIELD)
//Would ideally place this on the persistence layer ProjectIdea DAO
@NamedQuery(name="ProjectIdea.Exists",query=
 "SELECT CASE when COUNT(PI.Title)>0 then true else false end from ProjectIdea PI where PI.Title=:Title"
)
public class ProjectIdea implements java.io.Serializable{
 @Id
 private String Title;
 @ManyToOne(fetch=FetchType.EAGER,optional=false)
 private RegisteredUser Owner;
 //TODO Use ZonedDateTime
 @Temporal(TemporalType.DATE) @Column(nullable=false,updatable=false)
 private Calendar SubmissionDate=null;
 //Mapping enum types as string rather than ordinal will provide resilience against the enum being modified, such as new, removed, or reordered values
 @Enumerated(EnumType.STRING) @Column(nullable=false)
 private Statuses Status=Statuses.Provisional;
 //TODO Readonly-ness of collections
 @ManyToMany(fetch=FetchType.EAGER)
 //@JoinTable(inverseJoinColumns={@JoinColumn(foreignKey=@ForeignKey(foreignKeyDefinition="FOREIGN KEY (CATEGORIES_NAME) references PROJECTCATEGORY(\"NAME\") on delete cascade"))})
 @JoinTable(inverseForeignKey=@ForeignKey(
  name="FK_PROJECTCATEGORY",
  foreignKeyDefinition="FOREIGN KEY (CATEGORIES_NAME) references PROJECTCATEGORY(\"NAME\") on delete cascade"
 ),foreignKey=@ForeignKey(
  //Possible bug; setting inverse foreign key removes the other foreign key
  name="FK_PROJECTIDEA",
  foreignKeyDefinition="FOREIGN KEY (PROJECTIDEA_TITLE) references PROJECTIDEA(TITLE)"
 ))
 private Set<ProjectCategory> Categories=Collections.EMPTY_SET;
 @Column(nullable=false)
 private String Description="";
 @Column(nullable=false)
 private String AimsAndObjectives="";
 @Column(nullable=false)//edited by ramazan
 private String AcademicQuestion=""; // edited by pat
 @Column(nullable=false)
 private String IntendedFor="";
 /*
 //TODO Will all Students have an associated StudentUser entity
 @ManyToMany(fetch=FetchType.EAGER)
 private Set<StudentUser> IntendedFor=Collections.EMPTY_SET;
 */
 //Use explicit order column to avoid dependency on system clock
 @ElementCollection(fetch=FetchType.LAZY) /*@OrderBy("when DESC")*/ @OrderColumn(name="ChangeOrder",nullable=false)
 private List<ProjectIdeaStatusChangeAudit> StatusChanges;
 @Transient
 protected final StringSanitizer StringSanitizerService=new StringSanitizer();
 @Transient
 protected final Logger Log=Logger.getLogger(this.getClass().getPackage().getName());
 
 public ProjectIdea(){}

 @NotEmpty
 public String getTitle(){
  return Title;
 }
 /**
 The Title should only be changed whilst creating a new ProjectIdea;
 changing existing ProjectIdea entities will have undefined effects.
 */
 public void setTitle(String Title){
  //Design issue — Must remember to use StringSanitizer in all relevant places; could use special data type but this adds complexity
  Title=StringSanitizerService.ProcessLine(Title);
  Log.info(MessageFormat.format("Changing ProjectIdea Title from {0} to {1}",this.Title,Title));
  this.Title=Title;
 }
 
 /**
 Usually the Owner of a ProjectIdea is the RegisteredUser that originally created/submitted it.
 However, an Administrator can re-assign ProjectIdea entities to new Owners,
 which is normally done when a RegisteredUser leaves.
 */
 @NotNull
 public RegisteredUser getOwner(){
  return Owner;
 }
 public void setOwner(RegisteredUser Owner){
  this.Owner=Owner;
 }
 
 /**
 The date (note, not date–time) that this ProjectIdea was originally created/submitted.
 */
 public Calendar getSubmissionDate(){
  return SubmissionDate;
 }
 
 @NotNull
 public Statuses getStatus(){
  return Status;
 }
 public void setStatus(Statuses Status){
  this.Status=Status;
 }
 
 public Set<ProjectCategory> getCategories(){
  return Categories;
 }
 public void setCategories(Set<ProjectCategory> Categories){
  this.Categories=Categories;
 }
 
 @NotEmpty
 public String getDescription(){
  return Description;
 }
 public void setDescription(String Description){
  Description=StringSanitizerService.ProcessParagraph(Description);
  if(Description==null){Description="";}
  this.Description=Description;
 }
 
 @NotEmpty
 public String getAimsAndObjectives(){
  return AimsAndObjectives;
 }
 public void setAimsAndObjectives(String AimsAndObjectives){
  AimsAndObjectives=StringSanitizerService.ProcessParagraph(AimsAndObjectives);
  this.AimsAndObjectives=AimsAndObjectives;
 }
 
 @NotEmpty
 public String getAcademicQuestion(){
  return AcademicQuestion;
 }
 public void setAcademicQuestion(String AcademicQuestion){
  AcademicQuestion=StringSanitizerService.ProcessParagraph(AcademicQuestion);
  this.AcademicQuestion=AcademicQuestion;
 }
 
 /**
 The listing of Students that this ProjectIdea is aimed at;
 usually this is blank, indicating it is open to be allocated to any Student.
 */
 public String getIntendedFor(){
  return IntendedFor;
 }
 public void setIntendedFor(String IntendedFor){
  IntendedFor=StringSanitizerService.ProcessLine(IntendedFor);
  if(IntendedFor==null){IntendedFor="";}
  this.IntendedFor=IntendedFor;
 }
 //TODO Enhance IntendedFor property from a simple string back to a set of Students
 /*
 The set of Students that this ProjectIdea is aimed at;
 usually this is empty, indicating it is open to be allocated to any Student.
 public Set<StudentUser> getIntendedFor(){
  return IntendedFor;
 }
 public void setIntendedFor(Set<StudentUser> IntendedFor){
  this.IntendedFor=IntendedFor;
 }
 If this is false, IntendedFor will detail the particular Students that this ProjectIdea is aimed at.
 public boolean isIntendedForEveryone(){
  return IntendedFor.isEmpty();
 }
 */

 //TODO Reverse order
 /**
 The returned list will be in descending chronological order,
 with the most recent change first.
 */
 public List<ProjectIdeaStatusChangeAudit> getStatusChanges(){
  return StatusChanges;
 }
 
 /**
 @return true if the supplied user may view this ProjectIdea, otherwise false
 */
 public boolean canView(RegisteredUser User){
  return User.equals(this.getOwner()) || User.isStaff() || Statuses.Approved.equals(this.getStatus())
  || User.isAdministrator();
 }
 /**
 @return true if the supplied user may amend this ProjectIdea, otherwise false
 */
 public boolean canEdit(RegisteredUser User){
  return User.equals(this.getOwner()) || User.isCoordinator()
  || User.isAdministrator();
 }
 /**
 @return true if the supplied user may view the Status change history of this ProjectIdea, otherwise false
 */
 public boolean canViewStatusChanges(RegisteredUser User){
  return User.equals(this.getOwner()) || User.isStaff()
  || User.isAdministrator();
 }
 /**
 @return true if the supplied user may change a new or existing ProjectIdea's Status, otherwise false
 */
 public boolean canChangeStatus(RegisteredUser User){
  return User.isStaff()
  || User.isAdministrator();
 }
 
 @PrePersist
 private void onCreating(){
  Log.info(MessageFormat.format("New ProjectIdea \"{0}\" being created; setting SubmissionDate",getTitle()));
  if(this.SubmissionDate!=null){
   Log.warning(MessageFormat.format("Newly created ProjectIdea \"{1}\" already has a SubmissionDate of {0}",this.SubmissionDate,getTitle()));
   return;
  }
  this.SubmissionDate=Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Z")),Locale.ROOT);
 }
 
 public @Override int hashCode(){
  int hash=0;
  hash+=(getTitle()!=null?getTitle().hashCode():0);
  return hash;
 }
 public @Override boolean equals(Object object){
  if(!(object instanceof ProjectIdea)){
   return false;
  }
  ProjectIdea other=(ProjectIdea)object;
  if((this.getTitle()==null&&other.getTitle()!=null)||(this.getTitle()!=null&&!this.Title.equals(other.Title))){
   return false;
  }
  return true;
 }
 public @Override String toString(){
  return "ProjectIdea["+getTitle()+"]";
 }

 public static enum Statuses{
  /**
  The ProjectIdea has been created by non-Staff,
  or the ProjectIdea has been modified by non-Staff,
  and is awaiting approval;
  this is typically the initial state for a ProjectIdea
  */
  Provisional,
  /**
  The ProjectIdea has been reviewed and approved by a Staff member
  */
  Approved,
  /**
  An Approved ProjectIdea that has been allocated to the maximum number of Students,
  and so is no longer available for further Students to undertake
  */
  Allocated,
  /**
  The ProjectIdea has been reviewed and not approved by a Staff member,
  or the Owner has decided to withdraw their idea
  */
  Withdrawn
 }
}