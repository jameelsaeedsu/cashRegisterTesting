package se.su.dsv.grupp03;

import java.util.Objects;

public class OrderLine {

    private final Order order;
    private final Product product;
    private int quantity;

    public OrderLine(Order order, Product product, int quantity) {
        if(order == null || product == null)
            throw new NullPointerException();
        if(quantity<1)
            throw new IllegalArgumentException("Minimum quantity is 1.");

        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newQuantity) {
        if(newQuantity<1)
            throw new IllegalArgumentException("Minimum quantity is 1.");
        quantity = newQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderLine orderLine = (OrderLine) o;
        return order.getId() == orderLine.order.getId() && product.getId() == orderLine.product.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(order.getId(), product.getId());
    }

}
