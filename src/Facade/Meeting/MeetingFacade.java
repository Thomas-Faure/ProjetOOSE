package Facade.Meeting;

import BuisnessLogic.Meeting.AbstractMeeting;
import DAO.AbstractDAOFactory;

public class MeetingFacade implements IMeetingFacade {
    private AbstractMeeting[] meetings;
    private AbstractDAOFactory daoFactory;
}
