/* File: TunaDaoImpl.java
 * Author: Stanley Pieda
 * Date: August 2018
 * Modified By:
 * References:
 * Ram N. (2013). Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 */
package dataaccesslayer;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import datatransfer.Tuna;


// enum for singleton
/**
 * Ensure that all classes and class members have Javadoc comments with your name
 * @author abc xyz
 */
public enum TunaDaoImpl implements TunaDao{
	
	/** Only use one constant for Singleton Design Pattern */
	INSTANCE;

	private SessionFactory factory;
	private StandardServiceRegistry sR;
	private Configuration config;
	private StandardServiceRegistryBuilder sRBuilder;
	private Session session;
	
	
	private TunaDaoImpl(){
		try {
			config = new Configuration()
				    .addAnnotatedClass(Tuna.class) // load the entities
				    .configure("hibernate.cfg.xml"); 
			sRBuilder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
			sR = sRBuilder.build();
			factory = config.buildSessionFactory(sR);  // we create a factory only once per application	
		}
		catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(sR);
			if(sR != null) {
				shutdown();
			}
			throw e;
		}
	}

	@Override
	public void insertTuna(Tuna tuna){
		System.out.println("Add tune"+tuna);
		session=factory.openSession();
		session.beginTransaction();
		session.save(tuna);
		session.getTransaction().commit();
		System.out.println("Inserted");

	}

	@SuppressWarnings("unchecked")
	@Override
	public Tuna findByUUID(String uuid){
		System.out.println("Finding tunas from DB by UUID"+uuid);
		
		//Session session = factory.getCurrentSession();
		session=factory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Tuna where UUID = :uuid ");
		query.setParameter("uuid", uuid);
		List<Tuna> tunas= (ArrayList<Tuna>) query.list(); 
		session.getTransaction().commit();
			
		return (tunas.size() == 0) ? null : tunas.get(0);

	}

	@Override
	public void shutdown() {
		session.close();
	}
}
