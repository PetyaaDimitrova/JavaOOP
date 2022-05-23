package glacialExpedition.models.explorers;

public class NaturalExplorer extends  BaseExplorer{

    private static final double ENERGY = 60.0;
    public NaturalExplorer(String name) {
        super(name, ENERGY);
    }

    @Override
    public void search() {
        if(getEnergy() <=7){
            super.setEnergy(0);
        } else {
            super.setEnergy(this.getEnergy() - 7);
        }
    }
}
