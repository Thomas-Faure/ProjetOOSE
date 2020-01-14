package Test.Business;

import BusinessLogic.Ticket.AbstractTicket;
import BusinessLogic.Ticket.Ticket;
import BusinessLogic.User.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Rémi Salmi
 */
public class TicketTest {

    @Test
    void isAnswered(){
        User user = new User(1,"name","mdp","first","last","city","00000000","mail","pos",false);
        AbstractTicket ticket = new Ticket(1,"sujet",LocalDate.now(),"problem",user,"answer");
        AbstractTicket ticket2 = new Ticket(1,"sujet",LocalDate.now(),"problem",user,null);
        assertTrue(ticket.isAnswered());
        assertFalse(ticket2.isAnswered());
    }
}
