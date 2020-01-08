package BuisnessLogic.Ticket;

import BuisnessLogic.User.User;

import java.time.LocalDate;
import java.util.Date;

public abstract class AbstractTicket {

    public abstract String getSubject();

    public abstract void setSubject(String subject);

    public abstract String getProblem();

    public abstract void setProblem(String problem);

    public abstract LocalDate getDateCreation();

    public abstract void setDateCreation(LocalDate dateCreation);

    public abstract User getCreator();

    public abstract void  setCreator(User creator);

    public abstract String getAnswer();

    public abstract void setAnswer(String answer);

    public abstract Boolean isAnswered();

    public abstract int getId();
}
