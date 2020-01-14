package DAO;
/**
 *
 * @author Thomas Faure
 */
import DAO.Announcement.IAnnouncementDAO;
import DAO.Announcement.AnnouncementDAOMySQL;
import DAO.Chat.ChatDAOMySQL;
import DAO.Chat.IChatDAO;
import DAO.Idea.IIdeaDAO;
import DAO.Idea.IdeaDAOMySQL;
import DAO.Meeting.IMeetingDAO;
import DAO.Meeting.MeetingDAOMySQL;
import DAO.Message.IMessageDAO;
import DAO.Message.MessageDAOMySQL;
import DAO.Project.IProjectDAO;
import DAO.Project.ProjectDAOMySQL;
import DAO.Resource.IResourceDAO;
import DAO.Resource.ResourceDAOMySQL;
import DAO.Role.IRoleDAO;
import DAO.Role.RoleDAOMySQL;
import DAO.Sprint.ISprintDAO;
import DAO.Sprint.SprintDAOMySQL;
import DAO.Task.ITaskDAO;
import DAO.Task.TaskDAOMySQL;
import DAO.Ticket.ITicketDAO;
import DAO.Ticket.TicketDAOMySQL;
import DAO.User.GlobalUser.IGlobalUserDAO;
import DAO.User.GlobalUser.GlobalUserDAOMySQL;
import DAO.User.Member.IMemberDAO;
import DAO.User.Member.MemberDAOMySQL;

/**
 * This class is the MySQLDAOFactory, to create different MySQLDAO
 */
public  class MySQLDAOFactory extends AbstractDAOFactory {

    private static MySQLDAOFactory instance;
    private MySQLDAOFactory() {

    }

    /**
     * Method to get the static instance of MySQLDAOFactory
     * @return the static instance of MySQLDAOFactory
     */
    public static MySQLDAOFactory getInstance() {
        if(instance == null) {
            instance = new MySQLDAOFactory();
        }
        return instance;
    }


    public IGlobalUserDAO getUserDAO() {
        return new GlobalUserDAOMySQL();
    }


    public IIdeaDAO getIdeaDAO() {
        return new IdeaDAOMySQL();
    }

    @Override
    public IMeetingDAO getMeetingDAO() {
        return new MeetingDAOMySQL();
    }


    public IRoleDAO getRoleDAO() {
        return new RoleDAOMySQL();
    }
    public IAnnouncementDAO getAnnouncementDAO() {
        return new AnnouncementDAOMySQL();
    }

    public ITicketDAO getTicketDAO() {
        return new TicketDAOMySQL();
    }

    public IResourceDAO getResourceDAO(){
        return new ResourceDAOMySQL();
    };

    public IChatDAO getChatDAO(){ return new ChatDAOMySQL();}

    public ISprintDAO getSprintDAO(){ return new SprintDAOMySQL();}

    @Override
    public ITaskDAO getTaskDAO() {
        return new TaskDAOMySQL();
    }

    ;
    @Override
    public IMemberDAO getMemberDAO() {
        return new MemberDAOMySQL();
    }

    @Override
    public IProjectDAO getProjectDAO() {
        return new ProjectDAOMySQL();
    }
    @Override
    public IMessageDAO getMessageDAO() {
        return new MessageDAOMySQL();
    }

    ;




}