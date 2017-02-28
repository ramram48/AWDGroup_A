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
@author Reciprocal
*/
abstract public class AuthenticatedController implements java.io.Serializable{
 @Inject
 private transient Logger Log;
 
 private void readObject(java.io.ObjectInputStream in)throws java.io.IOException,java.lang.ClassNotFoundException{
  in.defaultReadObject();
  this.Log=Logger.getLogger(this.getClass().getPackage().getName());
 }
 
 public RegisteredUser getCurrentUser(){
  
  //TODO
  
  //return new StudentUser("T");
  return StaffUser.Administrator("Q");
  
 }
 
 /**
 Convenience base class method for determining if the current request is a "Post-Back" request,
 a term also used in the ASP.NET framework.
 @return true if the current request is a "Post-Back", otherwise false
 @author Reciprocal
 */
 public boolean isPostBack(){
  try{
   return FacesContext.getCurrentInstance().isPostback();
  }catch(UnsupportedOperationException Error){
   return false;
  }
 }
 
 /**
 Convenience base class method for redirecting a request to a different page/resource.
 The JSF request cycle will be aborted after calling this method.
 @param To Relative URL of the resource to redirect to, relative to the web application's root
 @author Reciprocal
 */
 protected void Redirect(String To){
  FacesContext RequestContext=FacesContext.getCurrentInstance();
  ExternalContext _RequestContext=RequestContext.getExternalContext();
  try{
   _RequestContext.redirect(_RequestContext.getRequestContextPath()+"/"+_RequestContext.encodeResourceURL(To));
  }catch(IOException Error){
   RequestContext.responseComplete();
   Log.log(Level.WARNING,MessageFormat.format("IO error redirecting to \"{0}\"",To),Error);
  }
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