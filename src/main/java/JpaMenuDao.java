import javax.persistence.Query;
import java.util.List;

abstract class JpaMenuDao extends ABaseDao<Integer,Menu> implements IMenuDao<Number, BaseEntity> {
    public List<Menu> findOrdersSubmittedSince(Double weight) {
        System.out.println("gg");
        Query q = entityManager.createQuery(
                "SELECT e FROM " + entityClass.getName() + " e WHERE weight = :weight");
        q.setParameter("weight", weight);
        return (List) q.getResultList();
    }
}
