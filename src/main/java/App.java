import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static MenuService ms = new MenuService();
    private static List<Menu> menus = new ArrayList();

    static {
        menus.add(new Menu("Chicken", 2.0, 900, false));
        menus.add(new Menu("Apple pie", 0.70, 200, false));
        menus.add(new Menu("Pasta", 0.50, 300, true));
        menus.add(new Menu("Musaka", 1.0, 700, false));
        menus.add(new Menu("Hamburger", 0.1, 100, false));
        menus.add(new Menu("Grill Meal", 2.5, 1000, true));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            ms.findAll();
            while (true) {
                System.out.println("Press[1-5]:");
                System.out.println("1: Hardcode DB");
                System.out.println("2: Insert to table");
                System.out.println("3: Costs from-to");
                System.out.println("4: Only discount");
                System.out.println("5: Random group with summ weight 1000g.");
                System.out.print("-> ");
                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        ms.deleteAll();
                        for (Menu m : menus)
                            ms.persist(m);
                        break;
                    case "2":
                        insertData(sc);
                        break;
                    case "3":
                        costsFromTo(sc);
                        break;
                    case "4":
                        onlyDiscount();
                        break;
                    case "5":
                        randomGroupOneKg();
                        break;
                    default:
                        return;
                }
            }

        } finally {
            sc.close();
            System.exit(0);
        }
    }

    private static void insertData(Scanner sc){
        Menu m = new Menu();
        System.out.println("Name dish:");
        m.setName(sc.nextLine());
        System.out.println("Price(double)(COMMA):");
        m.setPrice(sc.nextDouble());
        System.out.println("Weight(1000 = 1kg):");
        m.setWeight(sc.nextInt());
        System.out.println("Discount(true/false):");
        m.setDiscount(sc.nextBoolean());
        ms.persist(m);
    }
    private static void costsFromTo(Scanner sc){
        System.out.println("From price(double)(COMMA):");
        Double from = sc.nextDouble();
        System.out.println("To price(double)(COMMA):");
        Double to = sc.nextDouble();
        for (Menu m:costsFromTo(from,to)) {
                System.out.println(m);
        }
    }
    private static List<Menu> costsFromTo(Double from, Double to){
        List<Menu> res = new ArrayList<>();
        List<Menu> listTemp = ms.findAll();
        for (Menu m:listTemp) {
            if(m.getPrice() > from & m.getPrice() < to)
                res.add(m);
        }
        return res;
    }

    private static void onlyDiscount(){
        List<Menu> listTemp = ms.findAll();
        for (Menu m:listTemp) {
            if(m.getDiscount()==true)
                System.out.println(m);
        }
    }


    private static void randomGroupOneKg(){
        Integer to = 1000;
        List<Menu> listTemp=archGroup(ms.findAll(),null,to);
        List<Menu> listRes=new ArrayList<>();
        int rnd = 0;
        Menu techMenu=null;
        boolean check=false;
        while(true) {
            check=false;
            rnd = (int)(Math.random() * (listTemp.size()));
            if(rnd==listTemp.size())rnd=rnd-1;
            techMenu=listTemp.get(rnd);
            listRes.add(techMenu);
            to-=techMenu.getWeight();
            if(to==0)break;
            listTemp=archGroup(listTemp,techMenu,to);
            for(Menu l : listTemp){
                if(l.getWeight()<=to)
                    check=true;
            }
            if(!check)break;
        }

        for(Menu r:listRes) {
            System.out.println(r);
        }
    }
    private static List<Menu> archGroup(List<Menu> menus, Menu m, Integer to){
        List<Menu> listRes= new ArrayList<>();
        for(Menu i:menus) {
            if(i.getWeight()<=to)
                listRes.add(i);
        }
        if(m!=null)
            if (m.getWeight() <= to)
                listRes.remove(m);

        return listRes;
    }


}
