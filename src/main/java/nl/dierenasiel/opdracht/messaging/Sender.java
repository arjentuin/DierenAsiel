package nl.dierenasiel.opdracht.messaging;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.*;

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

    public void sendMessage(String emailadres) {
        try (
                Connection connection = cf.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer publisher = session.createProducer(queue)
        ) {
            connection.start();
            String bericht = String.format("%s, \nWe hebben een dier in ons asiel geregistreerd die mogelijk aan uw interesses voldoet. We zien u graag tegemoet.", emailadres);
            TextMessage message = session.createTextMessage(bericht);
            publisher.send(message);
        } catch (JMSException e) {
            System.out.println("Error while trying to send message to queueu: " + e.getMessage());
        }
    }


}
