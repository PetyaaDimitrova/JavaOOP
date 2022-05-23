package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements  Mission{


//xplorers cannot go on expeditions if their energy is below 0.
//
//They leave the station and start collecting exhibits one by one.
//
//If they find an exhibit, their energy is decreased.
//
//They add the exhibit to their carton. The exhibit should then be removed from the state.
//
//Explorers cannot continue collecting exhibits if their energy drops to 0.
//
//If their energy drops to 0, the next explorer starts exploring.
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        List<String> items = new ArrayList<>(state.getExhibits());
        for (Explorer e: explorers
             ) {
            while (e.canSearch()){
                String item = items.get(0);
                e.getSuitcase().getExhibits().add(item);
                e.search();
                items.remove(0);
                if(items.size() == 0){
                    return;
                }
            }
            explorers.remove(e);
        }


    }
}
