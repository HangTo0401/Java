/* ======================
 * @FileName: Range.java
 * @Author: HangTo
 * 1.0 Creation
 ========================*/
package src.main.java.io.ubitec.interview_challenges;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.io.Serializable;

public final class Range <T extends Comparable> implements Serializable {

    private T lowerbound;
    private T upperbound;
    public static boolean inLower = false;//true - In lowerbound
    public static boolean inUpper = false;//true - In upperbound
    private static double pos_infinity = Double.POSITIVE_INFINITY;
    private static double nev_infinity =  Double.NEGATIVE_INFINITY;
    public static String isInfinity = "";
    
 /**
   * Constructor is private BY DESIGN.
   */
	public Range(T lowerbound, T upperbound){
		this.lowerbound = lowerbound;
		this.upperbound = upperbound;
    }

  /**
   * Creates a new <b>closed</b> {@code Range} that includes both bounds.
   */
  public static Range<Integer> of(int lowerbound, int upperbound) {
	  Range<Integer> range = null;
    try {
      //Find [lowerbound, upperbound]
  	  inLower = true;
  	  inUpper = true;
      if(lowerbound < upperbound) {
        range = new Range<Integer>(lowerbound, upperbound);
      } else {
    	  throw new InvalidRangeArgumentException("Should not allow creating a invalid Range");
      }
      
    } catch (Exception e) {
        e.printStackTrace();
    }
	
	return range;
  }

  /**
   * Returns {@code true} on if the given {@code value} is contained in this
   * {@code Range}.
   */
  public boolean contains(T value) {
	  try {
		  boolean lowerFlag = false;
		  boolean upperFlag = false;
		  	if(isInfinity.equals("")) {
		  		lowerFlag = inLower ? lowerbound.compareTo(value) <= 0 : lowerbound.compareTo(value) < 0;
		  		upperFlag = inUpper ? upperbound.compareTo(value) >= 0 : upperbound.compareTo(value) > 0;
		        return lowerFlag && upperFlag;
		  	} else if(isInfinity.equals("-Infinity")) {// (Infinity, uppbound)
		  		upperFlag = inUpper ? upperbound.compareTo(value) >= 0 : upperbound.compareTo(value) > 0;
		  		return upperFlag;
		  	} else if(isInfinity.equals("Infinity")) {// (lowerbound, Infinity)
		  		lowerFlag = inLower ? lowerbound.compareTo(value) <= 0 : lowerbound.compareTo(value) < 0;
		  		return lowerFlag;
		  	}
		  	
		  	//(-INFINITY, INFINITY)
		  	return true;
	  } catch(Exception ex) {
			throw new IllegalArgumentException("Invalid integer");
	  }
  }

  /**
   * Returns the {@code lowerbound} of this {@code Range}.
   */
  public T lowerbound() {
    try {
      return lowerbound;
    } catch (NumberFormatException e){
        throw new IllegalArgumentException("Invalid integer");
    }
  }

  /**
   * Returns the {@code upperbound} of this {@code Range}.
   */
  public T upperbound() {
    try {
      return upperbound;
    } catch (NumberFormatException e){
        throw new IllegalArgumentException("Invalid integer");
    }
  }
 
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<?>> Range<T> closed(T lowerbound, T upperbound) {
	  inLower = true;
	  inUpper = true;
      Objects.requireNonNull(lowerbound);
      Objects.requireNonNull(upperbound);
      return new Range<T>(lowerbound, upperbound);
  }
  
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<?>> Range<T> open(T lowerbound, T upperbound) {
	  inLower = false;
	  inUpper = false;
      Objects.requireNonNull(lowerbound);
      Objects.requireNonNull(upperbound);
      return new Range<T>(lowerbound, upperbound);
  }
  
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<?>> Range<T> openClosed(T lowerbound, T upperbound) {
	  inLower = false;
	  inUpper = true;
      Objects.requireNonNull(lowerbound);
      Objects.requireNonNull(upperbound);
      return new Range<T>(lowerbound, upperbound);
  }
  
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<?>> Range<T> closedOpen(T lowerbound, T upperbound) {
	  inLower = true;
	  inUpper = false;
      Objects.requireNonNull(lowerbound);
      Objects.requireNonNull(upperbound);
      return new Range<T>(lowerbound, upperbound);
  }
  
