package BuisnessLogic.Member;

import BuisnessLogic.Member.AbstractMember;
import BuisnessLogic.User.User;
import Facade.IRoleFacade;

public class Member extends AbstractMember {
    private User user;
    private IRoleFacade projectRole;
}
