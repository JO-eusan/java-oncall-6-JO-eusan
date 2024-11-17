package oncall;

import oncall.controller.ScheduleController;

public class Application {
    public static void main(String[] args) {
        ScheduleController scheduleController = new ScheduleController();
        scheduleController.scheduling();
    }
}
