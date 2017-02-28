package uk.ac.port.SUMS.ProjectIdeas.model;
import javax.persistence.*;

/**
Model layer Entity, specialization of RegisteredUser.
Placeholder for testing; see RegisteredUser
*/
@Entity @Access(AccessType.FIELD)
public class ExternalUser extends RegisteredUser{
 public ExternalUser(String ID){
  super(ID);
  super.Organisation=LocalOrganisation;
 }
 private ExternalUser(){
  super();
 }

 protected @Override Types getType(){
  return Types.External;
 }
}