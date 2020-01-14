package Facade.Project;

import BusinessLogic.Project.AbstractProject;

import java.util.List;

public interface IProjectFacade {
    boolean getAllProjects();

    List<AbstractProject> getListProjects();

    boolean deleteProject(AbstractProject project);

    AbstractProject getProjectById(int id);

    boolean addProject(AbstractProject project);

    boolean update(AbstractProject project);
}
