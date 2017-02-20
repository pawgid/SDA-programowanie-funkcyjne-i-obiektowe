package sda.finances.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Expense {
    private String type;

    private LocalDate date;

    private List<Product> products;

    private double price;

    public Expense(String type, List<Product> products, int year, int month, int dayOfMonth) {
        this.type = type;
        this.products = products;
        this.date = LocalDate.of(year, month, dayOfMonth);
        this.price = countPrice();
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getPrice() {
        return price;
    }

    public Expense(String type, List<Product> products) {
        this.type = type;
        this.products = products;
        this.date = LocalDate.now();
        this.price = countPrice();

    }

    private double countPrice() {
        double tmpPrice = 0;
        for (Product product : products) {
            tmpPrice += product.getAmount() * product.getUnitPrice();
        }
        return tmpPrice;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "type='" + type + '\'' +
                ", date=" + date +
                ", products=" + products +
                ", price=" + price +
                '}';
    }
}
