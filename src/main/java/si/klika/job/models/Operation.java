package si.klika.job.models;

import java.lang.String;

/**
 * Created by dime on 02/02/15.
 */
public class Operation {

    private String op;
    private float a;
    private float b;

    private float result;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }
}
