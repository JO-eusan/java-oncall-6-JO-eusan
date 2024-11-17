package oncall.model;

import static oncall.constant.ErrorMessage.*;
import static oncall.constant.Value.*;

public class CalendarManager {
	private int month;
	private String dayOfWeek;
	private int endDay;

	public CalendarManager(String input) {
		String[] tokens = validateFormat(input);
		this.month = validateMonth(tokens[0]);
		this.dayOfWeek = validateDay(tokens[1]);
		this.endDay = Month.getMaxDay(this.month);
	}

	public int getMonth() {
		return month;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public int getEndDay() {
		return endDay;
	}

	public void setNextDayOfWeek() {
		this.dayOfWeek = DayOfWeek.getNextDayOfWeek(dayOfWeek);
	}

	public boolean isWeekend() {
		if(dayOfWeek.equals(SATURDAY) || dayOfWeek.equals(SUNDAY)) {
			return true;
		}
		return false;
	}

	private String[] validateFormat(String input) {
		String[] splitInput = input.split(",");

		for (String token : splitInput) {
			if (token.equals("") || token.isEmpty()) {
				throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
			}
		}
		return splitInput;
	}

	private int validateMonth(String token) {
		int month = 0;
		try {
			month = Integer.parseInt(token);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
		}

		if (month < 1 || month > 12) {
			throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
		}
		return month;
	}

	private String validateDay(String token) {
		if (token.equals(MONDAY) || token.equals(TUESDAY) || token.equals(WEDNESDAY) || token.equals(THURSDAY)
			|| token.equals(FRIDAY) || token.equals(SATURDAY) || token.equals(SUNDAY)) {
			return token;
		}
		throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
	}
}
