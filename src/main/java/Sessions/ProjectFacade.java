package Sessions;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entities.Project;
import Sessions.AbstractFacade;

@Stateless
@Named("project")
public class ProjectFacade extends AbstractFacade<Project> {

    @PersistenceContext(unitName = "k")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectFacade() {
        super(Project.class);
    }

}
