package oncall.view;

import static oncall.constant.ErrorMessage.*;
import static oncall.constant.Value.*;

import oncall.model.Holiday;

public class OutputView {
	private final String DayFormat = "%d월 %d일 %s ";
	private final String HolidayFormat = "%d월 %d일 %s(휴일) ";

	public void printArgumentErrorMessage(IllegalArgumentException e) {
		System.out.println(ERROR_FORMAT + e.getMessage());
	}

	public void printDay(int month, int day, String dayOfWeek) {
		if(Holiday.isHoliday(month, day) && !dayOfWeek.equals(SATURDAY) && !dayOfWeek.equals(SUNDAY)) {
			System.out.printf(HolidayFormat, month, day, dayOfWeek);
			return;
		}
		System.out.printf(DayFormat, month, day, dayOfWeek);
	}

	public void printName(String name) {
		System.out.println(name);
	}
}
