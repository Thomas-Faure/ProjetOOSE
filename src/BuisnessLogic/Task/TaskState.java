package BuisnessLogic.Task;
/**
 *
 * @author Thomas Faure
 */
public enum TaskState {
    todo("todo"),
    doing("doing"),
    done("done");
    private String state;
    TaskState(String state){
        this.state=state;
    }
    public String getStatetoString(){
        return state;
    }
    public static TaskState getStateByString(String state){

        TaskState stateToReturn = TaskState.valueOf(state);
        return stateToReturn;
    }

}
