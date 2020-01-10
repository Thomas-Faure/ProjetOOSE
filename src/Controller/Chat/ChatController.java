package Controller.Chat;


import BuisnessLogic.Project.AbstractProject;
import Controller.IController;
import Facade.IChatFacade;
import Main.App;
import UI.Project.ProjectUI;
import UI.Project.ReadProjectUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;


public class ChatController implements Initializable, IController {

    AbstractProject project;
    private IChatFacade cFacade;

    @FXML
    private Text titlepath;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private ListView peopleView;

    @FXML
    private ListView messagesView;

    @FXML
    private Button backButton;

    @FXML
    private Button sendButton;


    public ChatController(AbstractProject project){
        this.project = project;
    }


    @FXML
    void sendMessage(ActionEvent actionEvent){
        System.out.println("MESSAGE: "+messageTextArea.getText());
    }

    @FXML
    void backToProjectView(ActionEvent actionEvent){

        ReadProjectUI readProjectUI = new ReadProjectUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(readProjectUI.loadScene().getRoot());
    }


    @Override
    public void update() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlepath.setText("/Project"+project.getName()+"/Chat");
        update();
    }
}
