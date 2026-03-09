import java.util.ArrayList;
public class PharmaSupplierCollection {
private ArrayList<PharmaSupplier> suppliers;
    public boolean add(PharmaSupplier supplier){
        if(supplier==null){
            return false;
        }
        suppliers.add(supplier);
        return true;
    }

}
