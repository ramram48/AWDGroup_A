package uk.ac.port.SUMS.kernel.persistence.exceptions;

/**
Typically thrown when trying to retrieve an entity with a specific ID,
and the entity was not found.
@author Reciprocal
*/
public class NoEntityFoundException extends Exception{
 public NoEntityFoundException(){}
 public NoEntityFoundException(String msg){
  super(msg);
 }
}