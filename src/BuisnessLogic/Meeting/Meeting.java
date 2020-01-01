package BuisnessLogic.Meeting;

import java.time.LocalDate;
import java.util.Date;

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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getIdProject() {
        return idProject;
    }

    @Override
    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String getPlace() {
        return place;
    }

    @Override
    public void setPlace(String place) {
        this.place = place;
    }
}
