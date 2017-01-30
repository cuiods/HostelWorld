package edu.nju.util.task;

/**
 * static method to start a Task implements {@code Runnable}
 * @author cuihao
 */
public class TaskUtil {
    public static void startNow(Runnable task) {
        new Thread(task).start();
    }
}
