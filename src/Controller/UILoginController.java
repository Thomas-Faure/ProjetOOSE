package Controller;

import login.SessionFacade;

public class UILoginController {
	
	SessionFacade session;
	public UILoginController() {
		
	}
	public boolean login(String username,String password) {
		session = new SessionFacade();
		return session.login(username, password);
		
	}

}
