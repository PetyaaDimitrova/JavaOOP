package restaurant.entities.drinks.interfaces;

public class Smoothie extends BaseBeverage{
    private static final double PRICE = 4.5;

    public Smoothie(String name, int counter,  String brand) {
        super(name, counter, PRICE, brand);
    }
}