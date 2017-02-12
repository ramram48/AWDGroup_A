package uk.ac.port.SUMS.kernel.model;
import java.util.*;
import java.io.*;
import java.time.*;
import javax.persistence.*;

/**
@author Reciprocal
Domain Entity.
A ProjectIdea represents an idea a RegisteredUser has had for a Project.
A ProjectIdea is identified by its Title.
Alongside various properties describing the nature of the ProjectIdea,
it has a Status, defining whether it has been approved as a prospective Project or not.
*/
@Entity @Access(AccessType.FIELD)
public class ProjectIdea implements Serializable{
 //TODO Should all ProjectIdea Titles be unique
 @Id
 private String Title;
 @ManyToOne(fetch=FetchType.EAGER,optional=false)
 private RegisteredUser Owner;
 @Temporal(TemporalType.DATE)
 private ZonedDateTime SubmissionDate=null;
 //Mapping enum types as string rather than ordinal will provide resilience against the enum being modified, such as new, removed, or reordered values
 @Enumerated(EnumType.STRING)
 private Statuses Status=Statuses.Provisional;
 //TODO Readonly-ness of collections
 @ManyToMany(fetch=FetchType.EAGER)
 private Set<ProjectCategory> Categories=Collections.EMPTY_SET;
 private String Description="";
 private String AimsAndObjectives="";
 private String AcademicQuestion="";
 //TODO Will all Students have an associated StudentUser entity
 @ManyToMany(fetch=FetchType.EAGER)
 private Set<StudentUser> IntendedFor=Collections.EMPTY_SET;
 @Embedded @OrderBy("when DESC")
 private List<ProjectIdeaStatusChangeAudit> StatusChanges;
 
 public ProjectIdea(){}

 public String getTitle(){
  return Title;
 }
 public void setTitle(String Title){
  this.Title=Title;
 }
 /**
 Usually the Owner of a ProjectIdea is the RegisteredUser that originally created/submitted it.
 However, an Administrator can re-assign ProjectIdea entities to new Owners,
 which is normally done when a RegisteredUser leaves.
 */
 public RegisteredUser getOwner(){
  return Owner;
 }
 public void setOwner(RegisteredUser Owner){
  this.Owner=Owner;
 }
 /**
 The date (note, not date–time) that this ProjectIdea was originally created/submitted.
 */
 public ZonedDateTime getSubmissionDate(){
  return SubmissionDate;
 }
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
 public String getDescription(){
  return Description;
 }
 public void setDescription(String Description){
  this.Description=Description;
 }
 public String getAimsAndObjectives(){
  return AimsAndObjectives;
 }
 public void setAimsAndObjectives(String AimsAndObjectives){
  this.AimsAndObjectives=AimsAndObjectives;
 }
 public String getAcademicQuestion(){
  return AcademicQuestion;
 }
 public void setAcademicQuestion(String AcademicQuestion){
  this.AcademicQuestion=AcademicQuestion;
 }
 /**
 The set of Students that this ProjectIdea is aimed at;
 usually this is empty, indicating it is open to be allocated to any Student.
 */
 public Set<StudentUser> getIntendedFor(){
  return IntendedFor;
 }
 public void setIntendedFor(Set<StudentUser> IntendedFor){
  this.IntendedFor=IntendedFor;
 }
 /**
 If this is false, IntendedFor will detail the particular Students that this ProjectIdea is aimed at.
 */
 public boolean isIntendedForEveryone(){
  return IntendedFor.isEmpty();
 }

 /**
 The returned list will be in descending chronological order,
 with the most recent change first.
 */
 public List<ProjectIdeaStatusChangeAudit> getStatusChanges(){
  return StatusChanges;
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