package BuisnessLogic.Ticket;

import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.User.User;

import java.util.Date;

public class Ticket extends AbstractTicket {
    private String subject;
    private String problem;
    private Boolean status;
    private Date dateCreation;
    private User creator;
    private String answer;
}
