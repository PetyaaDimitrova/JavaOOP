package handlers;

import common.Type;
import messages.ExceptionMessages;

public class PurchaseValidator {


    private int id;
    private double cost;
    private Type type;

    public PurchaseValidator(int id, double cost, Type type) {
        this.setId(id);
        this.setCost(cost);
        this.setType(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id <= 0){
            throw new IllegalArgumentException(ExceptionMessages.PURCHASE_ID_LESS_OR_EQUALS_THAN_ZERO);
        }
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if(cost < 0){
            throw new IllegalArgumentException(ExceptionMessages.PURCHASE_COST_LESS_THAN_ZERO);
        }
        this.cost = cost;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if (type == null) {
            throw new NullPointerException(ExceptionMessages.TYPE_NAME_NULL);
        }
        this.type = type;
    }
}
