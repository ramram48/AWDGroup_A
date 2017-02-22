package uk.ac.port.SUMS.kernel.presentation;
import uk.ac.port.SUMS.kernel.model.*;

/**
Base class for Controllers (JSF Backing Beans) that provides information
about the currently logged in user.
Placeholder for testing; edit/replace with solution used by Project Registration module
*/
abstract public class AuthenticatedController{
 
 public RegisteredUser getCurrentUser(){
  
  //TODO
  
  return StaffUser.Administrator("Q");
  
 }
}