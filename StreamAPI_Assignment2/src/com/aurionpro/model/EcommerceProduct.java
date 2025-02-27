package com.aurionpro.model;

public class EcommerceProduct {
    private int productId;
    private String name;
    private String category;
    private double price;
    private double rating;

    public EcommerceProduct(int productId, String name, String category, double price, double rating) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "\nProduct details are: \nproductId=" + productId + "\nname=" + name + "\ncategory=" + category
                + "\nprice=" + price + "\nrating=" + rating;
    }
}
