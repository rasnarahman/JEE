/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entity.Tuna;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author krish
 */
@Stateless
public class TunaFacade extends AbstractFacade<Tuna> implements business.TunaFacadeRemote {

    @PersistenceContext(unitName = "TunaApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TunaFacade() {
        super(Tuna.class);
    }
    
}
