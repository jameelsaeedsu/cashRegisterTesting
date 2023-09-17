package se.su.dsv.grupp03;

import se.su.dsv.grupp03.utilities.StringFormatter;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private static int startId = 100;
    private final int id;
    private final String name;
    private final Money price;
    private final Manufacturer manufacturer;
    private final Supplier supplier;
    private final Category category;


    public Product(String name, Money price, Manufacturer manufacturer, Supplier supplier, Category category) {
        StringFormatter formatter = new StringFormatter();
        if(formatter.normalizeWhitespace(name).isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty");
        if(manufacturer == null || supplier == null || category == null)
            throw new NullPointerException();
        this.name = formatter.normalizeWhitespace(name);
        this.manufacturer = manufacturer;
        this.supplier = supplier;
        this.category = category;
        this.id = startId++;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Money getVat() {
        BigDecimal totalVAT = getPrice().getAmount().multiply(EnumVat.TWENTY_FIVE_PERCENT.getInBigDecimal());
        return new Money(Double.parseDouble(totalVAT.toString()));
    }

    public Money getPricePlusVAT() {
        BigDecimal pricePlusVAT = getPrice().getAmount().add(getVat().getAmount());
        return new Money(Double.parseDouble(pricePlusVAT.toString()));
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
