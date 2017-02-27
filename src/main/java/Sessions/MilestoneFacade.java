package Sessions;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entities.Milestone;
import Sessions.AbstractFacade;

@Stateless
@Named("milestone")
public class MilestoneFacade extends AbstractFacade<Milestone> {

    @PersistenceContext(unitName = "k")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MilestoneFacade() {
        super(Milestone.class);
    }

}
