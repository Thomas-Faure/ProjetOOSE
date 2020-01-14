package Facade.Meeting;

import BusinessLogic.Meeting.AbstractMeeting;
import BusinessLogic.Project.AbstractProject;
import DAO.Meeting.MeetingDAO;
import DAO.MySQLDAOFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette facade permet la gestion des meetings
 * @author Rémi Salmi
 */
public class MeetingFacade implements IMeetingFacade {
    private List<AbstractMeeting> meetings;
    private MeetingDAO dao;
    public static MeetingFacade instance;

    private MeetingFacade(){
        dao = MySQLDAOFactory.getInstance().getMeetingDAO();
        this.meetings = new ArrayList<>();
    }

    /**
     * Renvoie l'instance de la facade
     * @author Rémi Salmi
     */
    public static MeetingFacade getInstance(){
        if(instance == null){
            instance =new MeetingFacade();
        }
        return instance;
    }

    @Override
    /**
     * Permet de récupérer les meetings d'un projet
     * @author Rémi Salmi
     */
    public List<AbstractMeeting> getMeetingByProject(AbstractProject project) {
        this.meetings = dao.getMeetingByProject(project);
        return this.meetings;
    }

    @Override
    /**
     * Permet de supprimer un meeting
     * @author Rémi Salmi
     */
    public boolean deleteMeeting(AbstractMeeting meeting) {
        if(dao.delete(meeting.getId())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    /**
     * Permet d'ajouter un meeting
     * @author Rémi Salmi
     */
    public boolean addMeeting(AbstractMeeting meeting) {
        if(dao.save(meeting)){
            this.meetings.add(meeting);
            return true;
        }else {
            return false;
        }
    }

    @Override
    /**
     * Permet de meetre a jour un meeting
     * @author Rémi Salmi
     */
    public boolean update(AbstractMeeting meeting) {
        if(dao.update(meeting)){
            int i = 0;
            while(this.meetings.get(i).getId() != meeting.getId()){
                i++;
            }
            this.meetings.set(i, meeting);
            return true;
        }else {
            return false;
        }
    }
}
