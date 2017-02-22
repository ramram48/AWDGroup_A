package uk.ac.port.SUMS.kernel.model;
import java.io.*;
import javax.persistence.*;

/**
Model layer Entity, specialization of RegisteredUser.
Placeholder for testing; see RegisteredUser
*/
@Entity @Access(AccessType.FIELD)
public class StudentUser extends RegisteredUser{
 public StudentUser(String ID){
  super(ID);
  super.Organisation=LocalOrganisation;
 }
 private StudentUser(){
  super();
 }

 protected @Override Types getType(){
  return Types.Student;
 }
}