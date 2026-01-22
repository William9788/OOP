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

}
