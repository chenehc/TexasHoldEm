package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceParser {
	/**
	 * Method that reads from a file source 
	 * @param filePath String - file path
	 * @return String
	 * @throws IOException
	 */
	private static String readFile(String filePath) throws IOException {
		InputStream in = ResourceParser.class.getResourceAsStream(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}
	
	/**
	 * Method parses the help file and returns the parsed text
	 * @return String
	 * @throws IOException
	 */
	public static String parseHelp() throws IOException{
		String help = new String();
		help = readFile("Help.txt");
		return help;
	}
	
	/**
	 * Method parses the rule file and returns the parsed text
	 * @return String
	 * @throws IOException
	 */
	public static String parseRule() throws IOException{
		String rule = new String();
		rule = readFile("Rules.txt");
		return rule;
	}

//	public static void main(String[] args) throws IOException {
//		System.out.println(parseHelp());
//		System.out.println(parseRule());
//	}
}
