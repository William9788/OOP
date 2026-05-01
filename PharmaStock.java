import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
public class PharmaStock {
    private ArrayList<Medicine> products;
public PharmaStock(){
    products = new ArrayList<>();
}
public boolean add(Medicine obj){
    if(obj==null){
        return false;
    }
    products.add(obj);
    return true;
}
public Medicine findMedicine(String name){
    if(name==null){
        return null;
    }
    for(Medicine m: products){
        if(m.getMedicineName().equalsIgnoreCase(name)){
            return m;
        }
    }
    return null;
}
public boolean deleteMedicine(String name){
    Medicine medicine = findMedicine(name);
    if(medicine==null){
        return false;
    }
    return products.remove(medicine);
}
public ArrayList<Medicine> getProducts(){
    return new ArrayList<>(products);
}
public String findExpensive(){
    if(products.isEmpty()){
        return null;
    }
    Medicine expensive = products.get(0);
    for(Medicine m:products){
        if(m.getUnitCostPrice()>expensive.getUnitCostPrice()){
            expensive=m;
        }
    }
    return expensive.getMedicineName();
}
public Medicine findCheapest(){
    if(products.isEmpty()){
        return null;
    }
    Medicine cheapest = products.get(0);
    for(Medicine m:products){
        if(m.getUnitCostPrice()<cheapest.getUnitCostPrice()){
            cheapest=m;
        }
    }
    return cheapest;
}
public void displayByName(){
    ArrayList<Medicine> sorted = new ArrayList<>(products);
    Collections.sort(sorted, new Comparator<Medicine>(){
        public int compare(Medicine m1, Medicine m2){
            return m1.getMedicineName().compareToIgnoreCase(m2.getMedicineName());
        }
    });
    for(Medicine m:sorted){
        System.out.println(m);
    }
}
public void displayByQty(){
    ArrayList<Medicine> sorted = new ArrayList<>(products);
    Collections.sort(sorted, new Comparator<Medicine>(){
        public int compare(Medicine m1, Medicine m2){
            return Integer.compare(m2.getQuantityInStock(), m1.getQuantityInStock());
        }
    });
    for(Medicine m:sorted){
        System.out.println(m);
    }
}
public Map<String, ArrayList<Medicine>> createSupplierMap(){
    Map<String, ArrayList<Medicine>> supplierMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    for(Medicine m:products){
        PharmaSupplier supplier = m.getSupplierRef();
        String supplierName = supplier==null ? "No Supplier" : supplier.getSupplierName();
        if(!supplierMap.containsKey(supplierName)){
            supplierMap.put(supplierName, new ArrayList<Medicine>());
        }
        supplierMap.get(supplierName).add(m);
    }
    return supplierMap;
}
public ArrayList<PharmaOrder> createOrders(){
    ArrayList<PharmaOrder> orders= new ArrayList<>();
    for(Medicine m:products){
        if(!m.isOnOrderFlag()&&m.getQuantityInStock()<=m.getReorderLevel()){
            PharmaOrder order = new PharmaOrder(
                "AUTO",
                m,
                m.getReorderQuantity(),
                "AUTO"+m.getMedicineCode()
            );
            orders.add(order);
            m.setOnOrderFlag(true);

        }
    }
    return orders;
}
}
