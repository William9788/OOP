import java.time.LocalDate;

public class PharmaAppVersion1 {

public static void main(String[] args) {
// Create supplier objects
PharmaSupplier sup1 = new PharmaSupplier("Alpha Pharma", "12 Main Street, Springfield", 0.0, 20000.0);
PharmaSupplier sup2 = new PharmaSupplier("Beta Medical Supplies", "45 Elm Avenue, Lakeside", 1000.0, 15000.0);
PharmaSupplier sup3 = new PharmaSupplier("CareWell Distributors", "99 Oak Road, Hillview", 500.0, 12000.0);

// Create medicine objects referencing the suppliers
Medicine m1 = new Medicine("PainAway Tablets", "MED-001", 150, 50, 200, 5.50, sup1, false);
Medicine m2 = new Medicine("FluCure Syrup", "MED-002", 80, 30, 150, 8.20, sup1, true);
Medicine m3 = new Medicine("AllerFree Capsules", "MED-003", 60, 25, 100, 6.75, sup2, false);
Medicine m4 = new Medicine("GlucoCheck Strips", "MED-004", 40, 20, 120, 12.10, sup3, true);
Medicine m5 = new Medicine("HeartGuard Tablets", "MED-005", 200, 80, 250, 9.50, sup2, false);

// Create delivery objects
MedicineDelivery d1 = new MedicineDelivery(m1, LocalDate.of(2024, 3, 10), 75);
MedicineDelivery d2 = new MedicineDelivery(m2, LocalDate.of(2024, 3, 12), 120);
MedicineDelivery d3 = new MedicineDelivery(m4, LocalDate.of(2024, 3, 18), 90);

// Before and after demonstration for delivery d1
double amountOwedBefore = sup1.getAmountOwed();
int quantityBefore = m1.getQuantityInStock();

boolean deliveryProcessed = processDelivery(d1);

double amountOwedAfter = sup1.getAmountOwed();
int quantityAfter = m1.getQuantityInStock();

// Display information as per requirements
System.out.println("Delivery d1 cost: " + String.format("%.2f", d1.calculateDeliveryCost()));
System.out.println();
System.out.println("Before delivery d1:");
System.out.println("Supplier amount owed: " + String.format("%.2f", amountOwedBefore));
System.out.println("Medicine quantity in stock: " + quantityBefore);
System.out.println();
System.out.println("Processed delivery d1 successfully: " + deliveryProcessed);
System.out.println();
System.out.println("After delivery d1:");
System.out.println("Supplier amount owed: " + String.format("%.2f", amountOwedAfter));
System.out.println("Medicine quantity in stock: " + quantityAfter);
System.out.println();

// Process remaining deliveries to keep inventory up to date
processDelivery(d2);
processDelivery(d3);

System.out.println("Supplier summaries:");
System.out.println(sup1);
System.out.println(sup2);
System.out.println(sup3);
System.out.println();

System.out.println("Medicine summaries:");
System.out.println(m1);
System.out.println(m2);
System.out.println(m3);
System.out.println(m4);
System.out.println(m5);
}

/**
 * Apply delivery updates to both the medicine stock and the related supplier balance.
 *
 * @param delivery delivery to process
 * @return true when both stock and supplier balance were updated, false otherwise
 */
private static boolean processDelivery(MedicineDelivery delivery) {
if (delivery == null || delivery.getMedicineRef() == null) {
return false;
}

Medicine medicine = delivery.getMedicineRef();
PharmaSupplier supplier = medicine.getSupplierRef();

if (supplier == null) {
return false;
}

int quantityDelivered = delivery.getQuantityDelivered();

if (!medicine.increaseStock(quantityDelivered)) {
return false;
}

double deliveryCost = delivery.calculateDeliveryCost();

if (!supplier.increaseAmountOwed(deliveryCost)) {
// Roll back the stock increase if supplier update fails
medicine.decreaseStock(quantityDelivered);
return false;
}

return true;
}
}
