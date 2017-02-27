package Sessions;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entities.Person;
import Sessions.AbstractFacade;

@Stateless
@Named("person")
public class PersonFacade extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "k")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }

}
