/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import javax.ejb.Stateless;
import Sessions.*;
import Entities.*;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author a
 */
@Stateless
public class PersonBussines {
 @EJB
 private StudentFacade psf;
 @EJB
 private PersonStatusFacade personstatusfacade;
 @PersistenceContext
 private  EntityManager em;

    public void update(Student f) throws BusinessExceptions
    {
   Student employee = em.find(Student.class,f.getId());
 
  
  employee.getPerson().setName("Osman");
 
    

        //return something
       
    
    }
    
   public void updatePersonStatus(PersonStatus ps) throws BusinessExceptions
    {
   PersonStatus employee = em.find(PersonStatus.class,ps.getId());
   employee.setEditable(ps.isEditable());
  // employee.setStatusName(ps.getStatusName()); 
        System.out.println(ps.getStatusName());
        //return something
       
    
    }
    public void add1(Student s) throws BusinessExceptions
    {
        Person person=new Person();
        Mark m=new Mark();
        Mail mail=new Mail();
        Addres a=new Addres();
        Project p=new Project();
        
         em.persist(s.getPerson());
         em.persist(s.getPerson().getAddreses().get(0));
         em.persist(s.getPerson().getMails().get(0));
         em.persist(s);         
        //return something
       
    
    }
    public void addpersonstatus(PersonStatus ps) throws BusinessExceptions
    {
    em.persist(ps);
    
    }
    
public List<Student> get() throws BusinessExceptions
    {
    System.out.println(psf.findAll().size());  
    
    return  psf.findAll();

        //return something
        
    
    }
public List<PersonStatus> getpersonstatus() throws BusinessExceptions
    {
    //System.out.println(personstatusfacade.findAll().size());  
    
    return  personstatusfacade.findAll();

        //return something
        
    
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
