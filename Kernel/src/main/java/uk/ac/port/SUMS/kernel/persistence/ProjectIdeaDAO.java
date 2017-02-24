package uk.ac.port.SUMS.kernel.persistence;
import javax.persistence.*;
import javax.ejb.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;

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
 
 public boolean Exists(String Title){
  TypedQuery<Integer> ExistsQuery=getEntityManager().createNamedQuery("ProjectIdea.Exists",Integer.class);
  ExistsQuery.setParameter("Title",Title);
  return ExistsQuery.getSingleResult()!=0;
 }
 
 public @Override void Create(ProjectIdea ToCreate){
  super.Create(ToCreate);
 }
 
 public @Override ProjectIdea Update(ProjectIdea ToUpdate){
  return super.Update(ToUpdate);
 }
}