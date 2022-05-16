package aquarium.entities.fish;

public class FreshwaterFish extends BaseFish {
    private static final int SIZE_VALUE = 3;


    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);//////////size в конструктора, както и в на BaseFish конструктора????????
        setSize(SIZE_VALUE);
    }
    ////////////////////////////Can only live in FreshwaterAquarium!???????


    @Override
    public void eat() {
        int newSize = getSize() + SIZE_VALUE;
        setSize(newSize);
    }
}
