package uk.ac.port.SUMS.ProjectIdeas.test;
import java.text.*;
import java.util.*;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.*;
import javax.persistence.*;

@RequestScoped @Named(value="Debug")
public class Debug{
 @PersistenceContext(unitName="uk.ac.port.SUMS.kernel.PU")
 private EntityManager em;
 public void Execute(){
  try{
   //String Result=MessageFormat.format("{0,time,full}",Calendar.getInstance().getTime());
   //boolean Result=em.createQuery("SELECT CASE when COUNT(PI.Title)>0 then true else false end from ProjectIdea PI where PI.Title='Sample'",Integer.class).getSingleResult()!=0;
   String Result=FacesContext.class.getPackage().getImplementationVersion();
   return;
  }catch(Throwable Error){
   return;
  }
 }
}
