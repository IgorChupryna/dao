import javax.ejb.EJB;
import java.util.List;

public class EntityMenuDao extends ABaseDao<Integer,Menu>implements IMenuDao<Integer,Menu> {
    @EJB
    public IMenuDao iMenuDao;

    public List findOrdersSubmittedSince(Double weight) {
        System.out.println();
        List<Menu> im=iMenuDao.findOrdersSubmittedSince(weight);
        for (Menu m:im)
        System.out.println(m);
        return null;
    }

    public void persist(Menu entity) {

    }

    public void remove(Menu entity) {

    }

    public Menu findById(Integer id) {
        return null;
    }
}
