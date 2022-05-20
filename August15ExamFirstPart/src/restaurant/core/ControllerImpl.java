package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.interfaces.Fresh;
import restaurant.entities.drinks.interfaces.Smoothie;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.Salad;
import restaurant.entities.healthyFoods.interfaces.VeganBiscuits;
import restaurant.entities.tables.interfaces.InGarden;
import restaurant.entities.tables.interfaces.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

import java.util.Collection;

public class ControllerImpl implements Controller {


    private final HealthFoodRepository<HealthyFood> healthFoodRepository;
    private final TableRepository<Table> tableRepository;
    private final BeverageRepository<Beverages> beverageRepository;

    private double totalSum;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood healthyFood= null;

          if(type.equals("Salad")) {
              healthyFood = new Salad(name, price);
          } else if(type.equals("VeganBiscuits")) {

                healthyFood = new VeganBiscuits(name,price);

        }
        HealthyFood previouslyAdded = this.healthFoodRepository.foodByName(name);
        if(previouslyAdded == null){
            healthFoodRepository.add(healthyFood);
            return String.format(OutputMessages.FOOD_ADDED, name);
        }
       throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverage = null;
        if(type.equals("Fresh")){
            beverage = new Fresh(name,counter,brand);
        } else if(type.equals("Smoothie")){
            beverage = new Smoothie(name,counter, brand);
        }
        Beverages previouslyAdded = this.beverageRepository.beverageByName(name, brand);
        if(previouslyAdded == null){
            beverageRepository.add(beverage);
            return String.format(OutputMessages.BEVERAGE_ADDED, type,brand);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = null;
        if(type.equals("Indoors")){
            table = new Indoors(tableNumber, capacity);
        } else if(type.equals("InGarden")){
            table = new InGarden(tableNumber, capacity);
        }
        Table previouslyAdded = this.tableRepository.byNumber(tableNumber);
        if(previouslyAdded == null){
            tableRepository.add(table);
            return String.format(OutputMessages.TABLE_ADDED, tableNumber);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
    }

    @Override
    public String reserve(int numberOfPeople) {
        Collection<Table> tables = tableRepository.getAllEntities();
        for (Table table: tables
             ) {
            if(!table.isReservedTable() && table.getSize() >= numberOfPeople){
                table.reserve(numberOfPeople);
                return String.format(OutputMessages.TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
            }
        }
        return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = tableRepository.byNumber(tableNumber);
        if(table == null){
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood healthyFood = healthFoodRepository.foodByName(healthyFoodName);
        if(healthyFood == null){
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, healthyFoodName);
        }
        table.orderHealthy(healthyFood);
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = tableRepository.byNumber(tableNumber);
        if(table == null){
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        Beverages beverages = beverageRepository.beverageByName(name,brand);
        if(beverages == null){
            return String.format(OutputMessages.NON_EXISTENT_DRINK, name, brand);
        }
        table.orderBeverages(beverages);
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = tableRepository.byNumber(tableNumber);
        double sum = table.bill();
        totalSum+=sum;
        table.clear();
        return String.format(OutputMessages.BILL, tableNumber, sum);
    }


    @Override
    public String totalMoney() {

        return String.format(OutputMessages.TOTAL_MONEY, totalSum);
    }
}
