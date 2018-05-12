package engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static ArrayList<String> knowledgeBase;
	public static ArrayList<String> symbols;
	public static String query;

	public static void main(String[] args) {
		// init variabels
		knowledgeBase = new ArrayList<String>();
		symbols = new ArrayList<String>();
		// Read File
		readFile(args[1]);
		// Get method from first String
		String method = args[0];
		Method thisMethod = null;
		
		// Instantiate the appropriate method
		switch (method.toUpperCase()) {
			case "TT":
				thisMethod = new TruthTable(knowledgeBase, symbols, query);
				break;
			case "FC":
				thisMethod = new ForwardChaining(knowledgeBase, symbols, query);
				break;
			default:
				System.out.println(method + " is not a method, exiting");
				System.exit(1);
		}

		thisMethod.check();
		}

	private static void readFile(String fileName) {
		try {
			// Set up file reader
			FileReader reader = new FileReader(fileName);
			BufferedReader file = new BufferedReader(reader);

			String line = null;
			while ((line = file.readLine()) != null) {
				switch (line) {
				case "TELL":
					// Set knowledge base
					parseTellCommand(file.readLine());
					break;
				case "ASK":
					// Set Query
					query = file.readLine();
					break;
				default:
					System.out.println("Invalid command: " + line);
					System.exit(1);
				}
			}

			file.close();

		} catch (FileNotFoundException ex) {
			System.out.println(fileName + " not found.");
			System.exit(1);
		} catch (IOException ex) {
			System.out.println("Error reading " + fileName);
			System.exit(1);
		}
	}

	private static void parseTellCommand(String line) {
		// Remove any spaces and split commands at each semi-colon
		// Assign KB to Agent
		String[] kb = line.replaceAll("\\s", "").split(";");
		for (int i = 0; i < kb.length; i++) {
			knowledgeBase.add(kb[i]);
		}
		// Extract Symbols by removing any spaces and splitting by Horn Clause Operators
		String[] allSymbols = line.replaceAll("\\s", "").split(";|=>|&");
		for (int i = 0; i < allSymbols.length; i++) {
			// Check that Symbol is unique
			if (!symbols.contains(allSymbols[i])) {
				// Assign Unique Symbol to Agent
				symbols.add(allSymbols[i]);
			}
		}
	}
}
