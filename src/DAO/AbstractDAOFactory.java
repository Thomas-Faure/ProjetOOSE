package DAO;

import DAO.Announcement.IAnnouncementDAO;
import DAO.Chat.IChatDAO;
import DAO.Idea.IIdeaDAO;
import DAO.Meeting.IMeetingDAO;
import DAO.Message.IMessageDAO;
import DAO.Project.IProjectDAO;
import DAO.Resource.IResourceDAO;
import DAO.Role.IRoleDAO;
import DAO.Sprint.ISprintDAO;
import DAO.Task.ITaskDAO;
import DAO.Ticket.ITicketDAO;
import DAO.User.Member.IMemberDAO;
/**
 *	Abstract class of the DaoFactory
 * @author Thomas Faure
 */
public abstract class AbstractDAOFactory {
    /**Method to create an announcementDAO
     * @return an AnnouncementDAO
     */
    protected abstract IAnnouncementDAO getAnnouncementDAO();
    /**Method to create a ChatDAO
     * @return an ChatDAO
     */
    protected abstract IChatDAO getChatDAO();
    /**Method to create an IdeaDAO
     * @return an IdeaDAO
     */
    protected abstract IIdeaDAO getIdeaDAO();
    /**Method to create an MeetingDAO
     * @return an MeetingDAO
     */
    protected abstract IMeetingDAO getMeetingDAO();
    /**Method to create an MemberDAO
     * @return an MemberDAO
     */
    protected abstract IMemberDAO getMemberDAO();
    /**Method to create an ProjectDAO
     * @return an ProjectDAO
     */
    protected abstract IProjectDAO getProjectDAO();
    /**Method to create an RessourceDAO
     * @return an RessourceDAO
     */
    protected abstract IResourceDAO getResourceDAO();
    /**Method to create an RoleDAO
     * @return an RoleDAO
     */
    protected abstract IRoleDAO getRoleDAO();
    /**Method to create an SprintDAO
     * @return an SprintDAO
     */
    protected abstract ISprintDAO getSprintDAO();
    /**Method to create an TaskDAO
     * @return an TaskDAO
     */
    protected abstract ITaskDAO getTaskDAO();
    /**Method to create an TicketDAO
     * @return an TicketDAO
     */
    protected abstract ITicketDAO getTicketDAO();

    protected abstract IMessageDAO getMessageDAO();
}
