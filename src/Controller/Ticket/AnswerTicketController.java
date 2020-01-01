package Controller.Ticket;

import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.Ticket.Ticket;
import Facade.Ticket.ITicketFacade;
import Facade.Ticket.TicketFacade;
import Main.App;
import UI.Ticket.AnswerTicketUI;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

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
    public void initialize(URL arg0, ResourceBundle arg1) {
        AbstractTicket ticket = ticketFacade.getTicketById(id);
        if(ticket != null) {
            ticketTitle.setText("Ticket n° " + ticket.getId());
            subject.setText(ticket.getSubject());
            problem.setText(ticket.getProblem());
            if (ticket.getCreator() != null){
                ticketFrom.setText(ticket.getCreator().getFirstName() + " " + ticket.getCreator().getLastName());
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

    @FXML
    public void back(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        TicketUI ticketPage = new TicketUI();
        box.getChildren().add(ticketPage.loadScene().getRoot());
    }

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
