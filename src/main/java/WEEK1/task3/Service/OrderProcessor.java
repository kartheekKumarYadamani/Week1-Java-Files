package WEEK1.task3.Service;

import WEEK1.task3.Entity.Order;

@FunctionalInterface
public interface OrderProcessor {

    Order process(Order order);

    default OrderProcessor andThen(
            OrderProcessor nextProcessor) {

        return order ->
                nextProcessor.process(
                        this.process(order)
                );
    }
}