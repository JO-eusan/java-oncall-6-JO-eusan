package oncall.model;

public enum DayOfWeek {
	월,화,수,목,금,토,일;

	public static String getNextDayOfWeek(String current) {
		DayOfWeek[] days = values();

		for(int i=0; i<days.length; i++) {
			if(days[i].name().equals(current)) {
				return days[(i+1) % days.length].name();
			}
		}
		return "";
	}
}
