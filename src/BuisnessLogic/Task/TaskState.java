package BuisnessLogic.Task;

public enum TaskState {
    todo("todo"),
    doing("doing"),
    done("done");
    String state;
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
