package UI;

import Controller.IController;

public interface UIGlobalWithController extends UIGlobal{
    public IController getController();
}
