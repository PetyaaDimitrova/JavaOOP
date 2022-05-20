package restaurant.entities.tables.interfaces;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
    }

    public void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }
        this.size = size;
    }


    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {

        return numberOfPeople() * pricePerPerson;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
        this.isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double bevSum = this.beverages.stream().mapToDouble(Beverages::getPrice).sum();
        double foodSum = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();

        return bevSum + foodSum + allPeople();
    }

    @Override
    public void clear() {
        this.isReservedTable = false;
        this.numberOfPeople = 0;
        this.beverages.clear();
        this.healthyFood.clear();
    }

    @Override
    public String tableInformation() {

        return "Table: " + number + System.lineSeparator() +
                "Size: " + size + System.lineSeparator() +
                "Type: " + this.getClass().getSimpleName() + System.lineSeparator() +
                "All price - " + pricePerPerson;
    }
}
