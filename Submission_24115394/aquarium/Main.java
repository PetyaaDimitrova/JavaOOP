package aquarium;

import aquarium.core.Engine;
import aquarium.core.EngineImpl;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;


public class Main {
    public static void main(String[] args) {
       Engine engine = new EngineImpl();
       engine.run();



    }
}
