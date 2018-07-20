package com.example.aggregatespawning.commandside.order;

import lombok.Value;

import java.util.UUID;

@Value
public class OrderCreatedEvent {

    UUID customerId;
    UUID orderId;

}
