/**
 * 
 */
package wil.son.inter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Muniraju
 *
 *         Approach to solve the program. Step 1: Sort the user entered range
 *         based on their start of range. Step 2: Do this for every range in the
 *         input list Step 2.a: If pin code range doesnt overlap with the
 *         previous item in the list, then add the range as is to the list Step
 *         2.b: If the range overlaps with the previous range, then find the
 *         merged range and store it
 *
 */
public class MergeRestrictedPinRange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting restricted PIN range merge program..");

		// Part 1 :: This part would read the input pin code ranges from User
		Scanner inputScanner = null;
		List<PinCodeRange> userInputRanges = new ArrayList<PinCodeRange>();
		try {
			inputScanner = new Scanner(System.in);
			for (int i = 0; i < 100; i++) {
				System.out.println("Enter the pin code range separated by comma and press 'ENTER'");
				try {
					String input = inputScanner.next("[0-9]*,[0-9]*");
					System.out.println("Input: " + input);
					int start = Integer.parseInt(input.split(",")[0]);
					int end = Integer.parseInt(input.split(",")[1]);
					System.out.println("Start: " + start + " End: " + end);
					if (start > end) {
						System.out.println("Invalid range, start cannot be greater than end");
						continue;
					}
					PinCodeRange range = new PinCodeRange(start, end);
					userInputRanges.add(range);
					System.out.println("Do you want to add more ranges ? If Yes press 'Y', if No press 'N'");
					String use = inputScanner.next();
					System.out.println("User input : " + use);
					if (use.equalsIgnoreCase("N")) {
						System.out.println("End of inputs");
						break;
					}
				} catch (Exception e) {
					System.out.println("Exception with this Pin code range. Continue with next: ");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in merging the restricted pin code range " + e.getMessage() + e);
		} finally {
			inputScanner.close();
		}

		// Part 2:: This merges the pin code range
		System.out.println(userInputRanges.size());
		// Call this method to merge the ranges
		List<PinCodeRange> simplifiedRange = displayMergedPinCodeRanges(userInputRanges);
		System.out.println(simplifiedRange.toString());
	}

	/**
	 * @param userInputRanges
	 */
	public static List<PinCodeRange> displayMergedPinCodeRanges(List<PinCodeRange> userInputRanges) {
		// Sort the list before processing
		Collections.sort(userInputRanges);
		System.out.println("Input ranges to be merged");
		int index = 0;
		for (int i = 0; i < userInputRanges.size(); i++) {
			// Modify the PinCodeRange when there is an overlap between 2 adjacent ranges
			if (index != 0 && userInputRanges.get(i).getStart() - userInputRanges.get(index - 1).getEnd() <= 1) {
				userInputRanges.get(index - 1)
						.setEnd(maxOf(userInputRanges.get(i).getEnd(), userInputRanges.get(index - 1).getEnd()));
				userInputRanges.get(index - 1)
						.setStart(minOf(userInputRanges.get(i).getStart(), userInputRanges.get(index - 1).getStart()));
				index--;
			} else {
				// Add the pin code range into final Merged List when
				// 1. This is the first PinCodeRange
				// 2. When there is no overlap between the current range and the previous one
				userInputRanges.get(index).setStart(userInputRanges.get(i).getStart());
				userInputRanges.get(index).setEnd(userInputRanges.get(i).getEnd());
			}
			index++;
		}
		System.out.println("Final merged restricted pin code ranges");
		return userInputRanges.subList(0, index);

	}

	/**
	 * Method returns the min of 2 integers
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	private static int minOf(int l1, int l2) {
		return l1 <= l2 ? l1 : l2;
	}

	/**
	 * Method returns the max of 2 integers
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	private static int maxOf(int l1, int l2) {
		return l1 >= l2 ? l1 : l2;
	}
}

/**
 * 
 * This class will hold the POJO created to hold the pin code range and to sort
 * the user input pin code ranges based on their lowerbound values
 *
 */
class PinCodeRange implements Comparable<PinCodeRange> {

	/**
	 * Lower bound of pin code range
	 */
	private int start;
	/**
	 * Upper bound of pin code range
	 */
	private int end;

	/**
	 * Constructor to initialize objects
	 * 
	 * @param start start of pin code range
	 * @param end   end of pin code range
	 */
	public PinCodeRange(int start, int end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Overriding compareTo method to sort the PinCodeRange array based on lower
	 * bound value
	 * @param current object
	 * @return 1 when the current objects start value is greater than start of input object
	 *         -1 if less than input object
	 *         0 if both are equal
	 */
	@Override
	public int compareTo(PinCodeRange obj) {
		return (this.start - obj.start);
	}

	/**
	 * Overriding hashCode method to comply with compareTo
	 * @return Overridden hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + super.hashCode();
	}

	/**
	 * Overriding equals method to comply with compareTo
	 * @param current object
	 * @param true if start value of both current object and the input object are equal
	 *        else false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == null || obj == null) {
			return false;
		}
		PinCodeRange pinRange = (PinCodeRange) obj;
		return pinRange.getStart() == this.getStart();
	}
	/**
	 * Overriding toString for formatted display
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(this.start).append("-").append(this.end).append("]");
		return sb.toString();
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}
}
