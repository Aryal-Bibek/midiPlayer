package music;

import java.io.*;
import java.util.*;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import org.junit.runner.manipulation.NoTestsRemainException;


/**
 * @author Bibek Aryal 
 */
public class Player {

	public static void main(String[] args) {

		Music m = new Music();
		m.setScore(m.readFile("music2"));
		m.play();
	
	} // end of main
} 
