package onlineShop;

import onlineShop.core.EngineImpl;
import onlineShop.core.interfaces.Engine;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    Engine engine = new EngineImpl();
        engine.run();

    }
}
