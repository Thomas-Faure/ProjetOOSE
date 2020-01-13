package BusinessLogic.Ticket;

import BusinessLogic.User.User;

import java.time.LocalDate;

public class Ticket extends AbstractTicket {
    private int id;
    private String subject;
    private String problem;
    private LocalDate dateCreation;
    private User creator;
    private String answer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket(int id, String subject, LocalDate date, String problem, User creator, String answer){
        this.id = id;
        this.subject = subject;
        this.problem = problem;
        this.dateCreation = date;
        this.creator = creator;
        this.answer = answer;
    }

    /**
     * Retourne le sujet d'un ticket
     * @author Rémi Salmi
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set le sujet d'un ticket
     * @author Rémi Salmi
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Retourne le problème d'un ticket
     * @author Rémi Salmi
     */
    public String getProblem() {
        return problem;
    }

    /**
     * Set le problème d'un ticket
     * @author Rémi Salmi
     */
    public void setProblem(String problem) {
        this.problem = problem;
    }

    /**
     * Retourne la date de création d'un ticket
     * @author Rémi Salmi
     */
    public LocalDate getDateCreation() {
        return dateCreation;
    }

    /**
     * Set la date de création d'un ticket
     * @author Rémi Salmi
     */
    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Retourne le créteur d'un ticket
     * @author Rémi Salmi
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Set le créateur d'un ticket
     * @author Rémi Salmi
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * Retourne la réponse d'un ticket
     * @author Rémi Salmi
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set la réponse d'un ticket
     * @author Rémi Salmi
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Dis si le ticket est répondu
     * @author Rémi Salmi
     */
    @Override
    public Boolean isAnswered() {
        return this.answer != null;
    }
}
