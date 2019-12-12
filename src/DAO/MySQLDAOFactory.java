package DAO;

public  class MySQLDAOFactory extends AbstractDAOFactory {

	private static MySQLDAOFactory instance;
	private MySQLDAOFactory() {
		
	}
	public static MySQLDAOFactory getInstance() {
		if(instance == null) {
			instance = new MySQLDAOFactory();
		}
		return instance;
	}
	//mettre ici la connexion JDBC
	@Override
	public UserDAO createUserDAO() {
		return new UserDAOMySQL();
	}

	@Override
	public AnnouncementDAO createAnnouncementDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatDAO createChatDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdeaDAO createIdeaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeetingDAO createMeetingDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDAO createMemberUDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectDAO createProjectDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RessourceDAO createRessourceDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDAO createRoleDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SprintDAO createSprintDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskDAO createTaskDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TicketDAO createTicketDAO() {
		// TODO Auto-generated method stub
		return null;
	}



	
	
	

}
