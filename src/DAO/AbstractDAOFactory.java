package DAO;
/**
 *
 * @author Thomas Faure
 */
import DAO.Announcement.AnnouncementDAO;
import DAO.Idea.IdeaDAO;
import DAO.Meeting.MeetingDAO;
import DAO.Message.MessageDAO;
import DAO.Project.ProjectDAO;
import DAO.Role.RoleDAO;
import DAO.Task.TaskDAO;
import DAO.Ticket.TicketDAO;
import DAO.User.Member.MemberDAO;

public abstract class AbstractDAOFactory {
	protected abstract AnnouncementDAO createAnnouncementDAO();
	protected abstract ChatDAO createChatDAO();
	protected abstract IdeaDAO createIdeaDAO();
	protected abstract MeetingDAO createMeetingDAO();
	protected abstract MemberDAO createMemberDAO();
	protected abstract ProjectDAO createProjectDAO();
	protected abstract RessourceDAO createRessourceDAO();
	protected abstract RoleDAO createRoleDAO();
	protected abstract SprintDAO createSprintDAO();
	protected abstract TaskDAO createTaskDAO();
	protected abstract TicketDAO createTicketDAO();
	protected abstract MessageDAO createMessageDAO();
}
