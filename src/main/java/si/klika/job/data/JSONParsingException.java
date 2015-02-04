package si.klika.job.data;


/**
 * Custom exception class to handle JSON errors
 * 
 * @author grega
 *
 */
public class JSONParsingException extends Exception{
	public JSONParsingException (String message) {
		super(message);
	}
}
