package Controller.Ticket;

import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.Ticket.Ticket;

import Facade.Ticket.ITicketFacade;
import Facade.Ticket.TicketFacade;
import Main.App;

import UI.Announcement.UIModifyAnnouncement;
import UI.Announcement.UIReadAnnouncement;
import UI.Task.UITaskManagement;
import UI.Ticket.AnswerTicketUI;
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

public class TicketController implements Initializable {

    @FXML
    private TextField inputSearch ;

    @FXML
    private Button addTicket ;

    @FXML
    private ListView<AbstractTicket> ticketsList ;

    private ITicketFacade tFacade = TicketFacade.getInstance();

    private static AbstractTicket toManage;

    public TicketController(){
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
            ticketsList.setItems(listViewT);

        }else{
            ticketsList.setItems(listViewTemp);
        }

    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(ticketsList != null){
            //si on peut récuperer les tickets
            if(tFacade.getAllTickets()) {
                ArrayList<Ticket> listeElement = ((ArrayList) tFacade.getListTickets());
                ObservableList<AbstractTicket> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);

                ticketsList.setItems(listView);
                ticketsList.setCellFactory(param -> new Cell());
            }
        }

    }

    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(tFacade.deleteTicket(toManage))){
            UIError error = new UIError(new UITaskManagement());
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            ticketsList.getItems().remove(toManage);
            listViewTemp.remove(toManage);
            toManage = null;
        }
    }

    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }



    static class Cell extends ListCell<AbstractTicket> {
        AbstractTicket ticket;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnR = new Button("Read");
        Button btnD = new Button("Delete");
        Button btnA = new Button("Answer");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            hbox.getChildren().addAll(img, label, pane, btnR, btnD, btnA);
            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    toManage = ticket;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
                }
            });
            btnA.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    AnswerTicketUI answerPage = new AnswerTicketUI(ticket.getId());
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(answerPage.loadScene().getRoot());

                }
            });
            btnR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    ReadTicketUI read = new ReadTicketUI(ticket.getId(), true);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());

                }
            });


        }
        @Override
        public void updateItem(AbstractTicket name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                ticket = name;

                if (ticket.getStatus()){
                    btnA.setVisible(false);
                }
                label.setText(name.getId()+" "+name.getSubject());
                setGraphic(hbox);
            }

        }

    }

}
