package BuisnessLogic.Task;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;

import java.time.LocalDate;

public abstract class AbstractTask {
    public abstract int getId();
    public abstract String getName();
    public abstract int getPriority();
    public abstract LocalDate getDeadline();
    public abstract AbstractUser getCreator();
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
    public abstract void setProject(AbstractProject project);
    public abstract AbstractProject getProject();
    public abstract Integer getIdSprint();
    public abstract void setIdSprint(int id);


}
