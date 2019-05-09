package com.bksoftware.sellingweb.entities.product;


public class ProductAndDetails {

    private int id;

    private String name;

    private double originCost;

    private double saleCost;

    private boolean productStatus;

    private int guarantee;

    private String present;

    public ProductAndDetails() {
    }

    public ProductAndDetails(int id,String name, double originCost, double saleCost, boolean productStatus, int guarantee, String present) {
        this.id = id;
        this.name = name;
        this.originCost = originCost;
        this.saleCost = saleCost;
        this.productStatus = productStatus;
        this.guarantee = guarantee;
        this.present = present;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getOriginCost() {
        return originCost;
    }

    public void setOriginCost(double originCost) {
        this.originCost = originCost;
    }

    public double getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(double saleCost) {
        this.saleCost = saleCost;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }
}
