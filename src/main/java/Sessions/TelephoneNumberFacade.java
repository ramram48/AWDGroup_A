package Sessions;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entities.TelephoneNumber;
import Sessions.AbstractFacade;

@Stateless
@Named("telephoneNumber")
public class TelephoneNumberFacade extends AbstractFacade<TelephoneNumber> {

    @PersistenceContext(unitName = "k")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelephoneNumberFacade() {
        super(TelephoneNumber.class);
    }

}
