package nl.dierenasiel.opdracht.config;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

public class JmsConfig {
    @Produces
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory cf;

    @Produces
    @Resource(lookup = "java:/jms/queue/animalQueue")
    private Queue queue;
}
