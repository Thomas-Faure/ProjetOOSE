package Facade.Sprint;

import BusinessLogic.Sprint.AbstractSprint;

import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public interface ISprintFacade {

    /**
     * Access to the DAO to insert a new sprint in the database
     * @param sprint
     * @return true or false, depend if the action succeed
     */
    boolean addSprint(AbstractSprint sprint);

    /**
     * Access to the DAO to update a sprint in the database
     * @param sprint
     * @return true or false, depend if the action succeed
     */
    boolean updateSprint(AbstractSprint sprint);

    /**
     * Access to the DAO to delete a sprint in the database
     * @param sprintID
     * @return true or false, depend if the action succeed
     */
    boolean deleteSprint(int sprintID);

    /**
     * Access to the DAO to get a sprint with the id
     * @param sprintID
     * @return a AbstractSprint
     */
    AbstractSprint getSprintById(int sprintID);

    /**
     * Access to the DAO to get all the sprints relative to a project
     * @param projectID
     * @return a list of AbstractSprint
     */
    List<AbstractSprint> getListSprintByProject(int projectID);
}
