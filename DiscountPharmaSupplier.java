public class DiscountPharmaSupplier extends PharmaSupplier {
private double orderValueForDiscount;
private double discountPercentage;

public DiscountPharmaSupplier() {
super();
orderValueForDiscount = 0.0;
discountPercentage = 0.0;
}

public DiscountPharmaSupplier(String supplierName, String supplierAddress, double amountOwed,
double creditLimit, double orderValueForDiscount, double discountPercentage) {
super(supplierName, supplierAddress, amountOwed, creditLimit);
setOrderValueForDiscount(orderValueForDiscount);
setDiscountPercentage(discountPercentage);
}

public double getOrderValueForDiscount() {
return orderValueForDiscount;
}

public boolean setOrderValueForDiscount(double orderValueForDiscount) {
if (orderValueForDiscount < 0) {
return false;
}
this.orderValueForDiscount = orderValueForDiscount;
return true;
}

public double getDiscountPercentage() {
return discountPercentage;
}

public boolean setDiscountPercentage(double discountPercentage) {
if (discountPercentage < 0 || discountPercentage > 100) {
return false;
}
this.discountPercentage = discountPercentage;
return true;
}

public double applyDiscount(double orderValue) {
if (orderValue >= orderValueForDiscount) {
return orderValue - (orderValue * discountPercentage / 100.0);
}
return orderValue;
}

@Override
public String toString() {
return "DiscountPharmaSupplier{" +
"name='" + getSupplierName() + '\'' +
", address='" + getSupplierAddress() + '\'' +
", amountOwed=" + String.format("%.2f", getAmountOwed()) +
", creditLimit=" + String.format("%.2f", getCreditLimit()) +
", orderValueForDiscount=" + String.format("%.2f", orderValueForDiscount) +
", discountPercentage=" + String.format("%.2f", discountPercentage) +
'}';
}
}
