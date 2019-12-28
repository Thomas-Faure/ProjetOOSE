package DAO;

import BuisnessLogic.Sprint.AbstractSprint;
import BuisnessLogic.Task.AbstractTask;

import java.util.List;

public interface SprintDAO {
    boolean save(AbstractSprint sprint);
    boolean update(AbstractSprint sprint);
    boolean delete(int sprintID);
    AbstractSprint getSprintById(int sprintID);
    List<AbstractSprint> getAllSprintByProject(int projectID);
}
