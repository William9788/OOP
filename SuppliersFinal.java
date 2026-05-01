import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class SuppliersFinal {
private TreeSet<PharmaSupplier> sup;

public SuppliersFinal() {
sup = new TreeSet<>(new Comparator<PharmaSupplier>() {
public int compare(PharmaSupplier s1, PharmaSupplier s2) {
if (s1 == s2) {
return 0;
}
if (s1 == null) {
return -1;
}
if (s2 == null) {
return 1;
}
return s1.getSupplierName().compareToIgnoreCase(s2.getSupplierName());
}
});
}

public boolean add(PharmaSupplier supplier) {
if (supplier == null || supplier.getSupplierName() == null || supplier.getSupplierName().trim().isEmpty()) {
return false;
}
return sup.add(supplier);
}

public boolean add(String fileName) {
if (fileName == null || fileName.trim().isEmpty()) {
return false;
}
boolean addedAny = false;
try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
String line;
while ((line = br.readLine()) != null) {
PharmaSupplier supplier = parseSupplier(line);
if (supplier != null && add(supplier)) {
addedAny = true;
}
}
}
catch (IOException e) {
return false;
}
return addedAny;
}

private PharmaSupplier parseSupplier(String line) {
if (line == null || line.trim().isEmpty()) {
return null;
}
String[] part = line.split(",", -1);
try {
if (part.length == 4) {
return new PharmaSupplier(part[0].trim(), part[1].trim(),
Double.parseDouble(part[2].trim()), Double.parseDouble(part[3].trim()));
}
if (part.length == 6) {
return new DiscountPharmaSupplier(part[0].trim(), part[1].trim(),
Double.parseDouble(part[2].trim()), Double.parseDouble(part[3].trim()),
Double.parseDouble(part[4].trim()), Double.parseDouble(part[5].trim()));
}
if (part.length == 5 && part[0].trim().equalsIgnoreCase("normal")) {
return new PharmaSupplier(part[1].trim(), part[2].trim(),
Double.parseDouble(part[3].trim()), Double.parseDouble(part[4].trim()));
}
if (part.length == 7 && part[0].trim().equalsIgnoreCase("discount")) {
return new DiscountPharmaSupplier(part[1].trim(), part[2].trim(),
Double.parseDouble(part[3].trim()), Double.parseDouble(part[4].trim()),
Double.parseDouble(part[5].trim()), Double.parseDouble(part[6].trim()));
}
}
catch (NumberFormatException e) {
return null;
}
return null;
}

public PharmaSupplier findSupplier(String supplierName) {
if (supplierName == null) {
return null;
}
for (PharmaSupplier supplier : sup) {
if (supplier.getSupplierName().equalsIgnoreCase(supplierName)) {
return supplier;
}
}
return null;
}

public void displayAllName() {
for (PharmaSupplier supplier : sup) {
System.out.println(supplier);
}
}

public void displayAllNameDescending() {
Iterator<PharmaSupplier> it = sup.descendingIterator();
while (it.hasNext()) {
System.out.println(it.next());
}
}

public void displayAllAddress() {
ArrayList<PharmaSupplier> sorted = new ArrayList<>(sup);
Collections.sort(sorted, new Comparator<PharmaSupplier>() {
public int compare(PharmaSupplier s1, PharmaSupplier s2) {
int addressCompare = s1.getSupplierAddress().compareToIgnoreCase(s2.getSupplierAddress());
if (addressCompare != 0) {
return addressCompare;
}
return s1.getSupplierName().compareToIgnoreCase(s2.getSupplierName());
}
});
for (PharmaSupplier supplier : sorted) {
System.out.println(supplier);
}
}

public int removeSupplier(String address) {
if (address == null) {
return 0;
}
int count = 0;
Iterator<PharmaSupplier> it = sup.iterator();
while (it.hasNext()) {
PharmaSupplier supplier = it.next();
if (supplier.getSupplierAddress().equalsIgnoreCase(address)) {
it.remove();
count++;
}
}
return count;
}

public boolean removeSupplierByName(String supplierName) {
PharmaSupplier supplier = findSupplier(supplierName);
if (supplier == null) {
return false;
}
return sup.remove(supplier);
}

public boolean increaseOwed(String supplierName, double value) {
PharmaSupplier supplier = findSupplier(supplierName);
if (supplier == null) {
return false;
}
return supplier.increaseAmountOwed(value);
}

public void displayNameAndAmountOwedIncreasing() {
ArrayList<PharmaSupplier> sorted = new ArrayList<>(sup);
Collections.sort(sorted, new Comparator<PharmaSupplier>() {
public int compare(PharmaSupplier s1, PharmaSupplier s2) {
int owedCompare = Double.compare(s1.getAmountOwed(), s2.getAmountOwed());
if (owedCompare != 0) {
return owedCompare;
}
return s1.getSupplierName().compareToIgnoreCase(s2.getSupplierName());
}
});
for (PharmaSupplier supplier : sorted) {
System.out.println(supplier.getSupplierName() + " " + String.format("%.2f", supplier.getAmountOwed()));
}
}

public TreeSet<PharmaSupplier> getSuppliers() {
TreeSet<PharmaSupplier> copy = new TreeSet<>(sup.comparator());
copy.addAll(sup);
return copy;
}
}
