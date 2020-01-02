package DAO.Meeting;

import BuisnessLogic.Meeting.AbstractMeeting;
import BuisnessLogic.Project.AbstractProject;

import java.util.List;

public interface MeetingDAO {

    List<AbstractMeeting> getMeetingByProject(AbstractProject project);

    boolean delete(int id);

    boolean save(AbstractMeeting meeting);

    boolean update(AbstractMeeting meeting);
}
