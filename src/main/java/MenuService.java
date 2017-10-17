import java.util.List;

public class MenuService {


	private static MenuDao menuDao;

	public MenuService() {
		menuDao = new MenuDao();
	}

	public void persist(Menu entity) {
		menuDao.openCurrentSessionwithTransaction();
		menuDao.persist(entity);
		menuDao.closeCurrentSessionwithTransaction();
	}

	public void update(Menu entity) {
		menuDao.openCurrentSessionwithTransaction();
		menuDao.update(entity);
		menuDao.closeCurrentSessionwithTransaction();
	}

	public Menu findById(String id) {
		menuDao.openCurrentSession();
		Menu menu = menuDao.findById(id);
		menuDao.closeCurrentSession();
		return menu;
	}

	public void delete(String id) {
		menuDao.openCurrentSessionwithTransaction();
		Menu menu = menuDao.findById(id);
		menuDao.delete(menu);
		menuDao.closeCurrentSessionwithTransaction();
	}

	public List<Menu> findAll() {
		menuDao.openCurrentSession();
		List<Menu> menus = menuDao.findAll();
		menuDao.closeCurrentSession();
		return menus;
	}

	public void deleteAll() {
		menuDao.openCurrentSessionwithTransaction();
		menuDao.deleteAll();
		menuDao.closeCurrentSessionwithTransaction();
	}

	public MenuDao bookDao() {
		return menuDao;
	}
}
