package uk.ac.port.SUMS.ProjectIdeas.model;
import javax.persistence.*;

/**
Model layer Entity, specialization of RegisteredUser.
Placeholder for testing; see RegisteredUser
*/
@Entity @Access(AccessType.FIELD)
public class StaffUser extends RegisteredUser{
 private boolean administrator;
 public StaffUser(String ID){
  super(ID);
  super.Organisation=LocalOrganisation;
 }
 private StaffUser(){
  super();
 }

 protected @Override Types getType(){
  return Types.Staff;
 }
 public @Override boolean isCoordinator(){
  //TODO
  return super.isCoordinator();
 }
 public @Override boolean isAdministrator(){
  //TODO
  return administrator;
 }
 
 static public StaffUser Administrator(String ID){
  StaffUser Result=new StaffUser(ID);
  Result.administrator=true;
  return Result;
 }
}