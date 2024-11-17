package oncall.model;

import static oncall.constant.ErrorMessage.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sequence {
	private List<String> sequence;
	private String nextPerson;

	public Sequence(String names) {
		String[] tokens = validateFormat(names);
		List<String> namesLengthInFive = validateNameLength(tokens);
		validateDuplicate(namesLengthInFive);

		this.sequence = namesLengthInFive;
		this.nextPerson = namesLengthInFive.get(0);
	}

	public List<String> getSequence() {
		return sequence;
	}

	public String getNextPerson() {
		return nextPerson;
	}

	public void setNextPerson() {
		for(int i=0; i<sequence.size(); i++) {
			if(sequence.get(i).equals(nextPerson)) {
				this.nextPerson =  sequence.get((i+1) % sequence.size());
				break;
			}
		}

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

	private List<String> validateNameLength(String[] tokens) {
		List<String> sequence = new ArrayList<>();

		for(String token : tokens) {
			if(token.length() > 5) {
				throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
			}
			sequence.add(token);
		}
		return sequence;
	}

	private void validateDuplicate(List<String> names) {
		Set<String> removeDuplicate = new HashSet<>(names);

		if (names.size() != removeDuplicate.size()) {
			throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
		}
	}
}
