package si.klika.job.solvers;

import javax.naming.OperationNotSupportedException;

/**
 * SolveDivision implements Solver interface and solves divide operation
 * 
 * @author grega
 *
 */

public class SolveDivision implements Solver {
	/**
	 * Solve the division operation
	 * 
	 * @param a
	 * @param b
	 * @return float result of division
	 * @throws OperationNotSupportedException
	 */
	public float solve(float a, float b) throws OperationNotSupportedException{
		if(Float.isNaN(a) || Float.isNaN(b))
			throw new OperationNotSupportedException("Parameters not a number");
		
		return a/b;
	}
	
	/**
	 * Check if operation is supported
	 * 
	 * @param operation
	 * @return true if is supported otherwise false
	 */
    public boolean supports(String operation){
    	if(operation.equals("/")) return true;
    	
    	return false;
    }	
}
