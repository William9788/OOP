import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Version3App {
    public static void displayInvalidRecords(){
        try(BufferedReader br = new BufferedReader(new FileReader("InvalidSupplier.txt"))){
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
        }catch(IOException e){
            System.out.println("Error reading");
        }
    }
    public static void main(String [] args){
        PharmaSupplierCollection g1 = new PharmaSupplierCollection();
        g1.add("suppliers.txt");
        System.out.println("All valid suppliers in group1:");
        g1.displayAll();
        System.out.println("Invalid record:");
        displayInvalidRecords();
        g1.storeSuppliers("suppliers_out.txt");
        PharmaSupplierCollection g2 = new PharmaSupplierCollection();
        System.out.println("All suppliers i n group2:");
        g2.displayAll();
    }
}
