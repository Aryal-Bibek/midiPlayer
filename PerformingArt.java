package music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class PerformingArt implements Art {

	public static int exceptionCode;


	/**
	 * 
	 * Abstract class, with a static method, that inherits Exception and implements Art.
	 *
	 */
	/**
	 *  This method reads from a file and stores each line in an ArrayList, which follows the format "number number number".
	 *  If there is a problem with the file the value of exception code is set to -1.
	 * @param:string is the file name from which you want to read, which exists in the same workspace
	 * @return:str, which is an ArrayList of strings, each element holding a line from the file.
	 * 
	 */
	public static List<String> readFile(String string) {

		exceptionCode = 0;
		ArrayList<String> str = new ArrayList<String>();

		try {
			Scanner file = new Scanner(new File(string));
			
			while (file.hasNextLine()) {
				String s = file.nextLine();
				str.add(s);
			}
		} catch (Exception e) {
			exceptionCode = -1;
			System.out.println(e.getMessage());
		}

		return str;
	}
	/**
	 * Inherited method from Interface Art.
	 */
	@Override
	public void play() {

	}

}