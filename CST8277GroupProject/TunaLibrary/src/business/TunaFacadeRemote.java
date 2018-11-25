/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entity.Tuna;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author krish
 */
@Remote
public interface TunaFacadeRemote {

    void create(Tuna tuna);

    void edit(Tuna tuna);

    void remove(Tuna tuna);

    Tuna find(Object id);

    List<Tuna> findAll();

    List<Tuna> findRange(int[] range);

    int count();
    
}
