import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Version2App {
    public static void main(String[] args){
        PharmaSupplier s1= new PharmaSupplier("Sean Mcnamara","Dundalk",0,5000);
        PharmaSupplier s2= new PharmaSupplier("Shane Williams","Dundalk",0,5000);
        Medicine m1 = new Medicine("Paracetamol", "M001", 20, 10, 50, 1.5, s1, false);
        Medicine m2 = new Medicine("Ibuprofen", "M002", 15, 10, 40, 2.0, s1, false);
        Medicine m3 = new Medicine("Aspirin", "M003", 30, 20, 60, 1.2, s2, false);
        Medicine m4 = new Medicine("Vitamin C", "M004", 5, 10, 50, 0.8, s2, false);
        Medicine m5 = new Medicine("Antibiotic", "M005", 8, 15, 30, 5.0, s1, false);
        Medicine m6 = new Medicine("Cough Syrup", "M006", 25, 20, 40, 3.5, s2, false);
        Medicine m7 = new Medicine("Insulin", "M007", 3, 5, 20, 20.0, s1, false);
        PharmaStock stock=new PharmaStock();
        stock.add(m1);
        stock.add(m2);
        stock.add(m3);
        stock.add(m4);
        stock.add(m5);
        stock.add(m6);
        stock.add(m7);
        PharmaDeliveries deliveries = new PharmaDeliveries();
        MedicineDelivery d1 =new MedicineDelivery(m1, LocalDate.now().minusDays(1), 10);
        MedicineDelivery d2 =new MedicineDelivery(m2, LocalDate.now(), 15);
        MedicineDelivery d3 =new MedicineDelivery(m3, LocalDate.now(), 20);
        deliveries.addDeliveries(d1);
        deliveries.addDeliveries(d2);
        deliveries.addDeliveries(d3);
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter medicine name delivered");
        String mN = keyboard.nextLine();
        System.out.println("Enter quantity delivered");
        int qD=keyboard.nextInt();
        Medicine deliveredMed= stock.findMedicine(mN);
        if(deliveredMed!=null){
            deliveredMed.increaseStock(qD);
            double cost = qD*deliveredMed.getUnitCostPrice();
            deliveredMed.getSupplierRef().increaseAmountOwed(cost);
            deliveredMed.setOnOrderFlag(false);
            MedicineDelivery newDelivery= new MedicineDelivery(deliveredMed,LocalDate.now(),qD);
            deliveries.addDeliveries(newDelivery);
            System.out.println("Delivery recorded!");
        }
        else{
            System.out.println("Not found!");
        }
        PharmaOrderBook orderBook = new PharmaOrderBook();
        ArrayList<PharmaOrder> ordersToSend = stock.createOrders();
        orderBook.add(ordersToSend);
        int deleted = deliveries.deleteDeliveries("Sean Mcnamara");
        System.out.println("Deleted " + deleted + " deliveries from Sean Mcnamara");
        Medicine cheapest = stock.findCheapest();
        System.out.println("Cheapest medicine:");
        System.out.println(cheapest);
        String expensiveName = stock.findExpensive();
        Medicine expensive = stock.findMedicine(expensiveName);
        System.out.println("Most expensive medicine:");
        System.out.println("Name: " + expensive.getMedicineName());
        System.out.println("Quantity in stock: " + expensive.getQuantityInStock());
        System.out.println("Supplier: " +expensive.getSupplierRef().getSupplierName());
        System.out.println("All deliveries of Paracetamol：");
        ArrayList<MedicineDelivery>paraDeliverys= deliveries.findAllDeliveries("Paracetamol");
        for(MedicineDelivery m:paraDeliverys){
            System.out.println(m);
        }
    }
}
