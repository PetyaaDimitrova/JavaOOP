package viceCity.models.guns;

public class Pistol extends BaseGun {
    private static int bulletsPerBarrel;
    private static int totalBullets;


    public Pistol(String name) {
        super(name, 10, 100);
    }

    @Override
    public boolean canFire() {

        return getBulletsPerBarrel() >= 1;
    }


    @Override
    public int fire() {
        if (canFire()) {
            bulletsPerBarrel -= 1;
            return 1;
        }
        reload();
        return 0;
    }

    private void reload() {
        if (totalBullets > 0) {
            bulletsPerBarrel = 10;
            totalBullets -= 10;
        }
    }
}
