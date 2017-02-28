package uk.ac.port.SUMS.ProjectIdeas.application;
import javax.ejb.*;
import uk.ac.port.SUMS.ProjectIdeas.model.*;
import uk.ac.port.SUMS.ProjectIdeas.model.exceptions.*;
import uk.ac.port.SUMS.ProjectIdeas.persistence.*;

/**
Application layer EJB for retrieving an individual ProjectIdea model
for the user to view.
@author Reciprocal
*/
@Stateless
public class ViewProjectIdea{
 @EJB
 private ProjectIdeaDAO DAO;
 public ViewProjectIdea(){}
 
 //Could not distinguish between no entity found and not authorized for extra security, but this may be overdoing things
 public ProjectIdea Execute(String ProjectIdeaID,RegisteredUser ViewedBy)throws NoEntityFoundException,NotAuthorizedException{
  //TODO Any other error handling
  ProjectIdea Model=DAO.Read(ProjectIdeaID);
  if(!Model.canView(ViewedBy)){
   throw new NotAuthorizedException();
  }
  return Model;
 }
}