  //[-Infinity, value)
  public static <T extends Comparable<?>> Range<T> lessThan(T value){
	  inLower = true;
	  inUpper = false;
	  isInfinity=String.valueOf(nev_infinity);
	  Objects.requireNonNull(value);
	  return new Range<T>((T) isInfinity,value);
  }
  
  //[value, Infinitive]
  public static <T extends Comparable<?>> Range<T> atLeast(T value){
	  inLower = true;
	  inUpper = true;
	  isInfinity=String.valueOf(pos_infinity);
	  Objects.requireNonNull(value);
	  return new Range<T>(value, (T) isInfinity);
  }
  
  //[-Infinitive, value]
  public static <T extends Comparable<?>> Range<T> atMost(T value){
	  inLower = true;
	  inUpper = true;
	  isInfinity=String.valueOf(nev_infinity);
	  Objects.requireNonNull(value);
	  return new Range<T>((T) isInfinity, value);
  }
  
  //(value, Infinitive]
  public static <T extends Comparable<?>> Range<T> greaterThan(T value){
	  inLower = false;
	  inUpper = true;
	  isInfinity=String.valueOf(pos_infinity);
	  Objects.requireNonNull(value);
	  return new Range<T>(value, (T) isInfinity);
  }
  
  //[-Infinitive, Infinitive]
  public static <T extends Comparable<?>> Range<T> all() {
	  inLower = true;
	  inUpper = true;
	  isInfinity = String.valueOf(nev_infinity) + ", " + String.valueOf(pos_infinity);
	  return new Range<T>((T) String.valueOf(nev_infinity), (T) String.valueOf(pos_infinity));
  }

  public static <T extends Comparable<?>> String asString() {
	  StringBuilder sb = new StringBuilder();
	  
	  if(isInfinity.equals(String.valueOf(nev_infinity) + ", " + String.valueOf(pos_infinity))) {
		  sb.append(isInfinity);
	  }
	  if((inLower && inUpper) == true) {//[value, value]
		  sb.append("]");
	  } else if((inLower && inUpper) == false) {
		  sb.append("(");
		  if(isInfinity.equals(String.valueOf(nev_infinity) + ", " + String.valueOf(pos_infinity))) {
			  sb.append(isInfinity);
		  } else {
			
		  }
	  }
	  
	  return sb.toString();
  }
  
  public String toString(Object object){
	  if (object == null) return "null";
	  Class<?> clazz = object.getClass();
	  StringBuilder sb = new StringBuilder();
	  if(inLower == true) {//[value, ...
		  sb.append("[");
	  } else {
		  sb.append("(");
	  }
	  
	  if(isInfinity.equals(String.valueOf(nev_infinity) + ", " + String.valueOf(pos_infinity))) {
		  sb.append(isInfinity);
	  } else {
		  while (clazz != null && !clazz.equals(Object.class)) {
	          Field[] fields = clazz.getDeclaredFields();
	          for (Field f : fields) {
	              if (!Modifier.isStatic(f.getModifiers())) {
	                  try {
	                      f.setAccessible(true);
	                      if(f.get(object).toString().equals(String.valueOf(nev_infinity))) {
	                    	  sb.append("Infinity").append(",");
	                    	  continue;
	                      }
	                      sb.append(f.get(object)).append(",");
	                  } catch (IllegalAccessException e) {
	                      e.printStackTrace();
	                  }
	              }
	          }
	          clazz = clazz.getSuperclass();
	      }
	      sb.deleteCharAt(sb.lastIndexOf(","));
	  }
	  
      if(inUpper == true) {
		  sb.append("]");
	  } else {
		  sb.append(")");
	  }
      
      return sb.toString();
	}
  
  public static <T extends Comparable<?>> Range<T> parseNotation(String range) {
  	  try {
	  		int length = range.length();
	  		T lowerBound = (T) range.substring(1, range.indexOf(","));
	  		T upperBound = (T) range.substring(range.indexOf(",") +1, length -1);
	
	        if(range.charAt(0) == '(') {
	            if(range.charAt(length - 1) == ')') {
	                return Range.open(lowerBound, upperBound);
	            }
	            return Range.openClosed(lowerBound, upperBound);
	        } else {
	            if(range.charAt(length - 1) == ')') {
	                return Range.closedOpen(lowerBound, upperBound);
	            }
	            return Range.closed(lowerBound, upperBound);
	        }
  	  } catch(IllegalArgumentException ex) {
  		  throw new IllegalArgumentException("Range " + range + " is not valid.");
  	  }
  }
}
