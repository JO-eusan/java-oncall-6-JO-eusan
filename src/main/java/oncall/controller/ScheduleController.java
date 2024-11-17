package oncall.controller;

import static oncall.constant.ErrorMessage.*;
import static oncall.constant.Value.*;

import java.util.HashSet;
import java.util.Set;

import oncall.model.CalendarManager;
import oncall.model.Holiday;
import oncall.model.Sequence;
import oncall.view.InputView;
import oncall.view.OutputView;

public class ScheduleController {
	InputView inputView;
	OutputView outputView;
	CalendarManager calendarManager;
	Sequence weekdaySequence;
	Sequence weekendSequence;

	public ScheduleController() {
		this.inputView = new InputView();
		this.outputView = new OutputView();
	}

	public void scheduling() {
		setCalender();
		setSequence();
		startAssignment();
	}

	private void setCalender() {
		String monthAndDay = inputView.readMonthAndDay();
		try {
			this.calendarManager = new CalendarManager(monthAndDay);
		} catch (IllegalArgumentException e) {
			outputView.printArgumentErrorMessage(e);
			setCalender();
		}
	}

	private void setSequence() {
		String weekdaySequence = inputView.readWeekdaySequence();
		String weekendSequence = inputView.readWeekendSequence();
		try {
			this.weekdaySequence = new Sequence(weekdaySequence);
			this.weekendSequence = new Sequence(weekendSequence);
			checkPersonnel(this.weekdaySequence, this.weekendSequence);
		} catch (IllegalArgumentException e) {
			outputView.printArgumentErrorMessage(e);
			setSequence();
		}
	}

	private void checkPersonnel(Sequence weekdaySequence, Sequence weekendSequence) {
		Set<String> names = new HashSet<>();
		for (String name : weekdaySequence.getSequence()) {
			names.add(name);
		}
		for (String name : weekendSequence.getSequence()) {
			names.add(name);
		}

		if (names.size() < MINIMUM_PEOPLE) {
			throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
		}
		if (names.size() > MAXIMUM_PEOPLE) {
			throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
		}
	}

	private void startAssignment() {
		for(int i=1; i<calendarManager.getEndDay(); i++) {
			outputView.printDay(calendarManager.getMonth(), i, calendarManager.getDayOfWeek());
			printName(calendarManager.isWeekend(), Holiday.isHoliday(calendarManager.getMonth(), i));

			calendarManager.setNextDayOfWeek();
		}
	}

	private void printName(boolean isWeekend, boolean isHoliday) {
		if(isWeekend || isHoliday) {
			outputView.printName(weekendSequence.getNextPerson());
			weekendSequence.setNextPerson();
			return;
		}
		outputView.printName(weekdaySequence.getNextPerson());
		weekdaySequence.setNextPerson();
	}
}
