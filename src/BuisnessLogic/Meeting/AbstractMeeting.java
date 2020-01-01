package BuisnessLogic.Meeting;

import java.time.LocalDate;

public abstract class AbstractMeeting {

    public abstract LocalDate getDate();
    public abstract String getPlace();
    public abstract void setDate(LocalDate date);
    public abstract void setPlace(String place);
    public abstract void setId(int id);
    public abstract int getId();
    public abstract void setIdProject(int idProject);
    public abstract int getIdProject();
}
