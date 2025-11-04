public class PharmaSupplier {
    private String supplierName;
    private String supplierAddress;
    private double amountOwed;
    private double creditLimit;

    /**
     * No-argument constructor that sets sensible defaults.
     */
    public PharmaSupplier() {
        this("Unnamed Supplier", "Unknown Address", 0.0, 0.0);
    }

    /**
     * Constructor that accepts all fields.
     *
     * @param supplierName    name of the supplier
     * @param supplierAddress address of the supplier
     * @param amountOwed      current amount owed to the supplier
     * @param creditLimit     credit limit for the supplier
     */
    public PharmaSupplier(String supplierName, String supplierAddress, double amountOwed, double creditLimit) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.amountOwed = Math.max(amountOwed, 0.0);
        this.creditLimit = Math.max(creditLimit, 0.0);
    }

    public String getSupplierName() {
        return supplierName;
    }

    public boolean setSupplierName(String supplierName) {
        if (supplierName == null || supplierName.trim().isEmpty()) {
            return false;
        }
        this.supplierName = supplierName.trim();
        return true;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public boolean setSupplierAddress(String supplierAddress) {
        if (supplierAddress == null || supplierAddress.trim().isEmpty()) {
            return false;
        }
        this.supplierAddress = supplierAddress.trim();
        return true;
    }

    public double getAmountOwed() {
        return amountOwed;
    }

    public boolean setAmountOwed(double amountOwed) {
        if (amountOwed < 0 || amountOwed > creditLimit) {
            return false;
        }
        this.amountOwed = amountOwed;
        return true;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public boolean setCreditLimit(double creditLimit) {
        if (creditLimit < 0 || creditLimit < amountOwed) {
            return false;
        }
        this.creditLimit = creditLimit;
        return true;
    }

    /**
     * Increase the amount owed to this supplier.
     *
     * @param amount amount to increase
     * @return true when amount owed was updated, false otherwise
     */
    public boolean increaseAmountOwed(double amount) {
        if (amount <= 0) {
            return false;
        }
        double newAmount = amountOwed + amount;
        if (newAmount > creditLimit) {
            return false;
        }
        amountOwed = newAmount;
        return true;
    }

    /**
     * Decrease the amount owed to this supplier.
     *
     * @param amount amount to decrease
     * @return true when amount owed was updated, false otherwise
     */
    public boolean decreaseAmountOwed(double amount) {
        if (amount <= 0) {
            return false;
        }
        double newAmount = amountOwed - amount;
        if (newAmount < 0) {
            return false;
        }
        amountOwed = newAmount;
        return true;
    }

    @Override
    public String toString() {
        return "PharmaSupplier{" +
                "name='" + supplierName + '\'' +
                ", address='" + supplierAddress + '\'' +
                ", amountOwed=" + String.format("%.2f", amountOwed) +
                ", creditLimit=" + String.format("%.2f", creditLimit) +
                '}';
    }
}
