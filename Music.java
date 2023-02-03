package music;

import java.util.ArrayList;
import java.util.List;

/**
 *Child of PerformingArt. 
 *Composition of Note class
 *This class is designed to play a series of notes by invoking the methods in the note class 
 *
 */
class Music extends PerformingArt{
	private String composer;
	private String name;
	private ArrayList<Note> score;
	private int exceptionCode;

	/**
	 * Default Constructor of Music, sets the value of String instances to null.
	 */
	public Music() {
		this.composer = null;
		this.name = null;
		this.score = new ArrayList<Note>();
		exceptionCode = 0;
	}
	/**
	 * Overloaded Constructor of Music, that deep copies the values of all the arguements
	 * @param composer: <String> name of the composer
	 * @param name: <String> of the artist
	 * @param list: An ArrayList of type <Note>, which contains valid instances of Note for each valid element.
	 */
	public Music(String composer, String name, ArrayList<Note> list) {
		this.composer = new String(composer);
		this.name = new String(name);
		this.setScore(list);
	}
	
	/**
	 * Overloaded Constructor of Music, that recieves a List of <String> Instead of an ArrayList of <Note>. Deep Copies all the values passed
	 * and calls upon a setter method to set the value of score.
	 * @param composer: String name of the composer
	 * @param name: String name of the Artist.
	 * @param list: List of type <String> that contains a string in the (number number number) format in each element
	 */
	public Music(String composer, String name, List<String> list) {
		this.composer = new String(composer);
		this.name = new String(name);
		this.setScore(list);			
			
		}
	/**
	 * Sets the value of name
	 * @param name: <String> name of artist
	 */
	public void setName(String name) {
		this.name = new String(name);
	}
	/**
	 * Sets the value of composer
	 * @param composer: <String> name of composer
	 */
	public void setComposer(String composer) {
		this.composer= new String(composer);
	}
	/**
	 * Sets the value of Integer
	 * @param code: Integer
	 */
	public void setExceptionCode(int code) {
		this.exceptionCode= code;
	}
	
	/**
	 * Seperates the String ,at each valid index,into 3 <String>s and parses those into Integer and creates a new object <Note> that's then added to the score Instance
	 * @param list: list is a List of <String>, that're all properly formatted to conform to the Note standard in each valid element
	 */
	public void setScore(List<String> list) {
		
		this.score = new ArrayList<Note>();
		for(int i = 0; i < list.size(); i++) {
			
			//trims the trailing empty spaces
			String s = new String (list.get(i)).trim();
			
			String a1 = "";
			String a2 = "";
			String a3 = "";
			String temp = "";
			
			int j = 0;
	//Looping to Seperate the code into 3 parts, a1,a2,a3, which will be parsed later to int
			for(int k = 0; k < s.length();k++) {
				if(s.charAt(k)!= ' ')
				temp +=s.charAt(k);
				else if(s.charAt(k) == ' ') {
					if(j == 0 ) {
						j++;
						a1 = temp;
						temp = "";
					}
					else {
						a2= temp;
						temp ="";
						j++;
					}
				}
				
				if(k+1 == s.length())
					a3= temp;			
									
			} 	
			
	//Creating a new Note to be stored score with the parsed values of String
			Note n = new Note(Integer.parseInt(a1),Integer.parseInt(a2),Integer.parseInt(a3));
			this.score.add(n);

			}
		
	}
	/**
	 * This method deep copies the values of list and stores them to scores
	 * @param list: ArrayList of <Notes>, that's is valid for every index.
	 */
	public void setScore(ArrayList<Note> list) {
		this.score = new ArrayList<Note>();
		for (int i = 0; i < list.size(); i++) {
			this.score.add(list.get(i).getNotes());
		}
	}
		
	/**
	 * 
	 * @return: null, if composer == null else, else return composer name
	 */
	public String getComposer() {
		if (this.composer == null)
			return null;
		return new String(this.composer);
	}

	/**
	 * 
	 * @return: null, if name == null else, return name;
	 */
	public String getName() {
		if (this.name == null)
			return null;
		return new String(this.name);
	}
	/**
	 * 
	 * @return:0, if no Exceptions Occur, -1 if NullPointerException occurs, -2 if 	ClassCastException Occurs
	 */
	public int getExceptionCode() {
		return this.exceptionCode;
	}
	
	/**
	 * 
	 * @return: A deep copy of all Values of score.
	 */
	public ArrayList<Note> getScore() {
		ArrayList<Note> get = new ArrayList<Note>();
		for (int i = 0; i < this.score.size(); i++) {
			get.add(this.score.get(i).getNotes());
		}
		return get;
	}

	/**
	 * Calls the play() method in Note for each element of score.
	 */
	public void play() {
		for(int i =0; i <this.score.size(); i++) {
			this.score.get(i).play();
		}
	}

	/**
	 * 
	 * @param art: Is of type <Art> or it's subclass, that can cast to type <Music>.
	 * @return: 0 if art and this share the same memory address, or if all the instance values are equal, or the values of the instance values are equal, else return -1.
	 * 
	 */
	public int compareTo(Art art) {
		try {
		Music m = (Music) art;
		
		//check if art is null
		if (m == null) {
			this.exceptionCode=-1;
			return -1;
		}
		//checking memory address
		if (this == m)
			return 0;
		//comparing 2 instances
		if (this.name == m.name && this.composer == m.composer) {
			//comparing the address of score
			if (this.score == m.score)
				return 0;
			//comparing all the values of score
			for (int i = 0; i < this.score.size(); i++) {
				if (this.score.get(i).compareTo(m.score.get(i)) != 0)
					return 1;
			}
			return 0;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			this.exceptionCode=-2;
		}
		return -1;
		
	}
}