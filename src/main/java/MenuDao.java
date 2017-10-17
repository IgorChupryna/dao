import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class MenuDao implements MenuDaoInterface<Menu, String> {

	private Session currentSession;

	private Transaction currentTransaction;

	public MenuDao() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(Menu entity) {
		getCurrentSession().save(entity);
	}

	public void update(Menu entity) {
		getCurrentSession().update(entity);
	}

	public Menu findById(String id) {
		Menu book = (Menu) getCurrentSession().get(Menu.class, id);
		return book; 
	}

	public void delete(Menu entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> findAll() {
		List<Menu> menus = (List<Menu>) getCurrentSession().createQuery("from Menu").list();
		return menus;
	}

	public void deleteAll() {
		List<Menu> entityList = findAll();
		for (Menu entity : entityList) {
			delete(entity);
		}
	}

}
