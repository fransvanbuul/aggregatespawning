package com.example.aggregatespawning.commandside.customer;

import com.example.aggregatespawning.commandside.order.OrderAggregate;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.createNew;

@Aggregate
@NoArgsConstructor
@XSlf4j
public class CustomerAggregate {

    @AggregateIdentifier
    private UUID id;

    @CommandHandler
    public CustomerAggregate(RegisterNewCustomerCommand cmd) {
        log.entry(cmd);
        apply(new NewCustomerRegisteredEvent(cmd.getCustomerId(), cmd.getFirstName(), cmd.getLastName()));
    }

    @CommandHandler
    public void handle(StartOrderForCustomerCommand cmd) {
        log.entry(cmd);
        try {
            createNew(OrderAggregate.class, () -> new OrderAggregate(cmd.getCustomerId(), cmd.getOrderId()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @EventSourcingHandler
    public void on(NewCustomerRegisteredEvent evt) {
        log.entry(evt);
        id = evt.getCustomerId();
    }

}
