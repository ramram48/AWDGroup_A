package uk.ac.port.SUMS.kernel.persistence;
import javax.ejb.*;
import javax.persistence.*;
import uk.ac.port.SUMS.kernel.model.*;

@Stateless
public class RegisteredUserDAO extends AbstractDAO<RegisteredUser>{
 @PersistenceContext(unitName="uk.ac.port.SUMS.kernel.PU")
 private EntityManager em;
 public RegisteredUserDAO(){
  super(RegisteredUser.class);
 }

 protected @Override EntityManager getEntityManager(){
  return em;
 }
 
 public RegisteredUser Read(String UserID){
  return super.Read(UserID);
 }
}
