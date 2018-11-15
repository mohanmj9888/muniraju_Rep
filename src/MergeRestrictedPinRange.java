/**
 * 
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Muniraju
 *
 * Approach to solve the program.
 * Step 1: Sort the user entered range based on their start of range.
 * Step 2: Do this for every range in the input list
 * 		Step 2.a: If pin code range doesnt overlap with the previous item in the list, then add the range as is to the list
 * 		Step 2.b: If the range overlaps with the previous range, then find the merged range and store it
 *
 */
public class MergeRestrictedPinRange {

	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	  System.out.println("Starting restricted PIN range merge program..");
	  
	  //Part 1 :: This part would read the input pin code ranges from User
	  Scanner inputScanner = null;
	  List<PinCodeRange> userInputRanges = new ArrayList<PinCodeRange>();
	  try {
		  inputScanner = new Scanner(System.in);
			  for(int i = 0 ; i < 100 ; i++) {
				  System.out.println("Enter the pin code range separated by comma and press 'ENTER'");
				  try {
					  String input = inputScanner.next("[0-9]*,[0-9]*");
					  System.out.println("Input: "+input);
					  long start = Long.parseLong(input.split(",")[0]);
					  long end = Long.parseLong(input.split(",")[1]);
					  System.out.println("Start: "+start+" End: "+end);
					  if(start > end) {
						  System.out.println("Invalid range, start cannot be greater than end");
						  continue;
					  }
					  PinCodeRange range = new PinCodeRange(start, end);
					  userInputRanges.add(range);
					  System.out.println("Do you want to add more ranges ? If Yes press 'Y', if No press 'N'");
					  String use = inputScanner.next();
					  System.out.println("User input : "+use);
					  if(use.equalsIgnoreCase("N")) {
						  System.out.println("End of inputs");
						  break;
					  }
			  } catch(Exception e) {
				  System.out.println("Exception with this Pin code range. Continue with next: ");
			  }
		}
	} catch(Exception e) {
		System.out.println("Exception in merging the restricted pin code range " + e.getMessage() + e);
	} finally {
		inputScanner.close();
	}
	  
	  //Part 2:: This merges the pin code range
	System.out.println(userInputRanges.size());
	
	//Call this method to merge the ranges
	displayMergedPinCodeRanges(userInputRanges);
	
	}

	/**
	 * @param userInputRanges
	 */
	private static void displayMergedPinCodeRanges(List<PinCodeRange> userInputRanges) {
		//Sort the list before processing
		 Collections.sort(userInputRanges);
		 System.out.println("Input ranges to be merged");
		 for(int i=0; i < userInputRanges.size() ; i++) {
			  System.out.println("["+userInputRanges.get(i).getStart()+","+userInputRanges.get(i).getEnd()+"]");
		  }
		 
		  int index = 0;
		  for (int i=0; i< userInputRanges.size(); i++){
			  //Modify the PinCodeRange when there is an overlap between 2 adjacent ranges
			  if(index != 0 && userInputRanges.get(i).getStart() - userInputRanges.get(index-1).getEnd() <= 1) {
				  userInputRanges.get(index-1).setEnd(maxOf(userInputRanges.get(i).getEnd(), userInputRanges.get(index-1).getEnd()));
				  userInputRanges.get(index-1).setStart(minOf(userInputRanges.get(i).getStart(), userInputRanges.get(index-1).getStart()));
				  index--;
			  } else {
				//Add the pin code range into final Merged List when
				  //1. This is the first PinCodeRange
				  //2. When there is no overlap between the current range and the previous one
				  userInputRanges.get(index).setStart(userInputRanges.get(i).getStart());
				  userInputRanges.get(index).setEnd(userInputRanges.get(i).getEnd());
			  }
			  index ++;
		  }
		  System.out.println("Final merged restricted pin code ranges");
		  for(int i=0; i < index ; i++) {
			  System.out.println("["+userInputRanges.get(i).getStart()+","+userInputRanges.get(i).getEnd()+"]");
		  }
	}

	/**
	 * Method returns the min of 2 longs
	 * @param l1
	 * @param l2
	 * @return
	 */
	private static long minOf(long l1, long l2) {
		return l1 <= l2 ? l1: l2;
	}

	/**
	 * Method returns the max of 2 longs
	 * @param l1
	 * @param l2
	 * @return
	 */
	private static long maxOf(long l1, long l2) {
		return l1 >= l2? l1 : l2;
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
	private long start;
	/**
	 * Upper bound of pin code range
	 */
	private long end;

	/**
	 * Constructor to initialize objects
	 * 
	 * @param start
	 * @param end
	 */
	public PinCodeRange(long start, long end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Overriding compareTo method to sort the PinCodeRange array based on lower
	 * bound value
	 */
	@Override
	public int compareTo(PinCodeRange obj) {
		return (int) (this.start - obj.start);
	}

	/**
	 * @return the start
	 */
	public long getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(long start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public long getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(long end) {
		this.end = end;
	}
}
