package hello.advanced.pureproxy.concreteproxy;

import hello.advanced.pureproxy.concreteproxy.code.ConcreteClient;
import hello.advanced.pureproxy.concreteproxy.code.ConcreteLogic;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }
}
