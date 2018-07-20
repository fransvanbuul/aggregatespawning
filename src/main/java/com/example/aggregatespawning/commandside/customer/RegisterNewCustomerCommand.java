package com.example.aggregatespawning.commandside.customer;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class RegisterNewCustomerCommand {

    @TargetAggregateIdentifier UUID customerId;
    String firstName;
    String lastName;

}
