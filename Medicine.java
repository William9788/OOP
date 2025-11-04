public class Medicine {
private String medicineName;
private String medicineCode;
private int quantityInStock;
private int reorderLevel;
private int reorderQuantity;
private double unitCostPrice;
private PharmaSupplier supplierRef;
private boolean onOrderFlag;

/**
 * No-argument constructor that sets default values.
 */
public Medicine() {
this("Unnamed Medicine", "UNKNOWN", 0, 0, 0, 0.0, null, false);
}

/**
 * Constructor that accepts all fields.
 *
 * @param medicineName name of the medicine
 * @param medicineCode unique code
 * @param quantityInStock  current stock quantity
 * @param reorderLevel threshold for reordering
 * @param reorderQuantity  quantity to reorder
 * @param unitCostPricecost price per unit
 * @param supplierRef  reference to the supplier
 * @param onOrderFlag  indicates if more stock is currently on order
 */
public Medicine(String medicineName, String medicineCode, int quantityInStock, int reorderLevel,
int reorderQuantity, double unitCostPrice, PharmaSupplier supplierRef, boolean onOrderFlag) {
this.medicineName = medicineName;
this.medicineCode = medicineCode;
this.quantityInStock = Math.max(quantityInStock, 0);
this.reorderLevel = Math.max(reorderLevel, 0);
this.reorderQuantity = Math.max(reorderQuantity, 0);
this.unitCostPrice = Math.max(unitCostPrice, 0.0);
this.supplierRef = supplierRef;
this.onOrderFlag = onOrderFlag;
}

public String getMedicineName() {
return medicineName;
}

public boolean setMedicineName(String medicineName) {
if (medicineName == null || medicineName.trim().isEmpty()) {
return false;
}
this.medicineName = medicineName.trim();
return true;
}

public String getMedicineCode() {
return medicineCode;
}

public boolean setMedicineCode(String medicineCode) {
if (medicineCode == null || medicineCode.trim().isEmpty()) {
return false;
}
this.medicineCode = medicineCode.trim();
return true;
}

public int getQuantityInStock() {
return quantityInStock;
}

public boolean setQuantityInStock(int quantityInStock) {
if (quantityInStock < 0) {
return false;
}
this.quantityInStock = quantityInStock;
return true;
}

public int getReorderLevel() {
return reorderLevel;
}

public boolean setReorderLevel(int reorderLevel) {
if (reorderLevel < 0) {
return false;
}
this.reorderLevel = reorderLevel;
return true;
}

public int getReorderQuantity() {
return reorderQuantity;
}

public boolean setReorderQuantity(int reorderQuantity) {
if (reorderQuantity < 0) {
return false;
}
this.reorderQuantity = reorderQuantity;
return true;
}

public double getUnitCostPrice() {
return unitCostPrice;
}

public boolean setUnitCostPrice(double unitCostPrice) {
if (unitCostPrice < 0) {
return false;
}
this.unitCostPrice = unitCostPrice;
return true;
}

public PharmaSupplier getSupplierRef() {
return supplierRef;
}

public boolean setSupplierRef(PharmaSupplier supplierRef) {
if (supplierRef == null) {
return false;
}
this.supplierRef = supplierRef;
return true;
}

public boolean isOnOrderFlag() {
return onOrderFlag;
}

public boolean setOnOrderFlag(boolean onOrderFlag) {
this.onOrderFlag = onOrderFlag;
return true;
}

/**
 * Increase the quantity in stock by the supplied amount.
 *
 * @param amount quantity to add
 * @return true when stock was updated, false otherwise
 */
public boolean increaseStock(int amount) {
if (amount <= 0) {
return false;
}
quantityInStock += amount;
if (quantityInStock > 0) {
onOrderFlag = false;
}
return true;
}

/**
 * Decrease the quantity in stock by the supplied amount.
 *
 * @param amount quantity to remove
 * @return true when stock was updated, false otherwise
 */
public boolean decreaseStock(int amount) {
if (amount <= 0 || amount > quantityInStock) {
return false;
}
quantityInStock -= amount;
return true;
}

@Override
public String toString() {
return "Medicine{" +
"name='" + medicineName + '\'' +
", code='" + medicineCode + '\'' +
", quantityInStock=" + quantityInStock +
", reorderLevel=" + reorderLevel +
", reorderQuantity=" + reorderQuantity +
", unitCostPrice=" + String.format("%.2f", unitCostPrice) +
", supplierRef=" + (supplierRef != null ? supplierRef.getSupplierName() : "None") +
", onOrderFlag=" + onOrderFlag +
'}';
}
}
