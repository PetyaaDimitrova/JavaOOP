package CounterStriker.models.guns;

import CounterStriker.common.ExceptionMessages;

public abstract class GunImpl implements Gun{
    private String name;
    private int bulletsCount;

    protected GunImpl(String name, int bulletsCount) {
        this.setName(name);
        this.setBulletsCount(bulletsCount);
    }

    public void setBulletsCount(int bulletsCount){
        if(bulletsCount<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_BULLETS_COUNT);
        }
        this.bulletsCount = bulletsCount;
    }

    private void setName(String name) {
        if(name == null || name.trim().equals("")){
            throw new NullPointerException(ExceptionMessages.INVALID_GUN_NAME);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBulletsCount() {
        return bulletsCount;
    }

    @Override
    public int fire() {
        return 0;
    }
}
