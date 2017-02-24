package uk.ac.port.SUMS.ProjectIdeas.persistence;
import java.util.*;
import javax.persistence.*;
import javax.ejb.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.persistence.*;

/**
DAO/Facade class for reading and creating immutable ProjectCategory entities,
from and to the underlying data store.
*/
@Stateless
public class ProjectCategoryDAO extends AbstractFacade<ProjectCategory>{
 @PersistenceContext(unitName="uk.ac.port.SUMS.kernel.PU")
 private EntityManager em;
 public ProjectCategoryDAO(){
  super(ProjectCategory.class);
 }
 
 protected @Override EntityManager getEntityManager(){
  return em;
 }

 public @Override Set<ProjectCategory> ReadAll(){
  return new HashSet<>(super.ReadAll());
 }
 
 public boolean Exists(ProjectCategory Check){
  TypedQuery<Boolean> ExistsQuery=getEntityManager().createNamedQuery("ProjectCategory.Exists",Boolean.class);
  ExistsQuery.setParameter("CategoryName",Check.getName());
  return ExistsQuery.getSingleResult();
 }
 
 public @Override void Create(ProjectCategory ToCreate){
  super.Create(ToCreate);
 }
}