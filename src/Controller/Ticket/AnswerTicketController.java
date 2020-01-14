package Controller.Ticket;

import BusinessLogic.Ticket.AbstractTicket;
import Facade.Ticket.ITicketFacade;
import Facade.Ticket.TicketFacade;
import Main.App;
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
 * Ce controller permet de gérer l'UI de réponse à un ticket
 * @author Rémi Salmi
 */
public class AnswerTicketController implements Initializable {

    private ITicketFacade ticketFacade = TicketFacade.getInstance();
    private int id;


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


    public AnswerTicketController(){}
    public AnswerTicketController(int id){
        this.id=id;
    }


    @Override
    /**
     * Initialisation de l'UI
     * @author Rémi Salmi
     */
    public void initialize(URL arg0, ResourceBundle arg1) {
        AbstractTicket ticket = ticketFacade.getTicketById(id);
        if(ticket != null) {
            ticketTitle.setText("Ticket n° " + ticket.getId());
            subject.setText(ticket.getSubject());
            problem.setText(ticket.getProblem());
            if (ticket.getCreator() != null){
                ticketFrom.setText("Ticket from : " + ticket.getCreator().getFirstName() + " " + ticket.getCreator().getLastName());
            }
            pathIndication.setText("/Tickets/ticket n° " + ticket.getId());


        }else{
            UIError error = new UIError(new TicketUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }
    }

    /**
     * Permet de retrouner à la page précédente
     * @author Rémi Salmi
     */
    @FXML
    public void back(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        TicketUI ticketPage = new TicketUI();
        box.getChildren().add(ticketPage.loadScene().getRoot());
    }

    /**
     * Permet de répondre au ticket
     * @author Rémi Salmi
     */
    @FXML
    void answer(ActionEvent actionEvent){
        AbstractTicket ticket = ticketFacade.getTicketById(id);
        if(ticketFacade.answer(ticket, answer.getText())){
            TicketUI TicketsPage = new TicketUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(TicketsPage.loadScene().getRoot());
        }else{
            UIError error = new UIError(new TicketUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }
}
