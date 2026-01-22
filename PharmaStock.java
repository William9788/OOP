import java.util.ArrayList;
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
public String findCheapest(){
    if(products.isEmpty()){
        return null;
    }
    Medicine cheapest = products.get(0);
    for(Medicine m:products){
        if(m.getUnitCostPrice()<cheapest.getUnitCostPrice()){
            cheapest=m;
        }
    }
    return cheapest.getMedicineCode();
}
}
