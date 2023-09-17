package se.su.dsv.grupp03;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order {

    private final int id;
    private static int nextId = 1;
    private final Set<OrderLine> lines = new HashSet<>();

    public Order() {
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }

    public void add(OrderLine orderLine) {
        lines.add(orderLine);
    }

    public void remove(OrderLine line) {
        lines.remove(line);
    }

    public boolean contains(OrderLine line) {
        return lines.contains(line);
    }

    public boolean isEmpty() {
        return lines.isEmpty();
    }

    public int size(){
        return lines.size();
    }

    //Används för att kalkylera VAT i Receipt.
    public Set<OrderLine> getLines() {
        return Collections.unmodifiableSet(lines);

    }

    public Money getOrderTotal() {
        Money total = new Money(0);
        for (OrderLine o : lines)
            total = total.add(o.getProduct().getPrice().multiplyWithInteger(o.getQuantity()));
        return total;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
