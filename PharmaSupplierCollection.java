import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class PharmaSupplierCollection {
private ArrayList<PharmaSupplier> suppliers;
    public boolean add(PharmaSupplier supplier){
        if(supplier==null){
            return false;
        }
        suppliers.add(supplier);
        return true;
    }
    public PharmaSupplierCollection() {
        suppliers = new ArrayList<>();
    }
    public PharmaSupplierCollection(String fileName) {
        this();
        add(fileName);
    }
    public boolean add(String fileName){
        if(fileName ==null||fileName.trim().isEmpty()){
        return false;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName));
            PrintWriter pw = new PrintWriter(new java.io.FileWriter("invalidSupplier.txt"))) {
            String line;
            while((line=br.readLine())!=null){
            String[] part = line.split(",", -1);
            if(part.length!=4){
            writeInvalidRecord(pw, line, "Incorrect number of fields");
            continue;
    }
            String name = part[0].trim();
            String address = part[1].trim();
            String amountText = part[2].trim();
            String creditText = part[3].trim();
            if (name.isEmpty() || address.isEmpty() || amountText.isEmpty() || creditText.isEmpty()) {
            writeInvalidRecord(pw, line, "Missing fields");
            continue;
}
try {
    double amountOwed = Double.parseDouble(amountText);
    double creditLimit = Double.parseDouble(creditText);
    if (amountOwed < 0 || creditLimit < 0) {
    writeInvalidRecord(pw, line, "Negative values are not allowed");
    continue;
    }
    if (amountOwed > creditLimit) {
    writeInvalidRecord(pw, line, "amountOwed cannot be greater than creditLimit");
    continue;
    }
    PharmaSupplier supplier = new PharmaSupplier(name,address,amountOwed,creditLimit);
    add(supplier);
    }
    catch (NumberFormatException e) {
        writeInvalidRecord(pw, line, "Non-numeric value for amountOwed or creditLimit");
                    }
            }
        }
        catch (IOException e){
            return false;
        }
        return true;
    }
    private void writeInvalidRecord(PrintWriter pw, String record, String reason) {
        pw.println("Invalid record: " + record);
        pw.println("Reason: " + reason);
    }
    public void displayAll(){
        Collections.sort(suppliers,new Comparator<PharmaSupplier>(){
            public int compare(PharmaSupplier s1,PharmaSupplier s2){
                return s1.getSupplierName().compareToIgnoreCase(s2.getSupplierName());
            }
        });
        for(PharmaSupplier s:suppliers){
            System.out.println(s);
        }
    }
}
