package handlers;

import common.Type;

public interface LimitPrice {

    double getTargetPriceForGivenPosition(Type type, String className);

}
