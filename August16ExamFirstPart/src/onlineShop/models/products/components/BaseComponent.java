package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;

public abstract class BaseComponent extends BaseProduct implements Component {

    private int generation;

    protected BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getManufacturer() {
        return super.getManufacturer();
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public double getOverallPerformance() {
        return super.getOverallPerformance();
    }

    @Override
    public int getGeneration() {
        return generation;
    }
    @Override
    public String toString() {
        return String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d) Generation: %d", getOverallPerformance(), getPrice()
                ,this.getClass().getSimpleName(), getManufacturer(), getModel(), getId(), getGeneration());
    }
}
