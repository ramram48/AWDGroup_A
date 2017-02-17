package uk.ac.port.SUMS.ProjectIdeas.test;
import javax.faces.view.*;
import javax.inject.*;
import javax.annotation.*;
import javax.enterprise.context.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.persistence.*;

/**
@author Reciprocal
*/
@RequestScoped @Named(value="ProjectIdeaPersistence")
public class ProjectIdeaPersistence{
 @Inject
 private ProjectIdeaDAO DAO;
 @Inject
 private RegisteredUserDAO DAOUser;
 public ProjectIdeaPersistence(){}
 
 public String ExecuteCreate(){
  RegisteredUser Owner=DAOUser.Read("W");
  ProjectIdea Model=new ProjectIdea();
  Model.setTitle("The Title");
  Model.setOwner(Owner);
  Model.setDescription("The Description");
  Model.setAcademicQuestion("The Question");
  Model.setAimsAndObjectives("The Objectives");
  DAO.Create(Model);
  return "";
 }
}