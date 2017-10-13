
import sun.net.www.content.text.Generic;

import java.util.List;

public interface IMenuDao<I extends Number, M extends BaseEntity> extends GenericDao<Integer,Menu> {
    List findOrdersSubmittedSince(Double weight);
}
