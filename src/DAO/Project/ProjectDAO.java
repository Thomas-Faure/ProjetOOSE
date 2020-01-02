package DAO.Project;

import BuisnessLogic.Project.AbstractProject;

import java.util.List;

public interface ProjectDAO {

    List<AbstractProject> getAllProjects();

    boolean delete(int id);

    boolean save(AbstractProject project);

    boolean update(AbstractProject project);

    AbstractProject getProjectById(int id);
}
