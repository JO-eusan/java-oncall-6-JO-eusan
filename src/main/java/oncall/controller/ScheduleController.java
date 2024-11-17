package oncall.controller;

import oncall.model.Calendar;
import oncall.view.InputView;
import oncall.view.OutputView;

public class ScheduleController {
	InputView inputView;
	OutputView outputView;
	Calendar calendar;

	public ScheduleController() {
		this.inputView = new InputView();
		this.outputView = new OutputView();
	}

	public void scheduling() {
		setCalender();
	}

	private void setCalender() {
		String monthAndDay = inputView.readMonthAndDay();
		try {
			this.calendar = new Calendar(monthAndDay);
		} catch (IllegalArgumentException e) {
			outputView.printArgumentErrorMessage(e);
			setCalender();
		}
	}
}
