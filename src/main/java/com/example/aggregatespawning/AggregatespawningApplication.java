package com.example.aggregatespawning;

import com.thoughtworks.xstream.XStream;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AggregatespawningApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregatespawningApplication.class, args);
    }

    @Autowired
    public void configure(Serializer serializer) {
        if(serializer instanceof XStreamSerializer) {
            XStream xStream = ((XStreamSerializer)serializer).getXStream();
            XStream.setupDefaultSecurity(xStream);
            xStream.allowTypesByWildcard(new String[] { "com.example.aggregatespawning.**", "org.axonframework.**" });
        }
    }
}
