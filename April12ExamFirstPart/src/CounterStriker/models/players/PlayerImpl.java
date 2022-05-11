package CounterStriker.models.players;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

public abstract class PlayerImpl implements Player {
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    protected PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.setGun(gun);
    }

    public void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }
        this.gun = gun;
    }

    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().equals("")) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public Gun getGun() {
        return gun;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public void takeDamage(int points) {
        int newDamage = armor - points;
        if (newDamage >= 0) {
    this.setArmor(armor - points);
    if(health == 0 && armor == 0){
        isAlive = false;
    }
        } else {
            this.setArmor(0);
            int newHealth = this.getHealth() + newDamage;
            if(newHealth <= 0){
                this.setHealth(0);
                isAlive = false;
            } else {
                this.setHealth(newHealth);
            }
        }
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s: %s", this.getClass().getSimpleName(), this.username)).append(System.lineSeparator());
        sb.append(String.format("--Health: %d", this.health)).append(System.lineSeparator());
        sb.append(String.format("--Armor: %d", this.armor)).append(System.lineSeparator());
        sb.append(String.format("Gun: %s", this.gun.getName()));
        return sb.toString().trim();
    }
}
