/**
 * 
 */
package wil.son.inter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

/**
 * @author Muniraju
 *
 *         Approach to solve the program. Step 1: Sort the user entered range
 *         based on their start of range. Step 2: Do this for every range in the
 *         input list Step 2.a: If pin code range doesnt overlap with the list,
 *         then add the range as is Step 2.b: If the range overlaps with the
 *         previous range, then find the merged range
 *
 */

public class UnitTestPinCodeMerge {

	/**
	 * Junit method to test the success scenario
	 */
	@Test
	public void testSuccess() {
		System.out.println("----Starting testSuccess----");

		List<PinCodeRange> userInputRanges = new ArrayList<PinCodeRange>();

		PinCodeRange p1 = new PinCodeRange(49679, 52015);
		PinCodeRange p2 = new PinCodeRange(49800, 50000);
		PinCodeRange p3 = new PinCodeRange(51500, 53479);
		PinCodeRange p4 = new PinCodeRange(45012, 46937);
		PinCodeRange p5 = new PinCodeRange(54012, 59607);
		PinCodeRange p6 = new PinCodeRange(45500, 45590);
		PinCodeRange p7 = new PinCodeRange(45999, 47900);
		PinCodeRange p8 = new PinCodeRange(44000, 45000);
		PinCodeRange p9 = new PinCodeRange(43012, 45950);
		userInputRanges.add(p1);
		userInputRanges.add(p2);
		userInputRanges.add(p3);
		userInputRanges.add(p4);
		userInputRanges.add(p5);
		userInputRanges.add(p6);
		userInputRanges.add(p7);
		userInputRanges.add(p8);
		userInputRanges.add(p9);
		System.out.println("User entered ranges: " + userInputRanges.toString());
		List<PinCodeRange> simplifiedRange = MergeRestrictedPinRange.displayMergedPinCodeRanges(userInputRanges);
		System.out.println("Simplified range: " + simplifiedRange.toString());
		Assert.assertNotNull(simplifiedRange);
		System.out.println("----End of testSuccess----");
	}
	@Test
	public void testFailure() {
		System.out.println("----Starting testFailure----");
		List<PinCodeRange> userInputRanges = new ArrayList<PinCodeRange>();

		PinCodeRange p1 = new PinCodeRange(52100, 52015);
		userInputRanges.add(p1);
		
		List<PinCodeRange> simplifiedRange = MergeRestrictedPinRange.displayMergedPinCodeRanges(userInputRanges);
		Assert.assertNull(simplifiedRange);
		System.out.println("----End of testFailure----");
	}
	
	@Test
	public void testNull() {
		System.out.println("----Starting testNull----");
		List<PinCodeRange> simplifiedRange = MergeRestrictedPinRange.displayMergedPinCodeRanges(null);
		Assert.assertNull(simplifiedRange);
		System.out.println("----End of testNull----");
	}
	
	@Test
	public void testBorderCases() {
		System.out.println("----Starting testBorderCases----");
		List<PinCodeRange> userInputRanges = new ArrayList<PinCodeRange>();

		PinCodeRange p1 = new PinCodeRange(49679, 49679);
		PinCodeRange p2 = new PinCodeRange(49681, 49681);
		PinCodeRange p3 = new PinCodeRange(49683, 49683);
		PinCodeRange p4 = new PinCodeRange(49683, 49683);
		PinCodeRange p5 = new PinCodeRange(49600, 49600);
		userInputRanges.add(p1);
		userInputRanges.add(p2);
		userInputRanges.add(p3);
		userInputRanges.add(p4);
		userInputRanges.add(p5);
		List<PinCodeRange> simplifiedRange = MergeRestrictedPinRange.displayMergedPinCodeRanges(userInputRanges);
		Assert.assertNotNull(simplifiedRange);
		System.out.println("----End of testBorderCases----");
	}
}
