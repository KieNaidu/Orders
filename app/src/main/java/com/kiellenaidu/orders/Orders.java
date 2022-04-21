package com.kiellenaidu.orders;

public class Orders {

    String product;
    String quantity;

    public Orders() { //Empty constructor
    }

    public Orders(String product, String quantity) {
        this.product = product;
        this.quantity = quantity;

    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String ToString() {
        return "Product name     " + product  + "     Product quantity      " + quantity;
    }

}
