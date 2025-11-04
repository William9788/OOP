import java.time.LocalDate;

public class MedicineDelivery {
private Medicine medicineRef;
private LocalDate deliveryDate;
private int quantityDelivered;

/**
 * No-argument constructor with defaults.
 */
public MedicineDelivery() {
this(null, LocalDate.now(), 0);
}

/**
 * Constructor that accepts all fields.
 *
 * @param medicineRef   reference to the medicine being delivered
 * @param deliveryDate  delivery date
 * @param quantityDelivered quantity that was delivered
 */
public MedicineDelivery(Medicine medicineRef, LocalDate deliveryDate, int quantityDelivered) {
this.medicineRef = medicineRef;
this.deliveryDate = deliveryDate;
this.quantityDelivered = Math.max(quantityDelivered, 0);
}

public Medicine getMedicineRef() {
return medicineRef;
}

public boolean setMedicineRef(Medicine medicineRef) {
if (medicineRef == null) {
return false;
}
this.medicineRef = medicineRef;
return true;
}

public LocalDate getDeliveryDate() {
return deliveryDate;
}

public boolean setDeliveryDate(LocalDate deliveryDate) {
if (deliveryDate == null) {
return false;
}
this.deliveryDate = deliveryDate;
return true;
}

public int getQuantityDelivered() {
return quantityDelivered;
}

public boolean setQuantityDelivered(int quantityDelivered) {
if (quantityDelivered < 0) {
return false;
}
this.quantityDelivered = quantityDelivered;
return true;
}

/**
 * Calculate the delivery cost using the medicine's unit cost.
 *
 * @return cost of the delivery
 */
public double calculateDeliveryCost() {
if (medicineRef == null) {
return 0.0;
}
return quantityDelivered * medicineRef.getUnitCostPrice();
}

@Override
public String toString() {
return "MedicineDelivery{" +
"medicineRef=" + (medicineRef != null ? medicineRef.getMedicineCode() : "None") +
", deliveryDate=" + deliveryDate +
", quantityDelivered=" + quantityDelivered +
", deliveryCost=" + String.format("%.2f", calculateDeliveryCost()) +
'}';
}
}
