/* ======================
 * @FileName: InvalidRangeArgumentException.java
 * @Author: HangTo
 * 1.0 Creation
 ========================*/
package src.main.java.io.ubitec.interview_challenges;

public class InvalidRangeArgumentException extends RuntimeException{
	private String message;
	
	public InvalidRangeArgumentException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
		this.setMessage(message);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//Exception will be throw if lowerbound is greater than upperbound

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
