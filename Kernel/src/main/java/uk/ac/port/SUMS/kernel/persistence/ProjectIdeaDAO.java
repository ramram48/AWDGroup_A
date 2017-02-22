package uk.ac.port.SUMS.kernel.persistence;
import javax.persistence.*;
import javax.ejb.*;
import uk.ac.port.SUMS.kernel.persistence.exceptions.*;
import uk.ac.port.SUMS.kernel.model.*;

/**
DAO/Facade class for reading and writing ProjectIdea entities,
along with related value objects within the ProjectIdea aggregate root,
from and to the underlying data store.
@author Reciprocal
*/
@Stateless
public class ProjectIdeaDAO extends AbstractFacade<ProjectIdea>{
 @PersistenceContext(unitName="uk.ac.port.SUMS.kernel.PU")
 private EntityManager em;
 public ProjectIdeaDAO(){
  super(ProjectIdea.class);
 }
 
 protected @Override EntityManager getEntityManager(){
  return em;
 }

 public ProjectIdea Read(String Title)throws NoEntityFoundException{
  return super.Read(Title);
 }
 
 public @Override void Create(ProjectIdea ToCreate){
  super.Create(ToCreate);
 }
}