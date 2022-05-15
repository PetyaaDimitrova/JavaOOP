package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;


    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        }
        double sum = 0;
        for (Peripheral p : peripherals
        ) {
            sum += p.getOverallPerformance();
        }
        return sum / peripherals.size();
    }

    @Override
    public double getPrice() {
        double sumComponents = components.stream().mapToDouble(Component::getPrice).sum();
        double sumPeripherals = peripherals.stream().mapToDouble(Product::getPrice).sum();
        return sumComponents + sumPeripherals;
    }

    @Override
    public List<Component> getComponents() {
        return null;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return null;
    }

    @Override
    public void addComponent(Component component) {
        if (components.contains(component)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT, component.getClass().getSimpleName()
                    , this.getClass().getSimpleName(), this.getId()));
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {

       Component component = components.stream().filter(e-> e.getClass().getSimpleName().equals(componentType)).findFirst()
               .orElse(null);
        if(components.isEmpty() || component == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT, componentType,
                    this.getClass().getSimpleName(), this.getId()));
        }
        components.remove(component);
        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (peripherals.contains(peripheral)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName()
                    , this.getClass().getSimpleName(), this.getId()));
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral = peripherals.stream().filter(e-> e.getClass().getSimpleName().equals(peripheralType)).findFirst()
                .orElse(null);
        if(peripherals.isEmpty() || peripheral == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL, peripheralType,
                    this.getClass().getSimpleName(), this.getId()));
        }
        peripherals.remove(peripheral);
        return peripheral;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d)", getOverallPerformance(), getPrice()
                , this.getClass().getSimpleName(), getManufacturer(), getModel(), getId())).append(System.lineSeparator());

        sb.append(String.format(" Components: (%d)", components.size())).append(System.lineSeparator());
        for (Component c : components
        ) {
            sb.append("  ");
            sb.append(c.toString()).append(System.lineSeparator());
        }

        sb.append(String.format(" Peripherals (%d); Average Overall Performance (%.2f):", peripherals.size(), this.getOverallPerformance()));
        sb.append(System.lineSeparator());
        for (Peripheral p : peripherals
        ) {
            sb.append("  ");
            sb.append(p.toString()).append(System.lineSeparator());
        }
        return sb.toString();

    }

}
