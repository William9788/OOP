import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
public class PharmaDeliveries {
    private ArrayList<MedicineDelivery> isComing;
public PharmaDeliveries(){
    isComing = new ArrayList<>();
}
public boolean addDeliveries(MedicineDelivery delivery){
    if(delivery==null){
        return false;
    }
    isComing.add(delivery);
    return true;
}
public ArrayList<MedicineDelivery> findAllDeliveries(LocalDate date){
ArrayList<MedicineDelivery> result = new ArrayList<>();
if(date==null){
    return result;
}
for(MedicineDelivery m :isComing){
    if(m.getDeliveryDate().equals(date)){
        result.add(m);
    }
}
return result;
}
public ArrayList<MedicineDelivery>findAllDeliveries(String medicineName){
    ArrayList<MedicineDelivery> result = new ArrayList<>();
    if(medicineName==null){
        return result;
    }
    for(MedicineDelivery m:isComing){
        if(m.getMedicineRef().getMedicineName().equalsIgnoreCase(medicineName)){
            result.add(m);
        }
    }
    return result;
}
public int deleteDeliveries(String supplierName){
    int count=0;
    if(supplierName==null){
        return count;
    }
    Iterator<MedicineDelivery> it = isComing.iterator();
    while(it.hasNext()){
        MedicineDelivery m =it.next();
        if(m.getMedicineRef().getSupplierRef().getSupplierName().equalsIgnoreCase(supplierName)){
            it.remove();
            count++;
        }
    }
    return count;
}
}
