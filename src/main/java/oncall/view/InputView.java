package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	public final String MONTH_AND_DAY_INPUT_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
	public final String WEEKDAY_SEQUENCE_INPUT_MESSAGE = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
	public final String WEEKEND_SEQUENCE_INPUT_MESSAGE = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";

	public String readMonthAndDay() {
		System.out.print(MONTH_AND_DAY_INPUT_MESSAGE);
		return Console.readLine();
	}

	public String readWeekdaySequence() {
		System.out.print(WEEKDAY_SEQUENCE_INPUT_MESSAGE);
		return Console.readLine();
	}

	public String readWeekendSequence() {
		System.out.print(WEEKEND_SEQUENCE_INPUT_MESSAGE);
		return Console.readLine();
	}
}
