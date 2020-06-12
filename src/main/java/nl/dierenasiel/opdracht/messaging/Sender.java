package nl.dierenasiel.opdracht.messaging;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.*;
import javax.naming.NamingException;

@ApplicationScoped
public class Sender {

    private ConnectionFactory cf;
    private Queue queue;

    public Sender() {
    }

    @Inject
    public Sender(ConnectionFactory cf, Queue queue) {
        this.cf = cf;
        this.queue = queue;
    }

    public void sendMessage(String bericht) throws JMSException, NamingException {
        try (
                Connection connection = cf.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer publisher = session.createProducer(queue)
        ) {
            connection.start();

            TextMessage message = session.createTextMessage(bericht);

            publisher.send(message);
        } catch (JMSException e) {
            System.out.println("Error while trying to send message to queueu: " + e.getMessage());
        }
    }


}
