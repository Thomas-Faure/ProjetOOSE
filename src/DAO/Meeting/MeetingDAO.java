package DAO.Meeting;

import BuisnessLogic.Meeting.AbstractMeeting;

import java.util.List;

public interface MeetingDAO {

    List<AbstractMeeting> getMeetingByProject(int idProject);

    boolean delete(int id);

    boolean save(AbstractMeeting meeting);

    boolean update(AbstractMeeting meeting);
}
