package spaceStation.models.astronauts;


public class Biologist extends BaseAstronaut {

    private static final double OXYGEN = 70;

    public Biologist(String name) {
        super(name, OXYGEN);
    }

    @Override
    public boolean canBreath() {
        if(getOxygen() >= 5){
            return true;
        }
        return false;
    }

    @Override
    public void breath() {
        if(canBreath()){
            setOxygen(getOxygen() - 5);
        }
    }
}
