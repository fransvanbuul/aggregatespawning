package com.example.aggregatespawning.commandside.order;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class ConfirmOrderCommand {

    @TargetAggregateIdentifier UUID orderId;
}
