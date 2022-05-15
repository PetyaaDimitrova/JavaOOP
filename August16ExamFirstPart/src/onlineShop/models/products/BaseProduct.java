package onlineShop.models.products;

import onlineShop.common.constants.ExceptionMessages;

public abstract class BaseProduct implements Product {

    private int id;
    private String manufacturer;
    private String model;
    private double price;
    private double overallPerformance;

    protected BaseProduct(int id, String manufacturer, String model, double price, double overallPerformance) {
        this.setId(id);
        this.setManufacturer(manufacturer);
        this.setModel(model);
        this.setPrice(price);
        this.setOverallPerformance(overallPerformance);
    }

    public void setOverallPerformance(double overallPerformance) {
        if (overallPerformance <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_OVERALL_PERFORMANCE);
        }
        this.overallPerformance = overallPerformance;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    public void setModel(String model) {
        if (model == null || model.trim().equals("")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MODEL);
        }
        this.model = model;
    }

    public void setManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.trim().equals("")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MANUFACTURER);
        }
        this.manufacturer = manufacturer;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRODUCT_ID);
        }
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getOverallPerformance() {
        return overallPerformance;
    }

    @Override
    public String toString() {
        return String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d)", overallPerformance
                , price, this.getClass().getSimpleName(), manufacturer, model, id);
    }
}
