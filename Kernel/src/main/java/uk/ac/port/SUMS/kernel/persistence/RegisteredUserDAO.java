package uk.ac.port.SUMS.kernel.persistence;
import javax.ejb.*;
import javax.persistence.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.persistence.exceptions.*;

/**
DAO/Facade for RegisteredUser entities and their derivations.
Placeholder for testing; edit/replace with the actual DAO class
as implemented by the Project Registration subsystem
@author Reciprocal
*/
@Stateless
public class RegisteredUserDAO extends AbstractFacade<RegisteredUser>{
 @PersistenceContext(unitName="uk.ac.port.SUMS.kernel.PU")
 private EntityManager em;
 public RegisteredUserDAO(){
  super(RegisteredUser.class);
 }

 protected @Override EntityManager getEntityManager(){
  return em;
 }
 
 public RegisteredUser Read(String UserID)throws NoEntityFoundException{
  return super.Read(UserID);
 }
}
