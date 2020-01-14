package Controller.Ticket;


import BusinessLogic.Ticket.AbstractTicket;
import BusinessLogic.Ticket.Ticket;
import Facade.ISessionFacade;
import Facade.SessionFacade;
import Facade.Ticket.ITicketFacade;
import Facade.Ticket.TicketFacade;
import Main.App;
import UI.Ticket.MyTicketUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import java.time.LocalDate;

/**
 * Ce controller permet de gérer l'UI d'ajout de ticket
 * @author Rémi Salmi
 */
public class AddTicketController {
    private ITicketFacade tFacade = TicketFacade.getInstance();
    private ISessionFacade sessionFacade = SessionFacade.getInstance();
    @FXML
    private TextField subject;
    @FXML
    private TextArea problem;

    /**
     * Cette méthode permet d'annuler l'ajout d'un ticket
     * @author Rémi Salmi
     */
    @FXML
    void cancel(ActionEvent actionEvent){
        MyTicketUI myTicketsPage = new MyTicketUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(myTicketsPage.loadScene().getRoot());
    }

    /**
     * Cette méthode permet d'ajouter un ticket
     * @author Rémi Salmi
     */
    @FXML
    void addNewTicket(ActionEvent actionEvent){
        AbstractTicket ticket = new Ticket(1,subject.getText(),LocalDate.now(),problem.getText(),sessionFacade.getUser(),null);
        if(tFacade.addTicket(ticket)){
            MyTicketUI myTicketsPage = new MyTicketUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(myTicketsPage.loadScene().getRoot());
        }else{
            UIError error = new UIError(new MyTicketUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }
}
