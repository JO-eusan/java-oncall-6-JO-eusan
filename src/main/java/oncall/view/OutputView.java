package oncall.view;

import static oncall.constant.ErrorMessage.*;

public class OutputView {
	public void printArgumentErrorMessage(IllegalArgumentException e) {
		System.out.println(ERROR_FORMAT + e.getMessage());
	}


}
