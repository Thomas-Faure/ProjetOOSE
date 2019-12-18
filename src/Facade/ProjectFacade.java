package Facade;

import BuisnessLogic.Project.AbstractProject;
import DAO.AbstractDAOFactory;

public class ProjectFacade implements IProjectFacade {
    private AbstractProject[] projects;
    private AbstractDAOFactory daoFactory;
}
