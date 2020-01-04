package UI.User.Global;

import BuisnessLogic.User.GlobalUser;
import BuisnessLogic.User.User;
import Controller.User.GlobalUser.AccountController;
import Facade.SessionFacade;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class MyAccountUI {
    private GlobalUser user = new GlobalUser();

    public MyAccountUI(){
        User sessionUser =  SessionFacade.getInstance().getUser();
        /*System.out.println("SESSION");
        System.out.println(sessionUser.getId() + sessionUser.getUsername());

         */
        this.user.setId(sessionUser.getId());
        this.user.setUsername(sessionUser.getUsername());
        this.user.setPassword(sessionUser.getPassword());
        this.user.setFirstName(sessionUser.getFirstName());
        this.user.setLastName(sessionUser.getLastName());
        this.user.setCity(sessionUser.getCity());
        this.user.setPhoneNumber(sessionUser.getPhoneNumber());
        this.user.setEmail(sessionUser.getEmail());
        this.user.setPosition(sessionUser.getPosition());
        this.user.setAdmin(sessionUser.isAdmin());
    }

    public Scene loadScene() {
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(AccountController.class , new Callable<AccountController>() {
            public AccountController call() throws Exception {
                /*System.out.println("MyAccountUI");
                System.out.println(user.getId() + user.getUsername());

                 */
                return new AccountController(user);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyAccountUI.fxml"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> param) {
                    Callable<?> callable = creators.get(param);
                    if (callable == null) {
                        try {
                            // default handling: use no-arg constructor
                            return param.newInstance();
                        } catch (InstantiationException | IllegalAccessException ex) {
                            throw new IllegalStateException(ex);
                        }
                    } else {
                        try {
                            return callable.call();
                        } catch (Exception ex) {
                            throw new IllegalStateException(ex);
                        }
                    }
                }
            });
            root = loader.load();
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }
}
