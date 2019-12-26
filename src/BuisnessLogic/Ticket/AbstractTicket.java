package BuisnessLogic.Ticket;

import BuisnessLogic.User.User;

import java.util.Date;

public abstract class AbstractTicket {

    public abstract String getSubject();

    public abstract void setSubject(String subject);

    public abstract String getProblem();

    public abstract void setProblem(String problem);

    public abstract Boolean getStatus();

    public abstract void setStatus(Boolean status);

    public abstract Date getDateCreation();

    public abstract void setDateCreation(Date dateCreation);

    public abstract User getCreator();

    public abstract void  setCreator(User creator);

    public abstract String getAnswer();

    public abstract void setAnswer(String answer);
}
