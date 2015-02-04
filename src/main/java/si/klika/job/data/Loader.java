package si.klika.job.data;

import si.klika.job.models.Operation;
import si.klika.job.solvers.Solver;

import java.util.List;
import java.util.Map;

/**
 * Created by dime on 02/02/15.
 */
public interface Loader {
    public Map<String, Solver> getSolvers();
    public List<Operation> getOperations();
}
