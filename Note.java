package music;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * 
 * this class stores all required Information to play a note. Child of PerformingArt
 *
 */
class Note extends PerformingArt {
private int note;
private int duration;
private int exceptionCode;
private int octave;

/**
 * Default Constructor for class Note;
 */
public Note() {
	this.note = 0;
	this.duration = 0;
	this.exceptionCode = 0;
	this.octave = 0;
}

/**
 * Overloaded Constructor for class Note, which recives 3 integer values.
 * @param note: Integer between 0 and 127, that corresponds to the range of octave, see [https://content.instructables.com/ORIG/FWX/NBXG/H4AFZWE7/FWXNBXGH4AFZWE7.png]  for chart
 * @param duration: A non negative Integer
 * @param octave: An Integer between -1 - 9(inclusive).
 */
public Note(int note, int duration, int octave) {
	this.note = note;
	this.duration = duration;
	this.octave = octave;
}
/**
 * Setter method for note
 * @param note: An int between 1-127, that corresponds to the octave range
 */
public void setNote(int note) {
	this.note=note;
}
/**
 * Setter method for note
 * @param duration: A non negative integer
 */
public void setDuration(int duration) {
	this.duration=duration;
}

/**
 * Setter method for Octave
 * @param octave: A positive integer between -1 - 9(inclusive)
 */
public void setOctave(int octave) {
	this.octave= octave;
}
/**
 * Inherited Method, that first validaties the values of the note,octave and duration , to make sure they conform to the paramters defined in the constructor/setter.
 * If there any errors with the input with the validate() function call, Mismatch exception is caught and printed.
 * If there are no problems with validate() opens midiSynthesizer and plays the note assigned to this class
 */
@Override
public void play() {
	try {
	this.validate();	
	}
	catch(Exception e){
	System.out.println(e.getMessage());	
	this.exceptionCode=-3;
	}
	
	if(this.exceptionCode!= -3) {
	try {
	Synthesizer midiSynthesizer = MidiSystem.getSynthesizer();
	midiSynthesizer.open();
	Instrument[] instrument = midiSynthesizer.getDefaultSoundbank().getInstruments();
	midiSynthesizer.loadInstrument(instrument[0]);
	MidiChannel[] midiChannels =midiSynthesizer.getChannels();
	midiChannels[0].noteOn(this.note, 100);
		try {
			Thread.sleep(duration);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	catch(Exception e) {
	e.printStackTrace();	
	}
	}
}

/**
 * Check the instance values of note,duration and octave, to make sure they're playable
 * @throws MismatchException:  the value of note (or/and), duration (or/and) octave, didn't conform to the paramters defined earlier. 
 */
public void validate() throws MismatchException {
	
	if(this.note > 127 || this.note < 0) throw new MismatchException("Octave/Note Mismatch");
	if(this.duration <0) throw new MismatchException("Note duration must be non-negative");
	if(this.octave > 9 || this.octave <-1) throw new MismatchException("Octave/Note Mismatch");
	
	if(this.octave!=9) {
		int upperLimit =(this.octave+2)*12-1;
		int lowerLimit = (this.octave+1)*12;	
		if(this.note < lowerLimit || this.note > upperLimit)throw new MismatchException("Octave/Note Mismatch");
	}
	else if(this.note >127 || this.note < 120) throw new MismatchException("Octave/Note Mismatch");
	
}
		
	

/**
 * returns a deep copy of this object.
 * @return: Deep copy of this
 */
public Note getNotes() {
	Note n = new Note();
	n.note = this.note;
	n.duration = this.duration;
	n.exceptionCode = this.exceptionCode;
	n.octave = this.octave;

	return n;
}

/*
 * returns note value
 */
public int getNote() {
	return this.note;
}

/*
 * returns octave value
 */
public int getOctave() {
	return this.octave;
}

/*
 * returns the duration value
 */
public int getDuration() {
	return this.duration;
}
/*
 * returns the value of exceptionCode, 
 * if exceptionCode = 0, no Exceptions occured
 * if exceptionCode =-1, NullPointerException
 * if exceptionCode = -2 ClassCastException
 * if exceptionCode =-3, MismatchException
 */
public int getExceptionCode() {
	return this.exceptionCode;
}
/*
 * sets the value of exceptionCode
 */
public void setExceptionCode(int code) {
	this.exceptionCode=code;
}

/**
 * compareTo checks the instance values(note,octave) of two objects and memory address,if either case is passed, 0 is printed.
 * The value of art is casted onto type note, if it fails ClassCastException is printed. 
 * @param art: An Object thats of type Art, or it's children, that can be casted into type note 
 * @return: 0 if, object addresses are same or instance values of note and octave are same, else return the difference between the note value of this and art,  as long as art isn't null
 */
public int compareTo(Art art) {
	try {
	Note n =  (Note)art;
	// null check
	if (n == null) {
		this.exceptionCode=-1;
		return 1;
	}
	//memory address check
	if (this == n)
		return 0;
	//instance check
	if (this.note == n.note && this.octave == n.octave)
		return 0;
	else {
		return this.note-n.note;
	}
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		this.exceptionCode=-2;
		return 1;
	}
}
}