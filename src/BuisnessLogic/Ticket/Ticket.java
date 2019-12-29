package BuisnessLogic.Ticket;

import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.User.User;

import java.time.LocalDate;
import java.util.Date;

public class Ticket extends AbstractTicket {
    private int id;
    private String subject;
    private String problem;
    private Boolean status;
    private LocalDate dateCreation;
    private User creator;
    private String answer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket(int id, String subject, Boolean status, LocalDate date, String problem, User creator, String answer){
        this.id = id;
        this.subject = subject;
        this.problem = problem;
        this.status = status;
        this.dateCreation = date;
        this.creator = creator;
        this.answer = answer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
