import java.io.Serializable;

public interface GenericDao <E,K extends Serializable> {
    void persist(E entity);
    void remove(E entity);
    E findById(K id);
}
