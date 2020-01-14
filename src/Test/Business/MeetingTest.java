package Test.Business;

import BusinessLogic.Meeting.AbstractMeeting;
import BusinessLogic.Meeting.Meeting;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Rémi Salmi
 */
public class MeetingTest {

    @Test
    void setPlace(){
        AbstractMeeting meeting = new Meeting(1,LocalDate.now(),"montpellier",1);
        assertEquals(meeting.getPlace(),"montpellier");
        meeting.setPlace("Paris");
        assertEquals(meeting.getPlace(),"Paris");
    }
}
