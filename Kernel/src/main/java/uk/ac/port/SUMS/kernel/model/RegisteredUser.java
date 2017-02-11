package uk.ac.port.SUMS.kernel.model;
import java.io.*;
import javax.persistence.*;

/**
Domain Entity.
???
Identified by ???
A RegisteredUser is one of three mutually exclusive types — 
StaffUser, StudentUser, or ExternalUser.
*/
@Entity
abstract public class RegisteredUser implements Serializable{
 //TODO Presumably users are identified by some sort of supplied string ID
 @Id
 private final String ID;
 protected String Organisation;
 public RegisteredUser(String ID){
  this.ID=ID;
 }
 public String getID(){
  return ID;
 }

 
 //TODO
 
 
 public String getOrganisation(){
  return Organisation;
 }
 
 /**
 @author Reciprocal
 */
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
 
 /**
 @author Reciprocal
 */
 public static enum Types{Staff,Student,External}
}