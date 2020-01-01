package Facade.Meeting;

import BuisnessLogic.Meeting.AbstractMeeting;

import java.util.List;

public interface IMeetingFacade {

    List<AbstractMeeting> getMeetingByProject(int idProject);

    boolean deleteMeeting(AbstractMeeting toManage);

    boolean addMeeting(AbstractMeeting meeting);

    boolean update(AbstractMeeting meeting);
}
