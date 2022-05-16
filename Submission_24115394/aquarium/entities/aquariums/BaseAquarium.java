package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private List<Fish> fish;


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }



    public BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    public void setName(String name) {
        if (name.isEmpty() || name.trim().equals("")) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        int sum = 0;
        for (Decoration decor : decorations
        ) {
            sum += decor.getComfort();
        }
        return sum;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        int fishInAquarium = this.fish.size();
        if (fishInAquarium == capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    //void removeFish(Fish fish)
//
//Removes a Fish from the Aquarium.
    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish fish: fish
             ) {
            fish.eat();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s:", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());
        sb.append("Fish: ");
        if(this.fish.isEmpty()){
            sb.append("none").append(System.lineSeparator());
        } else {
        String fishOutput =  fish.stream().map(Fish::getName).collect(Collectors.joining(" "));
        sb.append(fishOutput).append(System.lineSeparator());
        }
        sb.append(String.format("Decorations: %d", decorations.size())).append(System.lineSeparator());
        sb.append(String.format("Comfort: %d", calculateComfort())).append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public List<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}
