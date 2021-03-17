/* ======================
 * @FileName: RangeTest.java
 * @Author: HangTo
 * 1.0 Creation
 ========================*/
package src.test.io.ubitec.interview_challenges;
import src.main.java.io.ubitec.interview_challenges.Range;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public final class RangeTest {

  @SuppressWarnings("deprecation")
  @Test
  public void level1_should_create_range() {
	System.out.println("=============START LEVEL 1=============");
    Range range = Range.of(5, 50);
    
    assertThat(range.lowerbound(), is(equalTo(5)));
    assertThat(range.upperbound(), is(equalTo(50)));
  }

  @Test
  public void level1_should_throw_error__when_create_with_lowerbound_bigger_than_upperbound() {
    try {
      Range.of(500, 1);
      fail("Should not allow creating a invalid Range");
    } catch(IllegalArgumentException e) {
      // pass
    }
  }

  @Test
  public void level1_closed_range_should_contain_both_bounds_and_all_elements_in_between() {
    Range<Integer> closedRange = Range.of(5, 50);
    
    assertThat(closedRange.contains(Integer.MIN_VALUE), is(equalTo(false)));
    assertThat(closedRange.contains(4), is(equalTo(false)));
    assertThat(closedRange.contains(5), is(equalTo(true)));
    assertThat(closedRange.contains(42), is(equalTo(true)));
    assertThat(closedRange.contains(50), is(equalTo(true)));
    assertThat(closedRange.contains(1000), is(equalTo(false)));
    assertThat(closedRange.contains(Integer.MAX_VALUE), is(equalTo(false)));
  }

  @Test
  public void level1_range_should_be_state_independent() {
    Range range1 = Range.of(5, 10);
    Range range2 = Range.of(11, 20);
    
    assertThat(range1.contains(10), is(equalTo(true)));
    assertThat(range2.contains(10), is(equalTo(false)));
  }
  
  @Test
  public void level1_test_range(){
	Range validAgesForWatchingPorn = Range.of(13, 100);

	assertThat(validAgesForWatchingPorn.contains(5), is(equalTo(false)));// false
	assertThat(validAgesForWatchingPorn.contains(13), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.contains(22), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.contains(100), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.contains(101), is(equalTo(false)));// false
	
	System.out.println(validAgesForWatchingPorn.contains(13)); // true
	System.out.println(validAgesForWatchingPorn.contains(22)); // true
	System.out.println(validAgesForWatchingPorn.contains(100)); // true
	System.out.println(validAgesForWatchingPorn.contains(101)); // false
	System.out.println("=============PASS LEVEL 1==============");
  }
  
  @Test
  public void level2_test_range(){
	System.out.println("=============START LEVEL 2=============");
	//Create Range from 1..100
	Range<Integer> validAgesForWatchingPorn = new Range<Integer>(1,100);

	//Create closed Range [13-100]
	System.out.println("=============CLOSED=============");
	assertThat(validAgesForWatchingPorn.closed(13, 100).contains(13), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.closed(13, 100).contains(100), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.closed(13, 100).contains(14), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.closed(13, 100).contains(12), is(equalTo(false)));// false
	
	System.out.println(validAgesForWatchingPorn.closed(13, 100).contains(13));//true
	System.out.println(validAgesForWatchingPorn.closed(13, 100).contains(100));//true
	System.out.println(validAgesForWatchingPorn.closed(13, 100).contains(14));//true
	System.out.println(validAgesForWatchingPorn.closed(13, 100).contains(12));//false
	
	//Create open Range (5-100)
	System.out.println("=============OPEN=============");
	assertThat(validAgesForWatchingPorn.open(5, 100).contains(5), is(equalTo(false)));// false
	assertThat(validAgesForWatchingPorn.open(5, 100).contains(6), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.open(5, 100).contains(99), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.open(5, 100).contains(100), is(equalTo(false)));// false
	
	System.out.println(validAgesForWatchingPorn.open(5, 100).contains(5));//false
	System.out.println(validAgesForWatchingPorn.open(5, 100).contains(6));//true
	System.out.println(validAgesForWatchingPorn.open(5, 100).contains(99));//true
	System.out.println(validAgesForWatchingPorn.open(5, 100).contains(100));//false
	
	//Create openClosed Range (5-90]
	System.out.println("=============OPEN_CLOSED=============");
	assertThat(validAgesForWatchingPorn.openClosed(5, 90).contains(5), is(equalTo(false)));// false
	assertThat(validAgesForWatchingPorn.openClosed(5, 90).contains(6), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.openClosed(5, 90).contains(90), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.openClosed(5, 90).contains(99), is(equalTo(false)));// false
	
	System.out.println(validAgesForWatchingPorn.openClosed(5, 90).contains(5));//false
	System.out.println(validAgesForWatchingPorn.openClosed(5, 90).contains(6));//true
	System.out.println(validAgesForWatchingPorn.openClosed(5, 90).contains(90));//true
	System.out.println(validAgesForWatchingPorn.openClosed(5, 90).contains(99));//false
	
	//Create closedOpen Range [6-90)
	System.out.println("=============CLOSED_OPEN=============");
	assertThat(validAgesForWatchingPorn.closedOpen(6, 90).contains(6), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.closedOpen(6, 90).contains(7), is(equalTo(true)));// true
	assertThat(validAgesForWatchingPorn.closedOpen(6, 90).contains(90), is(equalTo(false)));// false
	assertThat(validAgesForWatchingPorn.closedOpen(6, 90).contains(99), is(equalTo(false)));// false
	
	System.out.println(validAgesForWatchingPorn.closedOpen(6, 90).contains(6));//true
	System.out.println(validAgesForWatchingPorn.closedOpen(6, 90).contains(7));//true
	System.out.println(validAgesForWatchingPorn.closedOpen(6, 90).contains(90));//false
	System.out.println(validAgesForWatchingPorn.closedOpen(6, 90).contains(99));//false
	
	Range open = Range.open(5, 7);
	assertThat(open.contains(5), is(equalTo(false)));// false
	System.out.println(open.contains(5)); //false
	
	Range closed = Range.closed(5, 7);
	assertThat(closed.contains(5), is(equalTo(true)));// true
	System.out.println(closed.contains(5)); // true

	Range openClosed = Range.openClosed(5, 7);
	assertThat(openClosed.contains(5), is(equalTo(false)));// false
	assertThat(openClosed.contains(7), is(equalTo(true)));// true
	System.out.println(openClosed.contains(5)); // false
	System.out.println(openClosed.contains(7)); // true

	Range closedOpen = Range.closedOpen(5, 7);
	assertThat(closedOpen.contains(5), is(equalTo(true)));// true
	assertThat(closedOpen.contains(7), is(equalTo(false)));// false
	System.out.println(closedOpen.contains(5)); // true;
	System.out.println(closedOpen.contains(7)); // false;
	System.out.println("=============PASS LEVEL 2=============");
  }

	@Test
	public void level3_CheckRangeWithManyTypes(){
		System.out.println("=============START LEVEL 3=============");
		Range<String> text = Range.open("abc", "xyz");
		assertThat(text.contains("1.3243211"), is(equalTo(false)));
		System.out.println(text.contains("1.3243211"));
		
		Range<BigDecimal> decimals = Range.open(BigDecimal.valueOf(new Double("1.32432")), BigDecimal.valueOf(new Double("1.324323423423423423423")));
		System.out.println(decimals.contains(new BigDecimal("1.3243211")));
		assertThat(decimals.contains(new BigDecimal("1.3243211")), is(equalTo(true)));
		
		Range<LocalDate> dates = Range.closed(LocalDate.of(2016, Month.SEPTEMBER, 11), LocalDate.of(2020, Month.JUNE, 30));
		System.out.println(dates.contains(LocalDate.parse("2020-11-11")));
		assertThat(dates.contains(LocalDate.parse("2020-11-11")), is(equalTo(false)));
		System.out.println("=============PASS LEVEL 3=============");
	}
	
	@Test
	public void level4_test_open_ended_range() {
		System.out.println("=============START LEVEL 4=============");
		Range<Integer> lessThanFive = Range.lessThan(5); // [Infinitive, 5)
		System.out.println(lessThanFive.contains(5)); // false;
		System.out.println(lessThanFive.contains(4)); // true;
		System.out.println(lessThanFive.contains(-9000)); // true;
		
		assertThat(lessThanFive.contains(5), is(equalTo(false)));
		assertThat(lessThanFive.contains(4), is(equalTo(true)));
		assertThat(lessThanFive.contains(-9000), is(equalTo(true)));
		
		Range<LocalDate> lessLocalDate = Range.lessThan(LocalDate.of(1900, Month.JANUARY, 1)); // [Infinitive, 1900-01-01)
		assertThat(lessLocalDate.contains(LocalDate.parse("2020-01-08")), is(equalTo(false)));
		assertThat(lessLocalDate.contains(LocalDate.parse("1899-01-08")), is(equalTo(true))); // false;
		assertThat(lessLocalDate.contains(LocalDate.parse("2021-01-08")), is(equalTo(false))); // false;
		
		Range<BigDecimal> lessThanDecimals = Range.lessThan(BigDecimal.valueOf(new Double("1.324323423423423423423"))); //[Infinitive, 1.324323423423423423423)
		System.out.println(lessThanDecimals.contains(new BigDecimal("1.3243211")));//true
		System.out.println(lessThanDecimals.contains(new BigDecimal("1.3333333")));//false
		
		assertThat(lessThanDecimals.contains(new BigDecimal("1.3243211")), is(equalTo(true)));
		assertThat(lessThanDecimals.contains(new BigDecimal("1.3333333")), is(equalTo(false)));
		
		Range<Integer> atLeastFive = Range.atLeast(5); // [5, Infinitive]
		System.out.println(atLeastFive.contains(5)); // true;
		System.out.println(atLeastFive.contains(4)); // false;
		System.out.println(atLeastFive.contains(6)); // true;
		
		assertThat(atLeastFive.contains(5), is(equalTo(true)));
		assertThat(atLeastFive.contains(4), is(equalTo(false)));
		assertThat(atLeastFive.contains(6), is(equalTo(true)));

		Range<Integer> atMostFive = Range.atMost(5); // [Infinitive, 5]
		System.out.println(atMostFive.contains(5));// true
		System.out.println(atMostFive.contains(-234234)); // true;
		System.out.println(atMostFive.contains(6)); // false;
		
		assertThat(atMostFive.contains(5), is(equalTo(true))); // true
		assertThat(atMostFive.contains(-234234), is(equalTo(true))); // true;
		assertThat(atMostFive.contains(6), is(equalTo(false))); // false;

		Range<Integer> greaterThanZero = Range.greaterThan(-12); // (-12, Infinitive]
		System.out.println(greaterThanZero.contains(-10));// true
		System.out.println(greaterThanZero.contains(-11));// true
		System.out.println(greaterThanZero.contains(-19));// false
		
		assertThat(greaterThanZero.contains(-10), is(equalTo(true))); // true
		assertThat(greaterThanZero.contains(-11), is(equalTo(true))); // true
		assertThat(greaterThanZero.contains(-19), is(equalTo(false))); // false
		
		Range<LocalDate> afterEpoch = Range.greaterThan(LocalDate.of(1900, Month.JANUARY, 1)); // (1900-01-01, Infinitive]
		System.out.println(afterEpoch.contains(LocalDate.of(2016, Month.JULY, 28)));// true
		System.out.println(afterEpoch.contains(LocalDate.of(1750, Month.JANUARY, 1)));// false
		
		assertThat(afterEpoch.contains(LocalDate.of(2016, Month.JULY, 28)), is(equalTo(true))); // true;
		assertThat(afterEpoch.contains(LocalDate.of(1750, Month.JANUARY, 1)), is(equalTo(false))); // false;

		Range<String> all = Range.all(); // [Infinitive, Infinitive]
		System.out.println(all.contains("anything"));// true
		System.out.println(all.contains(""));// true
		System.out.println(all.contains(null));// true
		
		assertThat(all.contains("anything"), is(equalTo(true)));
		assertThat(all.contains(""), is(equalTo(true)));
		assertThat(all.contains(null), is(equalTo(true)));
		System.out.println("=============PASS LEVEL 4=============");
	}
	
	@Test
	public void level5_test_range_to_string() {
		System.out.println("=============START LEVEL 5=============");
		Range<Integer> lessThan100 = Range.lessThan(100);
		System.out.println(lessThan100.toString(lessThan100));
		assert lessThan100.toString(lessThan100).equals("[Infinity,100)");
		
		Range<String> all = Range.all();
		System.out.println(all.toString(all));
		assert all.toString(all).equals("[-Infinity, Infinity]");
		
		Range<Integer> atMostFive = Range.atMost(5); // [Infinitive, 5]
		System.out.println(atMostFive.toString(atMostFive));

		System.out.println(Month.JANUARY);
		
		Range<LocalDate> within2020 = Range.closed(
		  LocalDate.of(2020,Month.JANUARY,1),
		  LocalDate.of(2020,Month.DECEMBER,31)
		);
		
		System.out.println(within2020.toString(within2020));

		assert within2020.toString(within2020).equals("[2020-01-01,2020-12-31]");//true
		assert within2020.toString(within2020).equals("[2020-01-01,2021-12-31]");//false
		System.out.println("=============PASS LEVEL 5=============");
	}
	
	@Test
	public void level6_test_range_to_string() {
		System.out.println("=============LEVEL 6=============");
		Range<Integer> lessThan100 = Range.lessThan(100);
		String stringParse = lessThan100.toString(lessThan100);
		lessThan100.parseNotation(stringParse);
		System.out.println(lessThan100.parseNotation(stringParse));
		
		Range<Integer> atMost100 = Range.atMost(100);
		String atMostOneHundred = atMost100.toString(atMost100);
		lessThan100.parseNotation(atMostOneHundred);
		System.out.println(lessThan100.parseNotation(atMostOneHundred));
		System.out.println("=============PASS LEVEL 6=============");
	}
}
