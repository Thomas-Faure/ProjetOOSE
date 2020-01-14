package Controller.Ticket;

import BusinessLogic.Ticket.AbstractTicket;
import Facade.Ticket.ITicketFacade;
import Facade.Ticket.TicketFacade;
import Main.App;
import UI.Ticket.MyTicketUI;
import UI.Ticket.TicketUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Ce controller permet de gérer l'Ui de lecture d'un ticket
 * @author Rémi Salmi
 */
public class ReadTicketController implements Initializable {

    private ITicketFacade ticketFacade = TicketFacade.getInstance();
    private int id;
    private boolean isAdmin;

    @FXML
    private Text ticketTitle;

    @FXML
    private Text pathIndication;

    @FXML
    private Text ticketFrom;

    @FXML
    private TextField subject;

    @FXML
    private TextArea problem;

    @FXML
    private TextArea answer;


    public ReadTicketController(){}
    public ReadTicketController(int id, boolean isAdmin){
        this.id=id;
        this.isAdmin = isAdmin;
    }

    /**
     * Initialisation de l'UI
     * @author Rémi Salmi
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AbstractTicket ticket = ticketFacade.getTicketById(id);
        if(ticket != null) {
            ticketTitle.setText("Ticket n° " + ticket.getId());
            subject.setText(ticket.getSubject());
            answer.setText(ticket.getAnswer());
            problem.setText(ticket.getProblem());
            if (ticket.getCreator() != null){
                ticketFrom.setText("Ticket from : " + ticket.getCreator().getFirstName() + " " + ticket.getCreator().getLastName());
            }

            if(isAdmin){
                pathIndication.setText("/Tickets/ticket n° " + ticket.getId());
            }else{
                pathIndication.setText("/My tickets/ticket n° " + ticket.getId());
            }

        }else{
            UIError error;
            if(isAdmin){
                error = new UIError(new TicketUI());
            }else{
                error = new UIError(new MyTicketUI());
            }
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }
    }

    /**
     * Permet de retourner à la page précédente
     * @author Rémi Salmi
     */
    @FXML
    public void back(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        if(isAdmin) {
            TicketUI ticketPage = new TicketUI();
            box.getChildren().add(ticketPage.loadScene().getRoot());
        }else{
            MyTicketUI ticketPage = new MyTicketUI();
            box.getChildren().add(ticketPage.loadScene().getRoot());
        }
    }
}
