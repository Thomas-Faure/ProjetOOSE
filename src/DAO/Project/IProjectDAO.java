package DAO.Project;

import BusinessLogic.Project.AbstractProject;

import java.util.List;

public interface IProjectDAO {

    List<AbstractProject> getAllProjects();

    boolean delete(int id);

    boolean save(AbstractProject project);

    boolean update(AbstractProject project);

    AbstractProject getProjectById(int id);
}
