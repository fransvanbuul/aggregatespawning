# aggregatespawning
Small example of Axon 3.3 aggregate spawning functionality.

This example has two aggregates: a Customer and an Order. In this case, the Orders cannot appear out of thin air.
They can only be spawned by an already registered Customer.

There is no GUI or REST interface for this example, there's only a unit test showing how this works.
