package viceCity.models.guns;

public class Rifle extends BaseGun{

    private static int bulletsPerBarrel;
    private static int totalBullets;


    public Rifle(String name) {

        super(name, 50, 500);
    }

    @Override
    public boolean canFire() {
        return getBulletsPerBarrel() >= 5;
    }

    @Override
    public int fire() {
        if (canFire()) {
            bulletsPerBarrel-=5;
            return 5;
        }
            reload();
        return 0;
    }

    private void reload() {
        if(totalBullets > 0){
            bulletsPerBarrel = 50;
            totalBullets -= 50;
        }
    }
}
