package si.klika.job.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;

import com.google.gson.JsonSyntaxException;

import si.klika.job.data.JSONParser;
import si.klika.job.data.JSONParsingException;
import si.klika.job.models.Operation;
import si.klika.job.solvers.*;

/**
 * Main class for running JSON parser. Here we read input arguments, if any, 
 * start parsing JSON file, calculate results and save output back to json file.
 * 
 * @author grega
 *
 */

public class MainParser {
	
	private static String inputJSON = "resources/input.json";		//default input file path
	private static String outputJSON = "resources/output.json";		//default output file path
	
	public static void main(String[] args) {
		if(args.length == 2) { //check if input and output file given in arguments
			inputJSON = args[0];
			outputJSON = args[1];
		}
		else {
			System.out.println("Usage: [inputFilePath] [outputFilePath]");
			System.out.println("NOTICE: No input and output files passed in arguments. Using defaults.");
		}
		
		JSONParser parser = new JSONParser(inputJSON, outputJSON); 		//parse json file and save it to list of Operation objects
		
		System.out.println("-----------------------------------------");
		System.out.println("NOTICE: Start parsing from JSON file: "+inputJSON);
		try{
			parser.loadFromJSON();
		}
		catch (JSONParsingException e){
			System.out.println(e.getMessage());
			System.out.println("-----------------------------------------");
			System.exit(-1);
		}
	
		/* Loop the parsed json list and calculate results */
		for(Operation op : parser.getOperations()) {
			Solver currentSolver = parser.getSolvers().get(op.getOp()); //first check if operation supported
			if(currentSolver != null) {
				try {
					op.setResult(currentSolver.solve(op.getA(), op.getB()));
				}
				catch (OperationNotSupportedException e) {
					System.out.println("- ERROR :"+ e.getMessage());
					System.out.println("-----------------------------------------");
					System.exit(-1);
				}
			}
			else {
				System.out.println("- ERROR: Operation "+ op.getOp() + " not supported");
				System.out.println("-----------------------------------------");
				System.exit(-1);
			}
		}
		
		/* Save back to json */
		System.out.println("NOTICE: Saving results to JSON file: "+outputJSON);
		try{
			parser.saveToJSON();
		}
		catch (JSONParsingException e){
			System.out.println("Error saving to JSON: "+e.getMessage());
			System.out.println("-----------------------------------------");
			System.exit(-1);
		}
		System.out.println("-----------------------------------------");
	}
}
