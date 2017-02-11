package uk.ac.port.SUMS.kernel.model;
import java.io.*;
import javax.persistence.*;

/**
Domain Entity, specialization of RegisteredUser.
A StaffUser represents a member of Staff that has registered with the SUMS application.
*/
@Entity
public class StaffUser extends RegisteredUser{
 public StaffUser(String ID){
  super(ID);
  super.Organisation=LocalOrganisation;
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
  return super.isAdministrator();
 }
}