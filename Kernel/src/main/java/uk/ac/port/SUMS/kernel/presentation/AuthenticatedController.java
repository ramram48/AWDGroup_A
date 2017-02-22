package uk.ac.port.SUMS.kernel.presentation;
import java.io.*;
import java.text.*;
import java.util.logging.*;
import javax.inject.*;
import javax.faces.context.*;
import uk.ac.port.SUMS.kernel.model.*;

/**
Base class for Controllers (JSF Backing Beans) that provides information
about the currently logged in user.
Placeholder for testing; edit/replace with solution used by Project Registration module
*/
abstract public class AuthenticatedController{
 @Inject
 private Logger Log;
 
 public RegisteredUser getCurrentUser(){
  
  //TODO
  
  return StaffUser.Administrator("Q");
  
 }
 
 /**
 Convenience base class method for redirecting a request to a different page.
 The JSF request cycle will be aborted after calling this method.
 @param To The JSF page to redirect to, as would be returned by a backing bean application method
 @author Reciprocal
 */
 protected void Redirect(String To){
  FacesContext RequestContext=FacesContext.getCurrentInstance();
  ExternalContext _RequestContext=RequestContext.getExternalContext();
  try{
   _RequestContext.redirect(_RequestContext.encodeActionURL(_RequestContext.getRequestContextPath()+To));
  }catch(IOException Error){
   Log.log(Level.WARNING,MessageFormat.format("IO error redirecting to \"{0}\"",To),Error);
  }
  RequestContext.responseComplete();
 }
 
 /**
 Convenience base class method for setting the HTTP status code of the response, if applicable.
 This method only sets the response code;
 it does not direct to or display any error page.
 @param StatusCode The HTTP response status code to send in the response
 @author Reciprocal
 */
 protected void setHTTPStatusCode(int StatusCode){
  try{
   FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(StatusCode);
  }catch(UnsupportedOperationException __){
  }
 }
}