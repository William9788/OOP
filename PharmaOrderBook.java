import java.util.ArrayList;
public class PharmaOrderBook {
    private ArrayList<PharmaOrder> orders;
    public PharmaOrderBook(){
        orders = new ArrayList<>();
    }
    public boolean add(PharmaOrder order){
        if(order==null){
            return false;
        }
        orders.add(order);
        return true;
    }
    public boolean add(ArrayList<PharmaOrder> orderList){
        if(orderList== null||orderList.isEmpty()){
            return false;
        }
        orders.addAll(orderList);
        return true;
    }
    public PharmaOrder findOrder(String orderId){
        if(orderId==null){
            return null;
        }
        for(PharmaOrder order:orders){
            if(order.getUniqueId().equalsIgnoreCase(orderId)){
                return order;
            }
        }
        return null;
    }
    public double calcOrderCost(String orderId){
        PharmaOrder order = findOrder(orderId);
        if(order==null||order.getmedicineRef()==null){
            return -1;
        }
        Medicine medicine = order.getmedicineRef();
        double cost = order.getquantity()*medicine.getUnitCostPrice();
        PharmaSupplier supplier = medicine.getSupplierRef();
        if(supplier instanceof DiscountPharmaSupplier){
            cost = ((DiscountPharmaSupplier)supplier).applyDiscount(cost);
        }
        return cost;
    }
    @Override
    public String toString(){
        return "PharmaOrderBook contains"+orders.size()+"order.";
    }
}
