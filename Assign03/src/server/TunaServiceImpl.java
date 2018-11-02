/* File TunaServiceImpl.java
 * Author: Stanley Pieda
 * Modified By: Stanley Pieda
 * Modifed On: Jan 2018
 * Description: Remote Object that permits clients to insert
 *              Tuna records into a database, as well as
 *              retrieve them using String based uuid.
 */
package server;

import java.rmi.RemoteException;
import java.sql.SQLException;

import datatransfer.Tuna;

import dataaccesslayer.TunaDao;
import dataaccesslayer.TunaDaoImpl;

import remoteinterface.TunaService;

public class TunaServiceImpl implements TunaService  {
	
	public TunaServiceImpl() {}
	
	@Override
	public void insert(Tuna tuna) throws RemoteException{
		TunaDao dao = TunaDaoImpl.INSTANCE;
		dao.insertTuna(tuna);
	}

	@Override
	public Tuna findByUUID(String uuid) throws RemoteException{
		TunaDao dao = TunaDaoImpl.INSTANCE;
		return dao.findByUUID(uuid);
	}
	
	public void shutDownDao() {
		TunaDaoImpl.INSTANCE.shutdown();
	}
}
