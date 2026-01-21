public class PharmaOrder{
private String orderDate;
private Medicine medicineRef;
private int quantity;
private String uniqueId;
public PharmaOrder(){
this.orderDate="UNKNOWN";
this.medicineRef=new Medicine();
this.quantity=0;
this.uniqueId="UNSET";
}
public PharmaOrder(String orderDate,Medicine medicineRef,int quantity,String uniqueId){
this.orderDate="UNKNOWN";
this.medicineRef=new Medicine();
this.quantity=0;
this.uniqueId="UNSET";
setorderDate(orderDate);
setquantity(quantity);
setuniqueId(uniqueId);
setmedicineRef(medicineRef);
}
public Medicine getmedicineRef(){
return medicineRef;
}
public String getOrderDate(){
return orderDate;
}
public int getquantity(){
return quantity;
}
public String getUniqueId(){
    return uniqueId;
}
public boolean setmedicineRef(Medicine medicineRef){
if(medicineRef==null){
return false;
}
this.medicineRef=medicineRef;
return true;
}
public boolean setorderDate(String OrderDate){
if(OrderDate==null||OrderDate.trim().isEmpty()){
return false;
}
this.orderDate=OrderDate;
return true;
}
public boolean setquantity(int quantity){
if(quantity<0){
return false;
}
this.quantity=quantity;
return true;
}
public boolean setuniqueId(String UniqueId){
if(UniqueId==null||UniqueId.trim().isEmpty()){
return false;
}
this.uniqueId=UniqueId;
return true;
} 
@Override
public String toString(){
return 
"MedicineRef: "+medicineRef+
", Order date: "+orderDate+
", Unique Id: "+uniqueId+
", Quantity: "+quantity;
}
}