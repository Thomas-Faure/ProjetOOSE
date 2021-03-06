package Facade.Meeting;

import BusinessLogic.Meeting.AbstractMeeting;
import BusinessLogic.Project.AbstractProject;

import java.util.List;

public interface IMeetingFacade {

    List<AbstractMeeting> getMeetingByProject(AbstractProject project);

    boolean deleteMeeting(AbstractMeeting toManage);

    boolean addMeeting(AbstractMeeting meeting);

    boolean update(AbstractMeeting meeting);
}
