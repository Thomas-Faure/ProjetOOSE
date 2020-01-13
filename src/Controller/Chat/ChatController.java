package Controller.Chat;


import BusinessLogic.Chat.AbstractChat;
import BusinessLogic.Message.AbstractMessage;
import BusinessLogic.Message.Message;
import BusinessLogic.Project.AbstractProject;
import BusinessLogic.User.AbstractUser;
import Controller.IController;
import Facade.ChatFacade;
import Facade.Message.MessageFacade;
import Facade.SessionFacade;
import Main.App;
import UI.Project.ReadProjectUI;
import UI.UIGlobalWithController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Guillaume Tessier
 */
public class ChatController implements Initializable, IController {

    AbstractProject project;

    @FXML
    private Text titlepath;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private ListView<AbstractUser> peopleView;

    @FXML
    private ListView<AbstractMessage> messagesView;

    @FXML
    private Button backButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button sendButton;

    private List<AbstractMessage> historiqueMessage;

    private static ObservableList<AbstractMessage> listViewTemp;
    private UIGlobalWithController ui;

    public ChatController(AbstractProject project,UIGlobalWithController ui){
        this.project = project;
        this.ui=ui;
    }


    @FXML
    void sendMessage(ActionEvent actionEvent){
        ChatFacade.getInstance().getChatByProjectId(project.getId());
        AbstractChat currentChat = ChatFacade.getInstance().getCurrentChat();
        AbstractMessage newMessage = new Message(messageTextArea.getText(),currentChat.getIdChat(), SessionFacade.getInstance().getUser());
        MessageFacade.getInstance().addMessage(newMessage);
        messageTextArea.setText("");
        update();
    }

    @FXML
    void refreshMessages(ActionEvent actionEvent){
        update();
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
        ChatFacade.getInstance().getChatByProjectId(project.getId());
        AbstractChat chat = ChatFacade.getInstance().getCurrentChat();


        List<AbstractMessage> listeElement = chat.getHistoriqueMessage();
        ObservableList<AbstractMessage> listView = FXCollections.observableArrayList(listeElement);
        listViewTemp = FXCollections.observableArrayList(listeElement);
        messagesView.setItems(listView);
        messagesView.setCellFactory(param -> new ChatController.CellMessage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlepath.setText("/Project/"+project.getName()+"/Chat");
        update();
    }


    static class CellMessage extends ListCell<AbstractMessage> {
        AbstractMessage message;
        HBox hbox = new HBox();
        Label label = new Label("");
        Pane pane = new Pane();

        public CellMessage(){
            super();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(label,pane);
        }

        @Override
        public void updateItem(AbstractMessage name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                message = name;
                label.setText(message.getCreateur().getFirstName()+" dit: "+message.getContenu());
                setGraphic(hbox);
            }
        }

    }
}
