import javax.ejb.EJB;
import java.util.List;
import java.util.Scanner;


public class App  {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try {
            Menu o = new Menu();
            o.setWight(10.5);
            System.out.println();
            //iMenuDao.persist(o);

            EntityMenuDao eo = new EntityMenuDao();
            eo.findOrdersSubmittedSince(10.5);




        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }
}
