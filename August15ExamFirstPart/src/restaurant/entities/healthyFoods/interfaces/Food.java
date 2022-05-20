package restaurant.entities.healthyFoods.interfaces;

import restaurant.common.ExceptionMessages;

public abstract class Food implements HealthyFood {
    private String name;
    private double portion;
    private double price;

    protected Food(String name, double portion, double price) {
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
    }

    public void setPrice(double price){
        if(price<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    public void setPortion(double portion) {
        if (portion <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PORTION);
        }
        this.portion = portion;
    }

    public void setName(String name) {
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPortion() {
        return portion;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
