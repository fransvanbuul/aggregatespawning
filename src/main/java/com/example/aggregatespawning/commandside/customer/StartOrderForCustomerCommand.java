package com.example.aggregatespawning.commandside.customer;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class StartOrderForCustomerCommand {

    @TargetAggregateIdentifier UUID customerId;
    UUID orderId;

}
