package uk.ac.port.SUMS.kernel.model;
import java.io.*;
import javax.persistence.*;

/**
Domain Entity, specialization of RegisteredUser.
A StaffUser represents a member of Staff that has registered with the SUMS application.
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

 /**
 @author Reciprocal
 */
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