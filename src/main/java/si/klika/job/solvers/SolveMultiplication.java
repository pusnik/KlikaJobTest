package si.klika.job.solvers;

import javax.naming.OperationNotSupportedException;


/**
 * SolveMultiplication implements Solver interface and solves multiplication
 * 
 * @author grega
 *
 */

public class SolveMultiplication implements Solver{
	/**
	 * Solve the multiplication operation
	 * 
	 * @param a
	 * @param b
	 * @return float result of multiplication
	 * @throws OperationNotSupportedException
	 */
	public float solve(float a, float b) throws OperationNotSupportedException {
		if(Float.isNaN(a) || Float.isNaN(b))
			throw new OperationNotSupportedException("Parameters not a number");
		
		return a*b;	
	}
	
	/**
	 * Check if operation is supported
	 * 
	 * @param operation
	 * @return true if is supported otherwise false
	 */
    public boolean supports(String operation) {
    	if(operation.equals("*")) return true;
    	
    	return false;
    }	
}
