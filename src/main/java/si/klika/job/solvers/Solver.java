package si.klika.job.solvers;

import javax.naming.OperationNotSupportedException;

/**
 * Created by dime on 02/02/15.
 */
public interface Solver {
    public float solve(float a, float b) throws OperationNotSupportedException;
    public boolean supports(String operation);
}
