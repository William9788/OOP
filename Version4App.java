import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
public class Version4App {
private static PharmaStock stock = new PharmaStock();
private static PharmaOrderBook orderBook = new PharmaOrderBook();
private static PharmaDeliveries deliveries = new PharmaDeliveries();
private static SuppliersFinal suppliers = new SuppliersFinal();
private static Scanner keyboard = new Scanner(System.in);
public static void main(String[] args) {
boolean finished = false;
while (!finished) {
displayMenu();
int choice = readInt("Choice: ");
switch (choice) {
case 1:
addSupplier();
break;
case 2:
addSuppliersFromFile();
break;
case 3:
changeSupplierAddress();
break;
case 4:
deleteSupplier();
break;
case 5:
displaySupplierProducts();
break;
case 6:
suppliers.displayAllNameDescending();
break;
case 7:
suppliers.displayNameAndAmountOwedIncreasing();
break;
case 8:
addMedicine();
break;
case 9:
deleteMedicine();
break;
case 10:
decreaseQuantityInStock();
break;
case 11:
addDelivery();
break;
case 12:
displayDeliveriesByDate();
break;
case 13:
displayDeliveryValue();
break;
case 14:
stock.displayByName();
break;
case 15:
stock.displayByQty();
break;
case 16:
createOrders();
break;
case 0:
finished = true;
break;
default:
System.out.println("Invalid choice.");
}
}
}
private static void displayMenu() {
System.out.println("\n1 Add supplier");
System.out.println("2 Add suppliers from file");
System.out.println("3 Change supplier address");
System.out.println("4 Delete supplier");
System.out.println("5 Display supplier products");
System.out.println("6 Display suppliers by descending name");
System.out.println("7 Display supplier names and amount owed");
System.out.println("8 Add medicine");
System.out.println("9 Delete medicine");
System.out.println("10 Decrease quantity in stock");
System.out.println("11 Add delivery");
System.out.println("12 Display medicines delivered on date");
System.out.println("13 Display delivery value on date");
System.out.println("14 Display medicines by name");
System.out.println("15 Display medicines by quantity");
System.out.println("16 Create stock orders");
System.out.println("0 Exit");
}



private static void addSupplier() {
String type = readLine("Normal or discount supplier? ");
String name = readRequiredLine("Supplier name: ");
String address = readRequiredLine("Supplier address: ");
double amountOwed = readDouble("Amount owed: ");
double creditLimit = readDouble("Credit limit: ");
PharmaSupplier supplier;
if (type.equalsIgnoreCase("discount")) {
double orderValue = readDouble("Order value for discount: ");
double percentage = readDouble("Discount percentage: ");
supplier = new DiscountPharmaSupplier(name, address, amountOwed, creditLimit, orderValue, percentage);
}
else {
supplier = new PharmaSupplier(name, address, amountOwed, creditLimit);
}
System.out.println(suppliers.add(supplier) ? "Supplier added." : "Supplier not added.");
}


private static void addSuppliersFromFile() {
String fileName = readRequiredLine("File name: ");
System.out.println(suppliers.add(fileName) ? "Suppliers added." : "No suppliers added.");
}
private static void changeSupplierAddress() {
PharmaSupplier supplier = suppliers.findSupplier(readRequiredLine("Supplier name: "));
if (supplier == null) {
System.out.println("Supplier not found.");
return;
}
System.out.println(supplier);
String address = readRequiredLine("New address: ");
System.out.println(supplier.setSupplierAddress(address) ? "Address changed." : "Address not changed.");
}
private static void deleteSupplier() {
String supplierName = readRequiredLine("Supplier name: ");
PharmaSupplier supplier = suppliers.findSupplier(supplierName);
if (supplier == null) {
System.out.println("Supplier not found.");
return;
}
System.out.println(supplier);
displaySupplierProducts(supplierName);
String confirm = readLine("Delete this supplier? yes/no: ");
if (!confirm.equalsIgnoreCase("yes")) {
System.out.println("Supplier not deleted.");
return;
}
if (suppliers.removeSupplierByName(supplierName)) {
deliveries.deleteDeliveries(supplierName);
for (Medicine medicine : stock.getProducts()) {
if (medicine.getSupplierRef() != null &&
medicine.getSupplierRef().getSupplierName().equalsIgnoreCase(supplierName)) {
medicine.setSupplierRef(null);
}
}
System.out.println("Supplier deleted.");
}
}
private static void displaySupplierProducts() {
displaySupplierProducts(readRequiredLine("Supplier name: "));
}
private static void displaySupplierProducts(String supplierName) {
Map<String, ArrayList<Medicine>> supplierMap = stock.createSupplierMap();
ArrayList<Medicine> medicines = supplierMap.get(supplierName);
if (medicines == null || medicines.isEmpty()) {
System.out.println("No medicines found for this supplier.");
return;
}
for (Medicine medicine : medicines) {
System.out.println(medicine);
}
}
private static void addMedicine() {
String supplierName = readRequiredLine("Supplier name: ");
PharmaSupplier supplier = suppliers.findSupplier(supplierName);
if (supplier == null) {
System.out.println("Supplier not found.");
return;
}
String name = readRequiredLine("Medicine name: ");
String code = readRequiredLine("Medicine code: ");
int quantity = readInt("Quantity in stock: ");
int reorderLevel = readInt("Reorder level: ");
int reorderQuantity = readInt("Reorder quantity: ");
double unitCost = readDouble("Unit cost price: ");
Medicine medicine = new Medicine(name, code, quantity, reorderLevel, reorderQuantity, unitCost, supplier, false);
System.out.println(stock.add(medicine) ? "Medicine added." : "Medicine not added.");
}

private static void deleteMedicine() {
String name = readRequiredLine("Medicine name: ");
Medicine medicine = stock.findMedicine(name);
if (medicine == null) {
System.out.println("Medicine not found.");
return;
}
System.out.println(medicine);
System.out.println(stock.deleteMedicine(name) ? "Medicine deleted." : "Medicine not deleted.");
}

private static void decreaseQuantityInStock() {
Medicine medicine = stock.findMedicine(readRequiredLine("Medicine name: "));
if (medicine == null) {
System.out.println("Medicine not found.");
return;
}
System.out.println(medicine);
int amount = readInt("Decrease by: ");
System.out.println(medicine.decreaseStock(amount) ? "Quantity decreased." : "Quantity not decreased.");
}

private static void addDelivery() {
Medicine medicine = stock.findMedicine(readRequiredLine("Medicine name: "));
if (medicine == null) {
System.out.println("Medicine not found.");
return;
}
int quantity = readInt("Quantity delivered: ");
MedicineDelivery delivery = new MedicineDelivery(medicine, LocalDate.now(), quantity);
deliveries.addDeliveries(delivery);
medicine.increaseStock(quantity);
if (medicine.getSupplierRef() != null) {
medicine.getSupplierRef().increaseAmountOwed(delivery.calculateDeliveryCost());
}
medicine.setOnOrderFlag(false);
System.out.println("Delivery cost: " + String.format("%.2f", delivery.calculateDeliveryCost()));
}

private static void displayDeliveriesByDate() {
LocalDate date = readDate("Delivery date yyyy-mm-dd: ");
for (MedicineDelivery delivery : deliveries.findAllDeliveries(date)) {
System.out.println(delivery);
}
}

private static void displayDeliveryValue() {
LocalDate date = readDate("Delivery date yyyy-mm-dd: ");
double total = 0;
for (MedicineDelivery delivery : deliveries.findAllDeliveries(date)) {
total += delivery.calculateDeliveryCost();
}
System.out.println("Total delivery value: " + String.format("%.2f", total));
}

private static void createOrders() {
ArrayList<PharmaOrder> orders = stock.createOrders();
orderBook.add(orders);
for (PharmaOrder order : orders) {
System.out.println(order);
System.out.println("Order cost: " + String.format("%.2f", orderBook.calcOrderCost(order.getUniqueId())));
}
}

private static String readLine(String prompt) {
System.out.print(prompt);
return keyboard.nextLine().trim();
}

private static String readRequiredLine(String prompt) {
String value = readLine(prompt);
while (value.isEmpty()) {
System.out.println("Value required.");
value = readLine(prompt);
}
return value;
}

private static int readInt(String prompt) {
while (true) {
try {
int value = Integer.parseInt(readLine(prompt));
if (value >= 0) {
return value;
}
}
catch (NumberFormatException e) {
}
System.out.println("Enter a whole number greater than or equal to zero.");
}
}

private static double readDouble(String prompt) {
while (true) {
try {
double value = Double.parseDouble(readLine(prompt));
if (value >= 0) {
return value;
}
}
catch (NumberFormatException e) {
}
System.out.println("Enter a number greater than or equal to zero.");
}
}

private static LocalDate readDate(String prompt) {
while (true) {
try {
return LocalDate.parse(readLine(prompt));
}
catch (DateTimeParseException e) {
System.out.println("Enter the date in yyyy-mm-dd format.");
}
}
}
}
