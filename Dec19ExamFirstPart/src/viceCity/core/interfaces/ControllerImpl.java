package viceCity.core.interfaces;

import viceCity.common.ConstantMessages;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller {

    private Collection<Player> civilPlayers;
    private MainPlayer mainPlayer;
    private List<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        this.guns = new ArrayList<>();
        mainPlayer = new MainPlayer();
        neighbourhood = new GangNeighbourhood();
        civilPlayers = new ArrayList<>();
    }

    @Override
    public String addPlayer(String name) {
        CivilPlayer civilPlayer = new CivilPlayer(name);
        this.civilPlayers.add(civilPlayer);
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun = null;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                guns.add(gun);
                break;
            case "Rifle":
                gun = new Rifle(name);
                guns.add(gun);
                break;
            default:
                return ConstantMessages.GUN_TYPE_INVALID;
        }

        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (guns.size() == 0) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }
        if (name.equals("Vercetti")) {
            Gun gun = guns.get(0);
            mainPlayer.getGunRepository().add(gun);
            guns.remove(0);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), mainPlayer.getName());
        }
        Player c = civilPlayers.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (c == null) {
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }
        Gun gun = guns.get(0);
        c.getGunRepository().add(gun);
        guns.remove(0);
        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
    }

    @Override
    public String fight() {
        neighbourhood.action(mainPlayer, civilPlayers);
        if (!civilPlayers.isEmpty() && mainPlayer.isAlive()){
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        }
        return ConstantMessages.FIGHT_HAPPENED;
    }
}
