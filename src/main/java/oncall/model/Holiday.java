package oncall.model;

public enum Holiday {
	SINJUNG(1, 1),
	SAMILJUL(3, 1),
	CHILDRENDAY(5, 5),
	HYUNCHUNGIL(6, 6),
	GWANGBOKJUL(8, 15),
	GAECHUNJUL(10, 3),
	HANGUELNAL(10, 9),
	CHRISTMAS(12, 25);

	private int month;
	private int day;

	private Holiday(int month, int day) {
		this.month = month;
		this.day = day;
	}

	private int month() {
		return month;
	}

	private int day() {
		return day;
	}

	public static boolean isHoliday(int currentMonth, int currentDay) {
		for(Holiday holiday : values()) {
			if(holiday.month == currentMonth && holiday.day == currentDay) {
				return true;
			}
		}
		return false;
	}
}
