package uk.ac.port.SUMS.kernel.model;
import java.io.*;
import javax.persistence.*;

/**
Domain Entity, specialization of RegisteredUser.
A StudentUser represents a Student that has registered with the SUMS application.
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

 /**
 @author Reciprocal
 */
 protected @Override Types getType(){
  return Types.Student;
 }
}