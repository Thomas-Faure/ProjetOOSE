package DAO;

import DAO.Announcement.AnnouncementDAO;
import DAO.Idea.IdeaDAO;
import DAO.Meeting.MeetingDAO;
import DAO.Project.ProjectDAO;
import DAO.Role.RoleDAO;
import DAO.Task.TaskDAO;
import DAO.Ticket.TicketDAO;
import DAO.User.Member.MemberDAO;
/**
 *	Abstract class of the DaoFactory
 * @author Thomas Faure
 */
public abstract class AbstractDAOFactory {
    /**Method to create an announcementDAO
     * @return an AnnouncementDAO
     */
    protected abstract AnnouncementDAO getAnnouncementDAO();
    /**Method to create a ChatDAO
     * @return an ChatDAO
     */
    protected abstract ChatDAO getChatDAO();
    /**Method to create an IdeaDAO
     * @return an IdeaDAO
     */
    protected abstract IdeaDAO getIdeaDAO();
    /**Method to create an MeetingDAO
     * @return an MeetingDAO
     */
    protected abstract MeetingDAO getMeetingDAO();
    /**Method to create an MemberDAO
     * @return an MemberDAO
     */
    protected abstract MemberDAO getMemberDAO();
    /**Method to create an ProjectDAO
     * @return an ProjectDAO
     */
    protected abstract ProjectDAO getProjectDAO();
    /**Method to create an RessourceDAO
     * @return an RessourceDAO
     */
    protected abstract RessourceDAO getResourceDAO();
    /**Method to create an RoleDAO
     * @return an RoleDAO
     */
    protected abstract RoleDAO getRoleDAO();
    /**Method to create an SprintDAO
     * @return an SprintDAO
     */
    protected abstract SprintDAO getSprintDAO();
    /**Method to create an TaskDAO
     * @return an TaskDAO
     */
    protected abstract TaskDAO getTaskDAO();
    /**Method to create an TicketDAO
     * @return an TicketDAO
     */
    protected abstract TicketDAO getTicketDAO();
}
