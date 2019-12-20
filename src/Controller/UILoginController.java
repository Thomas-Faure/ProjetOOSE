package Controller;


import Facade.SessionFacade;

public class UILoginController {

	public static UILoginController instance;
	SessionFacade session;
	private UILoginController(){

	}
	public static UILoginController getInstance(){
		if(instance == null){
			instance= new UILoginController();
		}
		return instance;
	}

	public static boolean login(String username,String password) {


		return SessionFacade.getInstance().login(username, password);
		
	}

}
