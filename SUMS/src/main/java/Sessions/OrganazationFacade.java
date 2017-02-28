package Sessions;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entities.Organazation;
import Sessions.AbstractFacade;

@Stateless
@Named("organazation")
public class OrganazationFacade extends AbstractFacade<Organazation> {

    @PersistenceContext(unitName = "k")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrganazationFacade() {
        super(Organazation.class);
    }

}
