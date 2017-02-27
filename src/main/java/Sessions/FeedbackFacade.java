package Sessions;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entities.Feedback;
import Sessions.AbstractFacade;

@Stateless
@Named("feedback")
public class FeedbackFacade extends AbstractFacade<Feedback> {

    @PersistenceContext(unitName = "k")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeedbackFacade() {
        super(Feedback.class);
    }

}
