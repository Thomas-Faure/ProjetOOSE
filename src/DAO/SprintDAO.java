package DAO;

import BusinessLogic.Sprint.AbstractSprint;

import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public interface SprintDAO {

    /**
     * Insert a new sprint in the database
     * @param sprint
     * @return true or false, depend if the action succeed
     */
    boolean save(AbstractSprint sprint);

    /**
     * Update a sprint in the database
     * @param sprint
     * @return true or false, depend if the action succeed
     */
    boolean update(AbstractSprint sprint);

    /**
     * Delete a sprint in the database
     * @param sprintID
     * @return true or false, depend if the action succeed
     */
    boolean delete(int sprintID);

    /**
     * Found a sprint in the database and return them
     * @param sprintID
     * @return a AbstractSprint
     */
    AbstractSprint getSprintById(int sprintID);

    /**
     * Found all the sprints relatives to a project in the database and return all of them in a list
     * @param projectID
     * @return a list of AbstractSprint
     */
    List<AbstractSprint> getAllSprintByProject(int projectID);
}
