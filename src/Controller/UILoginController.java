package Controller;


import Facade.SessionFacade;

public class UILoginController {
	
	SessionFacade session;
	public UILoginController() {
		
	}
	public boolean login(String username,String password) {

		return SessionFacade.getInstance().login(username, password);
		
	}

}
