package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house = null;
        switch (type) {
            case "LongHouse":
                house = new LongHouse(name);
                houses.add(house);
                break;
            case "ShortHouse":
                house = new ShortHouse(name);
                houses.add(house);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy = null;
        switch (type) {
            case "Ball":
                toy = new Ball();
                toys.buyToy(toy);
                break;
            case "Mouse":
                toy = new Mouse();
                toys.buyToy(toy);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);
        if (toy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }
        toys.removeToy(toy);
        House house = houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
        assert house != null;
        house.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat = null;
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }
        House house = houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
        assert house != null;
        if (house.getClass().getSimpleName().equals("ShortHouse") && catType.equals("ShorthairCat") ||
                house.getClass().getSimpleName().equals("LongHouse") && catType.equals("LonghairCat")) {
            house.addCat(cat);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }
        return ConstantMessages.UNSUITABLE_HOUSE;
    }

    @Override
    public String feedingCat(String houseName) {
        House house = houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
        assert house != null;
        house.feeding();
        int feedCats = house.getCats().size();
        return String.format(ConstantMessages.FEEDING_CAT, feedCats);
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
        assert house != null;
        double priceCats = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double priceToys = house.getToys().stream().mapToDouble(Toy::getPrice).sum();

        return String.format(ConstantMessages.VALUE_HOUSE, houseName, priceCats + priceToys);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (House house: houses
             ) {
            sb.append(house.getStatistics()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
