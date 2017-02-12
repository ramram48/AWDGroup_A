package uk.ac.port.SUMS.kernel.model;
import java.time.*;
import java.io.*;
import javax.persistence.*;

/**
Domain Value Object.
Composition of a ProjectIdea entity.
Represents an audit of a change made to a ProjectIdea entity's Status property,
at a particular date–time, by a particular RegisteredUser.
*/
@Embeddable @Access(AccessType.FIELD)
public class ProjectIdeaStatusChangeAudit implements Serializable{
 @Temporal(TemporalType.TIMESTAMP) @Column(name="WhenMade")
 private final ZonedDateTime When;
 @Column(name="WhoBy")
 private final RegisteredUser By;
 public ProjectIdeaStatusChangeAudit(ZonedDateTime When,RegisteredUser By){
  this.When=When;
  this.By=By;
 }
 
 public ZonedDateTime getWhen(){
  return When;
 }
 public RegisteredUser getBy(){
  return By;
 }
 
 public @Override String toString(){
  return "StatusChangeAudit["+getWhen()+","+getBy()+"]";
 }
}