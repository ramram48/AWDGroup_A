package uk.ac.port.sums.kernel.presentation;
import uk.ac.port.SUMS.kernel.model.*;

/**
Base class for Controllers (JSF Backing Beans) that provides information
about the currently logged in user.
*/
abstract public class AuthenticatedController{
 
 public RegisteredUser getCurrentUser(){
  
  //TODO
  
  return new StaffUser("Q");
  
 }
}