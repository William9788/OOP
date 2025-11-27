public class PharmaOrder{
private String OrderDate;
private Medicine medicineRef;
private int quantity;
private String UniqueId;
public PharmaOrder(String OrderDate,Medicine medicineRef,int quantity,String UniqueId){
this.OrderDate="UNKNOWN";
this.medicineRef=new Medicine();
this.quantity=0;
this.UniqueId="UNSET";
setOrderDate(OrderDate);
setquantity(quantity);
setUniqueId(UniqueId);
setmedicineRef(medicineRef);
}
public Medicine getmedicineRef(Medicine medicineRef){
return medicineRef;
}
public String getOrderDate(String OrderDate){
return OrderDate;
}
public int getquantity(int quantity){
return quantity;
}
public String getUniqueId(String UniqueId){
    return UniqueId;
}
public boolean setmedicineRef(Medicine medicineRef){
if(medicineRef==null){
return false;
}
this.medicineRef=medicineRef;
return true;
}
public boolean setOrderDate(String OrderDate){
if(OrderDate==null||OrderDate.trim().isEmpty()){
return false;
}
this.OrderDate=OrderDate;
return true;
}
public boolean setquantity(int quantity){
if(quantity<0){
return false;
}
this.quantity=quantity;
return true;
}
public boolean setUniqueId(String UniqueId){
if(UniqueId==null||UniqueId.trim().isEmpty()){
return false;
}
this.UniqueId=UniqueId;
return true;
}
@Override
public String toString(){
return 
"MedicineRef: "+medicineRef+
", Order date: "+OrderDate+
", Unique Id: "+UniqueId+
", Quantity: "+quantity;
}
}