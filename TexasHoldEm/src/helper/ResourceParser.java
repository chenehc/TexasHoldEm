package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResourceParser {

	static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
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

	public static String parseHelp() throws IOException{
		String help = new String();
		help = readFile("src/res/Help.txt");
		return help;
	}

	public static String parseRule() throws IOException{
		String rule = new String();
		rule = readFile("src/res/Rules.txt");
		return rule;
	}

//	public static void main(String[] args) throws IOException {
//		System.out.println(parseHelp());
//		System.out.println(parseRule());
//	}
}