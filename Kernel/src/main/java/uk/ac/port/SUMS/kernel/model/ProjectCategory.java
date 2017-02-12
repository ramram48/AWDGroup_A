package uk.ac.port.SUMS.kernel.model;
import java.io.*;
import javax.persistence.*;

//TODO Who controls the lifetime of ProjectCategory entities
/**
@author Reciprocal
Domain Entity.
A ProjectIdea is associated with a set of ProjectCategories,
which are simply descriptive string names.
They are identified by their name.
*/
@Entity @Access(AccessType.FIELD)
public class ProjectCategory implements Serializable{
 @Id
 private final String Name;
 public ProjectCategory(String Name){
  this.Name=Name;
 }

 public String getName(){
  return Name;
 }

 public @Override int hashCode(){
  return Name.hashCode();
 }
 public @Override boolean equals(Object object){
  if(!(object instanceof ProjectCategory)){
   return false;
  }
  ProjectCategory other=(ProjectCategory)object;
  if((this.Name==null&&other.Name!=null)||(this.Name!=null&&!this.Name.equals(other.Name))){
   return false;
  }
  return true;
 }
 public @Override String toString(){
  return "ProjectCategory["+Name+"]";
 }
}