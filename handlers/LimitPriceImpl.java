package handlers;

import common.Type;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LimitPriceImpl implements LimitPrice {

    private static final Map<Type, List<Integer>> map = new LinkedHashMap<>();
    private static final List<String> managers = Arrays.asList("Manager", "Director", "VicePresident", "President", "ExecutiveMeeting");

    public LimitPriceImpl() {
        map.put(Type.CONSUMABLES, Arrays.asList(300, 500, 700, 1000));
        map.put(Type.CLERICAL, Arrays.asList(500, 1000, 1500, 2000));
        map.put(Type.GADGETS, Arrays.asList(1000, 1500, 2000, 3000));
        map.put(Type.GAMING, Arrays.asList(3000, 3500, 4500, 5000));
        map.put(Type.PC, Arrays.asList(5000, 6000, 6500, 8000));
    }

    @Override
    public double getTargetPriceForGivenPosition(Type type, String className) {
        int index = managers.indexOf(className);
        return map.get(type).get(index);
    }
}
