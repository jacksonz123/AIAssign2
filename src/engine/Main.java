package engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println(args[0] + " " + args[1]);
		readFile(args[1]);
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
						System.out.println(file.readLine());
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
		String[] knowledgeBase = line.replaceAll("\\s", "").split(";");
		for (int i = 0; i < knowledgeBase.length; i++) {
			System.out.println(knowledgeBase[i]);
		}
	}
}
