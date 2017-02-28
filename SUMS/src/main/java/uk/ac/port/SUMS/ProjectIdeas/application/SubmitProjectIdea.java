package uk.ac.port.SUMS.ProjectIdeas.application;
import javax.ejb.*;
import uk.ac.port.SUMS.ProjectIdeas.model.*;
import uk.ac.port.SUMS.ProjectIdeas.model.exceptions.*;

/**
@author Reciprocal
*/
@Stateless @TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class SubmitProjectIdea extends SubmitAmendProjectIdea{
 public SubmitProjectIdea(){}
 
 @TransactionAttribute(TransactionAttributeType.REQUIRED)
 public void Execute(ProjectIdea Model,RegisteredUser By)throws AlreadyExistsException{
  //Setting of the Owner belongs here in the application layer, not the presentation layer
  Model.setOwner(By);
  if(DAO.Exists(Model.getTitle())){
   Transaction.setRollbackOnly();
   throw new AlreadyExistsException();
  }
  //TODO Error handling
  DAO.Create(Model);
 }
}