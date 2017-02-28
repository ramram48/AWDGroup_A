package uk.ac.port.SUMS.ProjectIdeas.model;
import java.util.*;
import java.time.*;
import javax.persistence.*;

/**
Model layer Value Object, composition of a ProjectIdea entity.
Represents an audit of a change made to a ProjectIdea entity's Status property,
at a particular date–time, by a particular RegisteredUser.
@author Reciprocal
*/
@Embeddable @Access(AccessType.FIELD)
public class ProjectIdeaStatusChangeAudit implements java.io.Serializable{
 //TODO Use ZonedDateTime
 @Temporal(TemporalType.TIMESTAMP) @Column(nullable=false,name="WhenMade",updatable=false)
 private  Calendar When;
 @JoinColumn(nullable=false,name="WhoBy",updatable=false)
 private  RegisteredUser By;
 public ProjectIdeaStatusChangeAudit(Calendar When,RegisteredUser By){
  this.When=When;
  this.By=By;
 }
 //For JPA
 private ProjectIdeaStatusChangeAudit(){}
 
 public Calendar getWhen(){
  return When;
 }
 public RegisteredUser getBy(){
  return By;
 }
 
 public @Override String toString(){
  return "StatusChangeAudit["+getWhen()+","+getBy()+"]";
 }
}