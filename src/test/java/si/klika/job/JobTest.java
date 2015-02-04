package si.klika.job;

import java.io.File;
import org.junit.Test;
import junit.framework.AssertionFailedError;
import static org.junit.Assert.fail;
import org.skyscreamer.jsonassert.*;
import org.json.JSONException;
import si.klika.job.data.JSONParser;
import si.klika.job.data.JSONParsingException;


/**
 * Created by dime on 02/02/15.
 */
public class JobTest {
    @Test
    public void test() {  
    	String inputFile = "resources/output_expected.json";
    	String outputFile = "resources/output.json";
    		
    	try{
    		String expectedResult = JSONParser.JSONToString(inputFile);
    		String result = JSONParser.JSONToString(outputFile);
    		
    		JSONAssert.assertEquals(expectedResult, result, true);
    	}
    	catch(JSONParsingException e){
    		fail("- ERROR testing: "+e.getMessage());
    	}
    	catch(JSONException e){
    		fail("- ERROR testing: "+e.getMessage());
    	}  	
    }

}
