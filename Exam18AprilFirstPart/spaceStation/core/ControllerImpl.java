package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerImpl implements Controller {
    private AstronautRepository astronauts;
    private PlanetRepository planets;
    private Mission mission;

    public ControllerImpl() {
        this.astronauts =new AstronautRepository();
        this.planets = new PlanetRepository();
        this.mission = new MissionImpl();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut = null;
       switch (type){
           case"Biologist":
              astronaut = new Biologist(astronautName);
              astronauts.add(astronaut);
               break;
           case "Geodesist":
               astronaut = new Geodesist(astronautName);
               astronauts.add(astronaut);
               break;
           case "Meteorologist":
               astronaut = new Meteorologist(astronautName);
               astronauts.add(astronaut);
               break;
           default:
               throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
       }
       return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        List<String> itemsList = Arrays.asList(items);
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(itemsList);
        planets.add(planet);
        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = astronauts.findByName(astronautName);
        if(astronaut == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        astronauts.remove(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = planets.findByName(planetName);
        if(astronauts.getModels().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        int startNumber = astronauts.getModels().size();
        mission.explore(planet, astronauts.getModels());
        int endNumber = astronauts.getModels().size();
        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, startNumber - endNumber);
    }

    @Override
    public String report() {
        return null;
    }
}
