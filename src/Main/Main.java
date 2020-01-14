package Main;
/**
 *
 * @author Thomas Faure
 */
import UI.Login.UILogin;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is to launch the Application
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        App.getInstance();
        stage.setResizable(false);
        App.setInstanceStage(stage);
        UILogin login = new UILogin();
        App.setInstanceScene(login.loadScene());
        App.getInstanceStage().show();

    }
}
