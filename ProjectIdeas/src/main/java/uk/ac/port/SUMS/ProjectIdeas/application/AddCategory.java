package uk.ac.port.SUMS.ProjectIdeas.application;
import javax.annotation.*;
import javax.ejb.*;
import uk.ac.port.SUMS.ProjectIdeas.persistence.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;

@Stateless
public class AddCategory{
 @EJB
 private ProjectCategoryDAO DAO;
 @Resource
 private SessionContext Transaction;
 public AddCategory(){}
 
 public void Execute(ProjectCategory Add,RegisteredUser By)throws AlreadyExistsException,NotAuthorizedException{
  if(!Add.canCreate(By)){
   throw new NotAuthorizedException();
  }
  if(DAO.Exists(Add.getName())){
   Transaction.setRollbackOnly();
   throw new AlreadyExistsException();
  }
  DAO.Create(Add);
 }
}