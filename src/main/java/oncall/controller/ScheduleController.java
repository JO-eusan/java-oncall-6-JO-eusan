package oncall.controller;

import static oncall.constant.ErrorMessage.*;
import static oncall.constant.Value.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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
	Stack<String> result;

	public ScheduleController() {
		this.inputView = new InputView();
		this.outputView = new OutputView();

		result = new Stack<>();
		result.push(" ");
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
		for(int i=1; i<=calendarManager.getEndDay(); i++) {
			outputView.printDay(calendarManager.getMonth(), i, calendarManager.getDayOfWeek());
			assignment(calendarManager.isWeekend(), Holiday.isHoliday(calendarManager.getMonth(), i));
			outputView.printName(result.get(i));

			calendarManager.setNextDayOfWeek();
		}
	}

	private void assignment(boolean isWeekend, boolean isHoliday) {
		if(isWeekend || isHoliday) {
			if(result.peek().equals(weekendSequence.getNextPerson())) {
				String tmp = weekendSequence.getNextPerson();
				weekendSequence.setNextPerson();
				result.push(weekendSequence.getNextPerson());
				result.push(tmp);
				weekendSequence.setNextPerson();
				return;
			}
			result.push(weekendSequence.getNextPerson());
			weekendSequence.setNextPerson();
			return;
		}
		if(result.peek().equals(weekdaySequence.getNextPerson())) {
			String tmp = weekdaySequence.getNextPerson();
			weekdaySequence.setNextPerson();
			result.push(weekdaySequence.getNextPerson());
			result.push(tmp);
			weekdaySequence.setNextPerson();
			return;
		}
		result.push(weekdaySequence.getNextPerson());
		weekdaySequence.setNextPerson();
	}
}
