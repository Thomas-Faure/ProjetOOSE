package Controller.Ticket;

import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.Ticket.Ticket;
import Facade.ISessionFacade;
import Facade.SessionFacade;
import Facade.Ticket.ITicketFacade;
import Facade.Ticket.TicketFacade;
import Main.App;
import UI.Announcement.UIReadAnnouncement;
import UI.Ticket.AddTicketUI;
import UI.Ticket.ReadTicketUI;
import UI.UIError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyTicketController implements Initializable {
    @FXML
    private TextField inputSearch ;

    @FXML
    private Button addTicket ;

    @FXML
    private ListView<AbstractTicket> myTicketsList ;

    private ITicketFacade tFacade = TicketFacade.getInstance();
    private ISessionFacade sessionFacade = SessionFacade.getInstance();

    private static AbstractTicket toManage;

    public MyTicketController(){
    }

    //permet de garder la liste de base
    private static ObservableList<AbstractTicket> listViewTemp;
    @FXML
    public void searchBar(KeyEvent keyEvent) {

        if(!(inputSearch.getText().length() == 0)) {
            ArrayList<AbstractTicket> array = new ArrayList<>(listViewTemp);
            ArrayList<AbstractTicket> toDelete = new ArrayList<>();
            for (int i = 0; i < array.size(); ++i) {
                String inputS =inputSearch.getText();
                if(inputS.charAt(0) == '*'){
                    inputS= "\\"+inputS;
                }
                String regex = "(.*)" + inputS + "(.*)";
                if (array.get(i).getSubject().matches(regex)) {

                } else {
                    toDelete.add(array.get(i));
                }
            }

            for (AbstractTicket i : toDelete) {
                array.remove(i);
            }
            ObservableList<AbstractTicket> listViewT = FXCollections.observableArrayList(array);
            myTicketsList.setItems(listViewT);

        }else{
            myTicketsList.setItems(listViewTemp);
        }

    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(myTicketsList != null){
            //si on peut récuperer les tickets
            if(tFacade.getMyTickets(sessionFacade.getUser())) {
                ArrayList<Ticket> listeElement = ((ArrayList) tFacade.getListTickets());
                ObservableList<AbstractTicket> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);

                myTicketsList.setItems(listView);
                myTicketsList.setCellFactory(param -> new MyTicketController.Cell());
            }
        }

    }

    static class Cell extends ListCell<AbstractTicket> {
        AbstractTicket ticket;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnR = new Button("Read");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            hbox.getChildren().addAll(img, label, pane, btnR);
            btnR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    ReadTicketUI read = new ReadTicketUI(ticket.getId(),false);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());

                }
            });


        }
        @Override
        public void updateItem(AbstractTicket t, boolean empty){
            super.updateItem(t,empty);
            setText(null);
            setGraphic(null);

            if(t != null && !empty){
                ticket = t;
                String status ="";
                if (t.getStatus()){
                    status = "Répondu";
                }else{
                    status = "En attente"
;                }

                label.setText(t.getId() + " " + t.getSubject() + " | " + status);
                setGraphic(hbox);
            }

        }

    }

    @FXML
    void addTicketPage(ActionEvent actionEvent) {
        AddTicketUI addTicket = new AddTicketUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTicket.loadScene().getRoot());
    }
}
