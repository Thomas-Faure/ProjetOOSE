package Facade;

import BuisnessLogic.Member.AbstractMember;
import DAO.AbstractDAOFactory;

public class MemberFacade implements IMemberFacade {
    private AbstractMember[] members;
    private AbstractDAOFactory daoFactory;
}
