package onlineShop.models.products.computers;

public class DesktopComputer extends BaseComputer{
    private static final double overall_performance = 10;
    public DesktopComputer(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, overall_performance);
    }
}
