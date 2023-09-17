package se.su.dsv.grupp03;

import se.su.dsv.grupp03.utilities.StringFormatter;

import java.util.*;

public class Catalog {
    private static final StringFormatter formatter = new StringFormatter();
    private HashMap<Category, HashSet<Product>> catalogByCategory;
    private int size = 0;

    public Catalog() {
        catalogByCategory = new HashMap<>();
    }

    public boolean isEmpty() {
        return catalogByCategory == null || catalogByCategory.keySet().size() == 0;
    }

    public int getSize() {
        return size;
    }

    public void add(Product product) {
        if (product == null)
            throw new IllegalArgumentException();
        HashSet<Product> productList = catalogByCategory.computeIfAbsent(product.getCategory(), k -> new HashSet<>());
        if (productList.contains(product))
            return;
        productList.add(product);
        size++;
    }

    public Product getProduct(Product product) {
        if (product == null || !isProductPresent(product))
            throw new IllegalArgumentException();

        Product result = null;
        Set<Product> list = catalogByCategory.get(product.getCategory());
        for (Product p : list) {
            if (p == product)
                result = p; }
        return result;
    }

    public HashSet<Product> getProductsInCategory(Category category) {
        if(category == null)
            throw new NullPointerException();
        return catalogByCategory.get(category);
    }

    public Set<Category> getAllCategories() {
        return catalogByCategory.keySet();
    }

    public void deleteCatalog() {
        catalogByCategory = new HashMap<>();
        size = 0;
    }

    public void remove(Product product) {
        if (!isProductPresent(product))
            throw new IllegalArgumentException();
        catalogByCategory.get(product.getCategory()).remove(product);
        size--;
    }

    public boolean isProductPresent(Product product) {
       if (catalogByCategory.get(product.getCategory()) == null || !catalogByCategory.get(product.getCategory()).contains(product))
           return false;
       return true;
    }

}
