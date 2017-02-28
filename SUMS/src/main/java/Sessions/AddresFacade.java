package Sessions;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entities.Addres;
import Sessions.AbstractFacade;

@Stateless
@Named("addres")
public class AddresFacade extends AbstractFacade<Addres> {

    @PersistenceContext(unitName = "uk.ac.port.SUMS.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddresFacade() {
        super(Addres.class);
    }

}
