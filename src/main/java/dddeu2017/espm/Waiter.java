package dddeu2017.espm;

import dddeu2017.espm.events.OrderPlaced;
import dddeu2017.espm.framework.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static dddeu2017.espm.util.Util.newUUID;

public class Waiter {

    private static final Logger log = LoggerFactory.getLogger(Waiter.class);

    private final Publisher publisher;

    public Waiter(Publisher publisher) {
        this.publisher = publisher;
    }

    public UUID placeOrder() {
        Order order = new Order();
        order.orderId = newUUID();
        order.expires = Instant.now().plusSeconds(15);
        order.tableNumber = ThreadLocalRandom.current().nextInt(1, 20);
        order.dodgy = ThreadLocalRandom.current().nextBoolean();

        Item pancake = new Item();
        pancake.item = "pancake";
        pancake.quantity = 2;
        order.items.add(pancake);

        if (ThreadLocalRandom.current().nextBoolean()) {
            Item iceCream = new Item();
            iceCream.item = "ice cream";
            iceCream.quantity = 2;
            order.items.add(iceCream);
        }

        OrderPlaced message = new OrderPlaced(order, newUUID(), null);
        log.info("[{}] Placing an order", message.correlationId);
        publisher.publish(message);
        return order.orderId;
    }
}
