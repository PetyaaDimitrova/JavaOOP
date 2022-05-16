package viceCity.models.guns;

import viceCity.common.ExceptionMessages;

public abstract class BaseGun implements Gun{
    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;
    private boolean canFire;
    
    protected BaseGun(String name, int bulletsPerBarrel, int totalBullets) {
        this.setName(name);
        this.setBulletsPerBarrel(bulletsPerBarrel);
        this.setTotalBullets(totalBullets);
    }



    private void setTotalBullets(int totalBullets) {
        if(totalBullets <0){
            throw new IllegalArgumentException(ExceptionMessages.TOTAL_BULLETS_LESS_THAN_ZERO);
        }
        this.totalBullets = totalBullets;
    }

    private void setBulletsPerBarrel(int bulletsPerBarrel) {
        if(bulletsPerBarrel <0){
            throw new IllegalArgumentException(ExceptionMessages.BULLETS_LESS_THAN_ZERO);
        }
        this.bulletsPerBarrel = bulletsPerBarrel;
    }

    private void setName(String name) {
        if(name == null || name.trim().equals("")){
            throw new NullPointerException(ExceptionMessages.NAME_NULL);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsPerBarrel() {
        return this.bulletsPerBarrel;
    }

    @Override
    public boolean canFire() {
       if(bulletsPerBarrel > 0){
           return true;
       }
        return false;
    }

    @Override
    public int getTotalBullets() {
        return this.totalBullets;
    }

    @Override
    public int fire() {
        return 0;
    }
}
