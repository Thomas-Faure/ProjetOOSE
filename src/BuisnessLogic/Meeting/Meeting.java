package BuisnessLogic.Meeting;

import java.time.LocalDate;

/**
 * Cette classe représente un objet de type Meeting, c'est à dire un rdv pour une réunion
 *
 * @author Rémi Salmi
 *
 */
public class Meeting extends AbstractMeeting {
    private int id;
    private LocalDate date;
    private String place;
    private int idProject;

    public Meeting(int id, LocalDate date, String place, int idProject) {
        this.id = id;
        this.date = date;
        this.place = place;
        this.idProject = idProject;
    }


    /**
     * Retourne l'id d'un meeting
     * @author Rémi Salmi
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * set l'id d'un meeting
     * @author Rémi Salmi
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne l'id du projet du meeting
     * @author Rémi Salmi
     */
    @Override
    public int getIdProject() {
        return idProject;
    }

    /**
     * set l'id du projet du meeting
     * @author Rémi Salmi
     */
    @Override
    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    /**
     * Retourne la date d'un meeting
     * @author Rémi Salmi
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * set la date d'un meeting
     * @author Rémi Salmi
     */
    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Retourne l'emplacement du meeting
     * @author Rémi Salmi
     */
    @Override
    public String getPlace() {
        return place;
    }

    /**
     * set l'emplacement du meeting
     * @author Rémi Salmi
     */
    @Override
    public void setPlace(String place) {
        this.place = place;
    }
}
