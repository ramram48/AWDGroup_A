package uk.ac.port.SUMS.ProjectIdeas.application;
import uk.ac.port.SUMS.kernel.model.exceptions.NoEntityFoundException;
import javax.ejb.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;

@Stateless @TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AmendProjectIdea extends SubmitAmendProjectIdea{
 public AmendProjectIdea(){}
 
 public ProjectIdea RetrieveForAmending(String Title,RegisteredUser For)throws NoEntityFoundException,NotAuthorizedException{
  ProjectIdea Result=DAO.Read(Title);
  if(!Result.canEdit(For)){
   throw new NotAuthorizedException();
  }
  return Result;
 }
 
 //For the JSF presentation consumer, NotAuthorizedException won't be thrown (assuming non-malicious use), but for other presentation layer technologies it might
 @TransactionAttribute(TransactionAttributeType.REQUIRED)
 public ProjectIdea Execute(ProjectIdea Model,RegisteredUser By)throws ConcurrencyException,NotAuthorizedException{
  if(!Model.canEdit(By)){
   throw new NotAuthorizedException();
  }
  //TODO Authorize any Status changes, or reset to Provisional - should be done in Model layer
  //TODO What happens if not found; other error handling
  return DAO.Update(Model);
  //Transaction.setRollbackOnly();
 }
}