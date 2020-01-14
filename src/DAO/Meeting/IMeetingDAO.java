package DAO.Meeting;

import BusinessLogic.Meeting.AbstractMeeting;
import BusinessLogic.Project.AbstractProject;

import java.util.List;

public interface IMeetingDAO {

    List<AbstractMeeting> getMeetingByProject(AbstractProject project);

    boolean delete(int id);

    boolean save(AbstractMeeting meeting);

    boolean update(AbstractMeeting meeting);
}
