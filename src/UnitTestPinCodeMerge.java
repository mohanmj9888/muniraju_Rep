/**
 * 
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Muniraju
 *
 * Approach to solve the program.
 * Step 1: Sort the user entered range based on their start of range.
 * Step 2: Do this for every range in the input list
 * 		Step 2.a: If pin code range doesnt overlap with the list, then add the range as is
 * 		Step 2.b: If the range overlaps with the previous range, then find the merged range
 *
 */
public class UnitTestPinCodeMerge {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	  System.out.println("Starting restricted PIN range merge program..");
	  
	  List<PinRange> userInputRanges = new ArrayList<PinRange>();

	  PinRange p1 = new PinRange(49679, 52015);
	  PinRange p2 = new PinRange(49800, 50000);
	  PinRange p3 = new PinRange(51500, 53479);
	  PinRange p4 = new PinRange(45012, 46937);
	  PinRange p5 = new PinRange(54012, 59607);
	  PinRange p6 = new PinRange(45500, 45590);
	  PinRange p7 = new PinRange(45999, 47900);
	  PinRange p8 = new PinRange(44000, 45000);
	  PinRange p9 = new PinRange(43012, 45950);
	  userInputRanges.add(p1);
	  userInputRanges.add(p2);
	  userInputRanges.add(p3);
	  userInputRanges.add(p4);
	  userInputRanges.add(p5);
	  userInputRanges.add(p6);
	  userInputRanges.add(p7);
	  userInputRanges.add(p8);
	  userInputRanges.add(p9);
	
	System.out.println(userInputRanges.size());
	displayMergedPinCodeRanges(userInputRanges);
	
	}

	/**
	 * @param userInputRanges
	 */
	private static void displayMergedPinCodeRanges(List<PinRange> userInputRanges) {
		 Collections.sort(userInputRanges);
		 System.out.println("Input ranges to be merged");
		 for(int i=0; i < userInputRanges.size() ; i++) {
			  System.out.println("["+userInputRanges.get(i).getStart()+","+userInputRanges.get(i).getEnd()+"]");
		  }
		 
		  int index = 0;
		  for (int i=0; i< userInputRanges.size(); i++){
			  if(index != 0 && userInputRanges.get(i).getStart() - userInputRanges.get(index-1).getEnd() <= 1) {
				  userInputRanges.get(index-1).setEnd(maxOf(userInputRanges.get(i).getEnd(), userInputRanges.get(index-1).getEnd()));
				  userInputRanges.get(index-1).setStart(minOf(userInputRanges.get(i).getStart(), userInputRanges.get(index-1).getStart()));
				  index--;
			  } else {
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
class PinRange implements Comparable<PinRange> {

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
	public PinRange(long start, long end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Overriding compareTo method to sort the PinRange array based on lower
	 * bound value
	 */
	@Override
	public int compareTo(PinRange obj) {
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
