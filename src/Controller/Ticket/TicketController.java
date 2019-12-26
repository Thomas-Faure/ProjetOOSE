package Controller.Ticket;

import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Ticket.Ticket;

import Facade.ITicketFacade;
import Facade.TicketFacade;
import Main.App;

import UI.Announcement.UIModifyAnnouncement;
import UI.Announcement.UIReadAnnouncement;
import UI.Confirm.UIConfirm;
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
    private ListView ticketsList ;

    private ITicketFacade tFacade = TicketFacade.getInstance();

    public TicketController(){
    }

    //permet de garder la liste de base
    private static ObservableList<Ticket> listViewTemp;
    @FXML
    public void searchBar(KeyEvent keyEvent) {

        if(!(inputSearch.getText().length() == 0)) {
            ArrayList<Ticket> array = new ArrayList<>(listViewTemp);
            ArrayList<Ticket> toDelete = new ArrayList<>();
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

            for (Ticket i : toDelete) {
                array.remove(i);
            }
            ObservableList<Ticket> listViewT = FXCollections.observableArrayList(array);
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
                ArrayList<Ticket> listeElement = ((ArrayList) TicketFacade.getInstance().getListTickets());
                ObservableList<Ticket> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);

                ticketsList.setItems(listView);
                ticketsList.setCellFactory(param -> new Cell());
            }
        }

    }

    static class Cell extends ListCell<Ticket> {
        Ticket ticket;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnR = new Button("Read");
        Button btnD = new Button("Delete");
        Button btnM = new Button("Modify");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            hbox.getChildren().addAll(img, label, pane, btnR, btnM, btnD);
            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    getListView().getItems().remove(getItem());
                    listViewTemp.remove(getItem());
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    UIConfirm taskPage = new UIConfirm("Ticket", "Delete", ticket, box.getChildren().get(1));
                    box.getChildren().add(taskPage.loadScene().getRoot());
                    if (box.getChildren().size() > 1)
                        box.getChildren().remove(1);


                }
            });
            btnM.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    UIModifyAnnouncement modifyAnnouncement = new UIModifyAnnouncement(ticket.getId());
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(modifyAnnouncement.loadScene().getRoot());

                }
            });
            btnR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    UIReadAnnouncement read = new UIReadAnnouncement(ticket.getId(), true);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());

                }
            });


        }
        @Override
        public void updateItem(Ticket name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);

            if(name != null && !empty){
                ticket = name;
                label.setText(name.getId()+" "+name.getProblem());
                setGraphic(hbox);
            }

        }

    }
}
