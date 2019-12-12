package DAO;

public abstract class AbstractDAOFactory {
	protected abstract UserDAO createUserDAO();
	protected abstract AnnouncementDAO createAnnouncementDAO();
	protected abstract ChatDAO createChatDAO();
	protected abstract IdeaDAO createIdeaDAO();
	protected abstract MeetingDAO createMeetingDAO();
	protected abstract MemberDAO createMemberUDAO();
	protected abstract ProjectDAO createProjectDAO();
	protected abstract RessourceDAO createRessourceDAO();
	protected abstract RoleDAO createRoleDAO();
	protected abstract SprintDAO createSprintDAO();
	protected abstract TaskDAO createTaskDAO();
	protected abstract TicketDAO createTicketDAO();
}
