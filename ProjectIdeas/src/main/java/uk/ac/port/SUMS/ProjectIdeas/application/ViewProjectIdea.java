package uk.ac.port.SUMS.ProjectIdeas.application;
import javax.ejb.*;
import uk.ac.port.SUMS.kernel.model.*;
import uk.ac.port.SUMS.kernel.model.exceptions.*;
import uk.ac.port.SUMS.kernel.persistence.*;
import uk.ac.port.SUMS.kernel.persistence.exceptions.*;

/**
Application layer EJB for retrieving an individual ProjectIdea model
for the user to view.
*/
@Stateless
public class ViewProjectIdea{
 @EJB
 private ProjectIdeaDAO DAO;
 public ViewProjectIdea(){}
 
 public ProjectIdea Execute(String ProjectIdeaID,RegisteredUser ViewedBy)throws NoEntityFoundException,NotAuthorizedException{
  //TODO Any other error handling
  ProjectIdea Model=DAO.Read(ProjectIdeaID);
  if(!Model.canView(ViewedBy)){
   throw new NotAuthorizedException();
  }
  return Model;
 }
}