package aquarium.entities.decorations;

public class Plant extends BaseDecoration{
    private static final int COMFORT_VALUE = 5;
    private static final double PRICE_VALUE = 10;

    public Plant() {
        super(COMFORT_VALUE, PRICE_VALUE);
    }
}
