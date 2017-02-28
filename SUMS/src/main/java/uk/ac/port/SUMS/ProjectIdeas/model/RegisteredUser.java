package uk.ac.port.SUMS.ProjectIdeas.model;
import java.io.*;
import javax.persistence.*;

/**
Model layer Entity.
Placeholder for testing; edit or replace with the actual implemented entity, from the Project Registration subsystem

A RegisteredUser is one of three mutually exclusive types—
Staff, Student, or External
A Staff RegisteredUser can additionally be any combination of the following types—
Coordinator, and Administrator
There may be more than one Administrator
*/
@Entity @Access(AccessType.FIELD)
abstract public class RegisteredUser implements Serializable{
 //TODO Presumably users are identified by some sort of supplied string ID
 @Id
 private String ID;
 protected String Organisation;
 public RegisteredUser(String ID){
  this.ID=ID;
 }
 //For JPA
 protected RegisteredUser(){}

 
 //TODO
 
 public String getID(){
  return ID;
 }
 public String getFullName(){
  return ID;
 }
 
 
 public String getOrganisation(){
  return Organisation;
 }
 
 abstract protected Types getType();
 /**
 @author Reciprocal
 @return true if this RegisteredUser is a StaffUser
 */
 public boolean isStaff(){
  return Types.Staff.equals(getType());
 }
 /**
 @author Reciprocal
 @return true if this RegisteredUser is a StudentUser
 */
 public boolean isStudent(){
  return Types.Student.equals(getType());
 }
 /**
 @author Reciprocal
 @return true if this RegisteredUser is an ExternalUser
 */
 public boolean isExternal(){
  return Types.External.equals(getType());
 }
 /**
 @author Reciprocal
 @return true if this RegisteredUser is a Coordinator StaffUser
 */
 public boolean isCoordinator(){
  return false;
 }
 /**
 @author Reciprocal
 @return true if this RegisteredUser is an Administrator StaffUser
 */
 public boolean isAdministrator(){
  return false;
 }
 
 public @Override int hashCode(){
  int hash=0;
  hash+=(ID!=null?ID.hashCode():0);
  return hash;
 }
 public @Override boolean equals(Object object){
  if(!(object instanceof RegisteredUser)){
   return false;
  }
  RegisteredUser other=(RegisteredUser)object;
  if((this.ID==null&&other.ID!=null)||(this.ID!=null&&!this.ID.equals(other.ID))){
   return false;
  }
  return true;
 }
 public @Override String toString(){
  return "RegisteredUser["+ID+"]";
 }
 
 static protected final String LocalOrganisation="University of Portsmouth";
 
 public static enum Types{Staff,Student,External}
}