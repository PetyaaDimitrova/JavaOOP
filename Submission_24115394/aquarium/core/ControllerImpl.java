package aquarium.core;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository decorations;
    private List<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        switch (aquariumType) {
            case "FreshwaterAquarium":
                Aquarium aquarium = new FreshwaterAquarium(aquariumName);
                aquariums.add(aquarium);
                break;
            case "SaltwaterAquarium":
                Aquarium aquarium2 = new SaltwaterAquarium(aquariumName);
                aquariums.add(aquarium2);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        switch (type) {
            case "Ornament":
                Decoration decoration = new Ornament();
                decorations.add(decoration);
                break;
            case "Plant":
                Decoration decoration1 = new Plant();
                decorations.add(decoration1);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);

        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = decorations.findByType(decorationType);
        if (decoration == null) {
            throw new IllegalStateException(String.format(ExceptionMessages.NO_DECORATION_FOUND, decorationType));
        }
        for (Aquarium ac : aquariums
        ) {
            if (ac.getName().equals(aquariumName)) {
                ac.addDecoration(decoration);
                decorations.remove(decoration);
                break;
            }
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    //dds the desired Fish to the Aquarium with the given name. Valid Fish types are: "FreshwaterFish", "SaltwaterFish".
    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        switch (fishType) {
            case "FreshwaterFish":
                FreshwaterFish freshwaterFish = new FreshwaterFish(fishName, fishSpecies, price);
                for (Aquarium ac : aquariums
                ) {
                    if (ac.getName().equals(aquariumName)) {
                        if (ac.getClass().getSimpleName().equals("FreshwaterAquarium")) {
                            ac.addFish(freshwaterFish);
                        } else {
                            return ConstantMessages.WATER_NOT_SUITABLE;
                        }
                    }
                }
                break;
            case "SaltwaterFish":
                SaltwaterFish saltwaterFish = new SaltwaterFish(fishName, fishSpecies, price);
                for (Aquarium ac : aquariums
                ) {
                    if (ac.getName().equals(aquariumName)) {
                        if (ac.getClass().getSimpleName().equals("SaltwaterAquarium")) {
                            ac.addFish(saltwaterFish);
                        } else {
                            return ConstantMessages.WATER_NOT_SUITABLE;
                        }
                    }
                }
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        int n = 0;
        for (Aquarium ac : aquariums
        ) {
            if (ac.getName().equals(aquariumName)) {
                ac.feed();
                n= ac.getFish().size();
            }
        }
        return String.format(ConstantMessages.FISH_FED, n);
    }

    @Override
    public String calculateValue(String aquariumName) {
        double price = 0;
        for (Aquarium ac : aquariums
        ) {
            if (ac.getName().equals(aquariumName)) {
                price = ac.getFish().stream().mapToDouble(Fish::getPrice).sum() +
                        ac.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
            }
        }
        return String.format(ConstantMessages.VALUE_AQUARIUM, aquariumName,price);
    }

    @Override
    public String report() {
        return aquariums.stream().map(Aquarium::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }
}
