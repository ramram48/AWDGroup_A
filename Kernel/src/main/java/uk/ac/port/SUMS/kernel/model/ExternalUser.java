package uk.ac.port.SUMS.kernel.model;
import java.io.*;
import javax.persistence.*;

/**
Domain Entity, specialization of RegisteredUser.
An ExternalUser represents someone external to the university
that has registered with the SUMS application.
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

 /**
 @author Reciprocal
 */
 protected @Override Types getType(){
  return Types.External;
 }
}