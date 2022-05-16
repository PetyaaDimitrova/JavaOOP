package viceCity.models.players;

import viceCity.common.ExceptionMessages;
import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

public abstract class BasePlayer implements Player {
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    protected BasePlayer(String name, int lifePoints) {
        this.setName(name);
        this.setLifePoints(lifePoints);
        this.gunRepository = new GunRepository();
    }

    private void setLifePoints(int lifePoints) {
        if (lifePoints < 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }
        this.lifePoints = lifePoints;
    }

    private void setName(String name) {
        if (name == null || name.trim().equals("")) {
            throw new NullPointerException(ExceptionMessages.NAME_NULL);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public boolean isAlive() {
        if (lifePoints > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return this.gunRepository;
    }

    @Override
    public void takeLifePoints(int points) {
        if (getLifePoints() - points <= 0) {
            this.setLifePoints(0);
        }
        if (isAlive()) {
            this.setLifePoints(getLifePoints() - points);
        }
    }
}
