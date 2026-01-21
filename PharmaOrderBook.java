import java.util.ArrayList;
public class PharmaOrderBook {
    private ArrayList<PharmaOrder> orders;
    public PharmaOrderBook(){
        orders = new ArrayList();
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
    @Override
    public String toString(){
        return "PharmaOrderBook contains"+orders.size()+"order.";
    }
}
