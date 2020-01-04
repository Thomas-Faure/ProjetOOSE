package Main;

import UI.Login.UILogin;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        App.getInstance();
        App.setInstanceStage(stage);
        UILogin login = new UILogin();
        App.setInstanceScene(login.loadScene());
        App.getInstanceStage().show();

    }
}
