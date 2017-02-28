package uk.ac.port.SUMS.ProjectIdeas.application;
import java.util.*;
import javax.ejb.*;
import uk.ac.port.SUMS.ProjectIdeas.persistence.*;
import uk.ac.port.SUMS.kernel.model.*;

/**
@author Reciprocal
*/
@Stateless
public class ViewCategories{
 @EJB
 private ProjectCategoryDAO DAO;
 public ViewCategories(){}
 
 public Set<ProjectCategory> Execute(RegisteredUser By){
  return DAO.ReadAll();
 }
}