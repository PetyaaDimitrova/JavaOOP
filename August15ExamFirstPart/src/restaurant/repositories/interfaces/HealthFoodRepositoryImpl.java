package restaurant.repositories.interfaces;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {
    private Map<String, HealthyFood> healthyFood;

    public HealthFoodRepositoryImpl() {
        this.healthyFood = new LinkedHashMap<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        return healthyFood.get(name);
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return healthyFood.values();
    }

    @Override
    public void add(HealthyFood entity) {
        healthyFood.put(entity.getName(), entity);
    }
}
