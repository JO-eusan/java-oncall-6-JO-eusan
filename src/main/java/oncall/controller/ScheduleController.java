package oncall.controller;

import static oncall.constant.ErrorMessage.*;
import static oncall.constant.Value.*;

import java.util.HashSet;

import oncall.model.Calendar;
import oncall.model.Sequence;
import oncall.view.InputView;
import oncall.view.OutputView;

public class ScheduleController {
	InputView inputView;
	OutputView outputView;
	Calendar calendar;
	Sequence weekdaySequence;

	public ScheduleController() {
		this.inputView = new InputView();
		this.outputView = new OutputView();
	}

	public void scheduling() {
		setCalender();
		setSequence();
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

	private void setSequence() {
		String weekdaySequence = inputView.readWeekdaySequence();
		try {
			this.weekdaySequence = new Sequence(weekdaySequence);
			System.out.println(this.weekdaySequence);
			/* 휴일 추가*/
		} catch (IllegalArgumentException e) {
			outputView.printArgumentErrorMessage(e);
			setSequence();
		}
	}

	private void checkPersonnel(HashSet<String> names) {
		if (names.size() < MINIMUM_PEOPLE) {
			throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
		}
		if(names.size() > MAXIMUM_PEOPLE) {
			throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
		}
	}
}
