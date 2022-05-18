package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        List<Astronaut> list = new ArrayList<>(astronauts);
        List<String> items = new ArrayList<>(planet.getItems());

/*
        for(int i = 0; i< list.size(); i++){

            Astronaut astronaut = list.get(i);
            List<String> itemsInBag = new ArrayList<>(astronaut.getBag().getItems());
            while (astronaut.canBreath()){
                for(int j = 0; j< items.size(); j++){
                    itemsInBag.add(items.get(j));
                    items.remove(items.get(j));

                    astronaut.breath();
                    if(!astronaut.canBreath()){
                        break;
                    }
                }
            }
            astronaut.getBag().getItems().addAll(itemsInBag);
        }

        planet.getItems().clear();
        planet.getItems().addAll(items);

        astronauts = new ArrayList<>(list); */
    }
}
