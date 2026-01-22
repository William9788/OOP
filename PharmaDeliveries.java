import java.time.LocalDate;
import java.util.ArrayList;
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
}
