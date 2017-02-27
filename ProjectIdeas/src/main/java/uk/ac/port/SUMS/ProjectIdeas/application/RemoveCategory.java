package uk.ac.port.SUMS.ProjectIdeas.application;
import javax.annotation.*;
import javax.ejb.*;
import uk.ac.port.SUMS.ProjectIdeas.persistence.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;

@Stateless
public class RemoveCategory{
 @EJB
 private ProjectCategoryDAO DAO;
 public RemoveCategory(){}
 
 public void Execute(ProjectCategory Remove,RegisteredUser By)throws NotAuthorizedException{
  if(!Remove.canDelete(By)){
   throw new NotAuthorizedException();
  }
  try{
   DAO.Delete(Remove);
  }catch(ConcurrencyException Error){
   //ProjectCategory entities are immutable; should only be thrown if the ProjectCategory has been concurrently deleted
  }
 }
}