package BuisnessLogic.Task;

import BuisnessLogic.User.User;

import java.time.LocalDate;

public abstract class AbstractTask {
    public abstract int getId();
    public abstract String getName();
    public abstract int getPriority();
    public abstract LocalDate getDeadline();
    public abstract User getCreator();
    public abstract String getDescription();
    public abstract  String getStateString();
    public abstract void setState(TaskState state);
    public abstract TaskState getState();
    public abstract void setId(int id);
    public abstract void setName(String name);
    public abstract void setPriority(int priority);
    public abstract void setDeadline(LocalDate deadline);
    public abstract void setCreator(User creator);
    public abstract void setDescription(String description);


}
