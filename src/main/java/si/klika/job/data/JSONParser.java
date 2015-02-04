package si.klika.job.data;

import java.util.ArrayList;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Hashtable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;

import si.klika.job.models.Operation;
import si.klika.job.solvers.*;

//gson imports
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * JSONParser class is used for manipulation with JSON files.
 * 
 */

public class JSONParser implements Loader{
	
	private Map<String, Solver> solvers;
	private List<Operation>		operations;
	private String 				inputFilePath;
	private String				outputFilePath;
	private Gson				gson;
	

	/**
	 * Default contructor that initializes solvers and sets filepaths for JSON files.
	 * 
	 * @param _inputFilePath
	 * @param _outputFilePath
	 */
	public JSONParser(String _inputFilePath, String _outputFilePath) {	
		this.inputFilePath = _inputFilePath;
		this.outputFilePath = _outputFilePath;
		this.gson = new GsonBuilder().registerTypeAdapter(float.class, new JSONFloatSerializer()).setPrettyPrinting().create(); 
		
		this.solvers = new Hashtable();
		
		this.solvers.put("+", new SolveSum());
		this.solvers.put("-", new SolveSubtract());
		this.solvers.put("/", new SolveDivision());
		this.solvers.put("*", new SolveMultiplication());		
	}
	
	/**
	 * This method parses JSON file and fills list of parsed operations.
	 * If any exceptions are caught while parsing, JSONParsingException is thrown.
	 * 
	 * @throws JSONParsingException
	 */
	public void loadFromJSON() throws JSONParsingException{
		try {
			/* Pass read string to gson parser and save output to operations list */
			operations = gson.fromJson(JSONToString(this.inputFilePath), new TypeToken<List<Operation>>(){}.getType());
		}
		catch (JsonParseException e){
			throw new JSONParsingException("- ERROR: Error parsing JSON file: "+e.getMessage());
		}
		catch (JSONParsingException e){
			throw new JSONParsingException("- ERROR: JSON file not found: "+e.getMessage());
		}
	}
	
	/**
	 * This method saves modified operations list back to JSON file.
	 * 
	 * @throws JSONParsingException
	 */
	public void saveToJSON() throws JSONParsingException{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputFilePath));
			writer.write(gson.toJson(this.operations));
			writer.close();
		}
		catch (IOException e){
			throw new JSONParsingException("- ERROR: IO Exception in saving json: "+e.getMessage());
		}
		catch (JsonParseException e){
			throw new JSONParsingException("- ERROR: Error writing JSON file: "+e.getMessage());
		}
	}
	
	/**
	 * Getter for solver Map
	 * 
	 * @return map of solvers
	 */
	public Map<String, Solver> getSolvers() {
		 return this.solvers;
	}
	
	/**
	 * Getter for operations List
	 * 
	 * @return List of operations
	 */
	public List<Operation> getOperations() {
		 return this.operations;
	}
	
	
	/**
	 * Static method to convert json file to string
	 * 
	 * @param filepath
	 * @return json String
	 * @throws JSONParsingException
	 */
	public static String JSONToString(String filepath) throws JSONParsingException {
		/* Read from file line by line and save it to StringBuilder */
		String line;
		StringBuilder sb = new StringBuilder();
		try{
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			while ((line = br.readLine()) != null) sb.append(line);
			br.close();
		}
		catch (FileNotFoundException e){
			throw new JSONParsingException("- ERROR: JSON file not found: "+e.getMessage());
		}
		catch (IOException e){
			throw new JSONParsingException("- ERROR: IO Exception: "+e.getMessage());
		}
				
		return sb.toString();
	}
	
	/**
	 * Custom JSON serializer class to properly format output numbers
	 * 
	 * @author grega
	 *
	 */
	public static class JSONFloatSerializer implements JsonSerializer<Float> {
		private static final int DecimalPlaces = 5;
		
	    public JsonElement serialize(Float value, Type theType, JsonSerializationContext context) { 	
	    	if (value.isNaN())
	    		return new JsonPrimitive(0); // Convert NaN to zero 
	    	else if (value.isInfinite())
            	return new JsonPrimitive(value); 
	    	else
            	/* format float */
            	return new JsonPrimitive((new BigDecimal(value)).setScale(DecimalPlaces, BigDecimal.ROUND_HALF_UP).stripTrailingZeros()); 
        } 
	}
}


