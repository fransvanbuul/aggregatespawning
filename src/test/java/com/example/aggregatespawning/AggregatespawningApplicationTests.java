package com.example.aggregatespawning;

import com.example.aggregatespawning.commandside.customer.RegisterNewCustomerCommand;
import com.example.aggregatespawning.commandside.customer.StartOrderForCustomerCommand;
import com.example.aggregatespawning.commandside.order.ConfirmOrderCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@XSlf4j
public class AggregatespawningApplicationTests {

    @Autowired
    private CommandGateway commandGateway;

    @Test
    public void simpleTest() {
        log.info("Registering new customer");
        UUID customerId = UUID.randomUUID();
        commandGateway.sendAndWait(new RegisterNewCustomerCommand(customerId, "Frans", "van Buul"));

        log.info("Starting order for customer");
        UUID orderId = UUID.randomUUID();
        commandGateway.sendAndWait(new StartOrderForCustomerCommand(customerId, orderId));

        log.info("Completing order");
        commandGateway.sendAndWait(new ConfirmOrderCommand(orderId));
    }

}
