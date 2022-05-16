package aquarium.entities.decorations;

public class Ornament extends BaseDecoration{
    //as 1 comfort and its price is 5.
    private static final int COMFORT_VALUE = 1;
    private static final double PRICE_VALUE = 5;

    public Ornament() {
        super(COMFORT_VALUE, PRICE_VALUE);
    }
}
