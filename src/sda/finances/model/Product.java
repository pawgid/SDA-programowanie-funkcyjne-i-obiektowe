package sda.finances.model;

public class Product {

    private String name;

    private int amount;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public Product(String name, int amount, double unitPrice) {
        this.name = name;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public String getName() {

        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    private double unitPrice;

}
