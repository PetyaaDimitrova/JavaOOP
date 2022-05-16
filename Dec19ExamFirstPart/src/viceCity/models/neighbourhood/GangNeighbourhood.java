package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.*;
import java.util.stream.Collectors;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        List<Gun> guns = new ArrayList<>(mainPlayer.getGunRepository().getModels());
        List<Player> civil = new ArrayList<>(civilPlayers);
        while (!guns.isEmpty()) {
            Gun gun = guns.get(0);
            while (gun.canFire() && !civil.isEmpty()) {
                for (int i = 0; i < civil.size(); i++) {
                    Player civilPlayer = civil.get(i);
                    int lifePoints = gun.fire();
                    civilPlayer.takeLifePoints(lifePoints);
                    if (!civilPlayer.isAlive()) {
                        civil.remove(i);
                    }
                    i--;
                }
            }
            guns.remove(0);
        }
        if (!civil.isEmpty()) {
            for (int i = 0; i < civil.size(); i++) {
                Player civilPlayer = civil.get(i);
                List<Gun> hisGuns = new ArrayList<>(civilPlayer.getGunRepository().getModels());
                while (!hisGuns.isEmpty()) {
                    Gun thisGun = hisGuns.get(0);
                    while (thisGun.canFire()) {
                        int lifePoints = thisGun.fire();
                        mainPlayer.takeLifePoints(lifePoints);
                        if (!mainPlayer.isAlive()) {
                            return;
                        }
                    }
                    hisGuns.remove(0);
                }
            }
        } else {
            civilPlayers = new ArrayList<>(civil);
        }
      /*  Collection<Player> counterTerroristList = players.stream().filter(p -> p instanceof CounterTerrorist)
                .collect(Collectors.toList());
        Collection<Player> terroristList = players.stream().filter(p -> p instanceof Terrorist)
                .collect(Collectors.toList());

        while (counterTerroristList.stream().anyMatch(Player::isAlive) && terroristList.stream().anyMatch(Player::isAlive)) {
            for (Player terrorist : terroristList) {
                for (Player counterTerrorist : counterTerroristList) {
                    counterTerrorist.takeDamage(terrorist.getGun().fire());
                }
            }
            counterTerroristList = counterTerroristList.stream().filter(Player::isAlive).collect(Collectors.toList());

            for (Player counterTerrorist : counterTerroristList) {
                for (Player terrorist : terroristList) {
                    terrorist.takeDamage(counterTerrorist.getGun().fire());
                }
            }

            terroristList = terroristList.stream().filter(Player::isAlive).collect(Collectors.toList());


        }
        return terroristList.stream().anyMatch(Player::isAlive) ? OutputMessages.TERRORIST_WINS : OutputMessages.COUNTER_TERRORIST_WINS;
  */
    }


}
