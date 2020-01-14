package BusinessLogic.Task;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Project.AbstractProject;
import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.User;

import java.time.LocalDate;

/**
 *
 * The task class (here the abstract)
 *
 * @author Thomas Faure
 */
public abstract class AbstractTask {
    /**Method to get the id of a task
     * @return
     */
    public abstract int getId();

    /**Method to get the name of a task
     * @return
     */
    public abstract String getName();

    /**Method to get the priority of a task
     * @return
     */
    public abstract int getPriority();

    /**Method to get the deadline of a task
     * @return
     */
    public abstract LocalDate getDeadline();

    /**Method to get the creator of a task
     * @return
     */
    public abstract AbstractUser getCreator();

    /**Method to get the description of a task
     * @return
     */
    public abstract String getDescription();

    /**Method to get the current state (to string) of a task
     * @return
     */
    public abstract  String getStateString();

    /**Method to set the state of a task
     * @param state
     */
    public abstract void setState(TaskState state);

    /**Method to get the state of a task
     * @return
     */
    public abstract TaskState getState();

    /**Method to set the id of a task
     * @param id
     */
    public abstract void setId(int id);

    /**Method to set the name of a staks
     * @param name
     */
    public abstract void setName(String name);

    /**Method to set the priority of a task
     * @param priority
     */
    public abstract void setPriority(int priority);

    /**Method to set the deadline of a task
     * @param deadline
     */
    public abstract void setDeadline(LocalDate deadline);

    /**Method to set the creator of a task
     * @param creator
     */
    public abstract void setCreator(User creator);

    /**Method to set the description of a task
     * @param description
     */
    public abstract void setDescription(String description);

    /**Method to set the project of a task
     * @param project
     */
    public abstract void setProject(AbstractProject project);

    /**Method to get the project of a task
     * @return
     */
    public abstract AbstractProject getProject();

    /**Methof to get the sprint's id of a task
     * @return
     */
    public abstract Integer getIdSprint();

    /**Method to set the sprint's id of a task
     * @param id
     */
    public abstract void setIdSprint(int id);


}
