package onlineShop.core.interfaces;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Collection<Computer> computerCollections;
    private Collection<Component> componentCollections;
    private Collection<Peripheral> peripheralCollection;

    public ControllerImpl() {
        computerCollections = new ArrayList<>();
        componentCollections = new ArrayList<>();
        peripheralCollection = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        for (Computer c : computerCollections
        ) {
            if (c.getId() == id) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
            }
        }
        switch (computerType) {
            case "DesktopComputer":
                //int id, String manufacturer, String model, double price, double overallPerformance
                Computer computer = new DesktopComputer(id, manufacturer, model, price);
                computerCollections.add(computer);
                break;
            case "Laptop":
                Computer computer1 = new Laptop(id, manufacturer, model, price);
                computerCollections.add(computer1);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }
        return String.format(OutputMessages.ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        Computer computer = computerCollections.stream().filter(e -> e.getId() == computerId).findFirst().orElse(null);
        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        Peripheral peripheral = peripheralCollection.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (peripheral != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
        }
        Peripheral peripheral1;
        switch (peripheralType) {
            case "Headset":
                peripheral1 = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral1 = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);

                break;
            case "Monitor":
                peripheral1 = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);

                break;
            case "Mouse":
                peripheral1 = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);

                break;

            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }
        peripheralCollection.add(peripheral1);
        for (Computer c: computerCollections
        ) {
            if(c.getId()==computerId){
                c.addPeripheral(peripheral1);
            }
        }
        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Computer computer = computerCollections.stream().filter(e -> e.getId() == computerId).findFirst().orElse(null);
        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        Peripheral peripheral = peripheralCollection.stream().filter(c->c.getClass().getSimpleName().equals(peripheralType))
                .findFirst().orElse(null);
        if(peripheral != null){
            peripheralCollection.remove(peripheral);
            for (Computer c: computerCollections
            ) {
                if(c.getId()==computerId){
                    c.removePeripheral(peripheralType);
                }
            }
            return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
        }
        return "";
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        Computer computer = computerCollections.stream().filter(e -> e.getId() == computerId).findFirst().orElse(null);
        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        Component component = componentCollections.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (component != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
        }
        Component component1;
        switch (componentType) {
            case "CentralProcessingUnit":
                component1 = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);

                break;
            case "Motherboard":
              component1 = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);

                break;
            case "PowerSupply":
                 component1 = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);

                break;
            case "RandomAccessMemory":
                 component1 = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);

                break;
            case "SolidStateDrive":
                 component1 = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);

                break;
            case "VideoCard":
                 component1 = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }
        componentCollections.add(component1);
        for (Computer c: computerCollections
             ) {
            if(c.getId()==computerId){
                c.addComponent(component1);
            }
        }
        return String.format(OutputMessages.ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer computer = computerCollections.stream().filter(e -> e.getId() == computerId).findFirst().orElse(null);
        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        Component component = componentCollections.stream().filter(c->c.getClass().getSimpleName().equals(componentType))
                .findFirst().orElse(null);
        if(component != null){
            componentCollections.remove(component);
            for (Computer c: computerCollections
            ) {
                if(c.getId()==computerId){
                    c.removeComponent(componentType);
                }
            }
            return String.format(OutputMessages.REMOVED_COMPONENT, componentType, component.getId());
        }
        return "";
    }

    @Override
    public String buyComputer(int id) {
        Computer computer = computerCollections.stream().filter(e->e.getId() == id).findFirst().orElse(null);
        computerCollections.remove(computer);
        assert computer != null;
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> bestOnes;
       bestOnes =  computerCollections.stream().sorted(Comparator.comparingDouble(Product::getOverallPerformance))
       .collect(Collectors.toList());
       for(int i = 0; i<bestOnes.size(); i++){
           Computer computer = bestOnes.get(i);
           if(computer.getPrice() <= budget){
               computerCollections.remove(computer);
               return computer.toString();
           }
       }
        return String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget);
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = computerCollections.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        return computer.toString();
    }
}
