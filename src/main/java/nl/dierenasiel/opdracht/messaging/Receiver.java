package nl.dierenasiel.opdracht.messaging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Stateless
@MessageDriven(
        name = "AnimalMessageHandler",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType",
                        propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination",
                        propertyValue = "/queue/animalQueue")
        }
)
public class Receiver implements MessageListener {


    @Override
    public void onMessage(Message message) {

        try {
            System.out.println("Message received!  " + message.getBody(String.class));
//            animalService.addAnimal(message.getBody(Animal.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
