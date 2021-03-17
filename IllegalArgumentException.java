/* ======================
 * @FileName: IllegalArgumentException.java
 * @Author: HangTo
 * 1.0 Creation
 ========================*/
package src.main.java.io.ubitec.interview_challenges;

public class IllegalArgumentException extends RuntimeException{
	
	public IllegalArgumentException(String message) {
		System.out.println(message);
    }

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//Exception will be throw if lowerbound is greater than upperbound
}
