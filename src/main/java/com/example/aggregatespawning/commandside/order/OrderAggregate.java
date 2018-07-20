package com.example.aggregatespawning.commandside.order;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@XSlf4j
@NoArgsConstructor
public class OrderAggregate {

    private UUID customerId;
    @AggregateIdentifier private UUID orderId;
    private boolean confirmed;

    public OrderAggregate(UUID customerId, UUID orderId) {
        log.entry(customerId, orderId);
        apply(new OrderCreatedEvent(customerId, orderId));
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand cmd) {
        log.entry(cmd);
        if(confirmed) throw new IllegalStateException("order already confirmed");
        apply(new OrderConfirmedEvent());
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent evt) {
        log.entry(evt);
        customerId = evt.getCustomerId();
        orderId = evt.getOrderId();
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent evt) {
        log.entry(evt);
        confirmed = true;
    }

}
