package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldImpl implements Field {

    @Override
    public String start(Collection<Player> players) {
        Collection<Player> counterTerroristList = players.stream().filter(p -> p instanceof CounterTerrorist)
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
    }
}





      /*  while (terroristList.isEmpty() && counterTerroristList.isEmpty()) {
            for (int i = 0; i < terroristList.size(); i++) {
                int bullets = terroristList.get(i).getGun().getBulletsCount();
                terroristList.get(i).getGun().fire();
                for (int j = 0; j < counterTerroristList.size(); j++) {
                    counterTerroristList.get(j).takeDamage(bullets);
                    if (!counterTerroristList.get(j).isAlive()) {
                        counterTerroristList.remove(counterTerroristList.get(j));
                        j--;

                    }
                }
            }

            return null;
        }*/