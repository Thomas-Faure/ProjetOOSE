package UI;
/**
 *
 * @author Thomas Faure
 */
import Controller.IController;

public interface UIGlobalWithController extends UIGlobal{
    public IController getController();
}
