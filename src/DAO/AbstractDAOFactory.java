package DAO;

public abstract class AbstractDAOFactory {
	public abstract UserDAO createUserDAO();
	public abstract AnnouncementDAO createAnnouncementDAO();
	public abstract ChatDAO createChatDAO();
	public abstract IdeaDAO createIdeaDAO();
	public abstract MeetingDAO createMeetingDAO();
	public abstract MemberDAO createMemberUDAO();
	public abstract ProjectDAO createProjectDAO();
	public abstract RessourceDAO createRessourceDAO();
	public abstract RoleDAO createRoleDAO();
	public abstract SprintDAO createSprintDAO();
	public abstract TaskDAO createTaskDAO();
	public abstract TicketDAO createTicketDAO();



}
