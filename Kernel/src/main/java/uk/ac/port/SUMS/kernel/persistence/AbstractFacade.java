package uk.ac.port.SUMS.kernel.persistence;
import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import uk.ac.port.SUMS.kernel.persistence.exceptions.*;

/**
Persistence layer convenience base class for DAO classes,
providing template implementations for the basic four data operations.
@param <T> Entity class that derived DAO classes will operate on
@author Reciprocal
*/
abstract class AbstractFacade<T>{
 private final Class<T> entityClass;
 protected AbstractFacade(Class<T> entityClass){
  this.entityClass=entityClass;
 }

 abstract protected EntityManager getEntityManager();

 protected void Create(T entity){
  getEntityManager().persist(entity);
 }

 protected T Update(T entity){
  return getEntityManager().merge(entity);
 }

 protected void Delete(T entity){
  getEntityManager().remove(getEntityManager().merge(entity));
 }

 protected T Read(Object id)throws NoEntityFoundException{
  T Result=getEntityManager().find(entityClass,id);
  if(Result==null){throw new NoEntityFoundException();}
  return Result;
 }
 protected long ReadTotal(){
  CriteriaQuery cq=getEntityManager().getCriteriaBuilder().createQuery();
  Root<T> rt=cq.from(entityClass);
  cq.select(getEntityManager().getCriteriaBuilder().count(rt));
  Query q=getEntityManager().createQuery(cq);
  return (long)q.getSingleResult();
 }
 protected Collection<T> ReadAll(){
  CriteriaQuery cq=getEntityManager().getCriteriaBuilder().createQuery();
  cq.select(cq.from(entityClass));
  return getEntityManager().createQuery(cq).getResultList();
 }
 /*
 protected Collection<T> ReadRange(int[] range){
  javax.persistence.criteria.CriteriaQuery cq=getEntityManager().getCriteriaBuilder().createQuery();
  cq.select(cq.from(entityClass));
  javax.persistence.Query q=getEntityManager().createQuery(cq);
  q.setMaxResults(range[1]-range[0]+1);
  q.setFirstResult(range[0]);
  return q.getResultList();
 }
 */
}