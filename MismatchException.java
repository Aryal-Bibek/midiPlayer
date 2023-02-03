package music;

/**
 * Child of Exception, created to throw exception if instances of Note don't conform the requirement before being played
 * 
 */
class MismatchException extends Exception {
	public MismatchException() {
		super();
	}
	public MismatchException(String message) {
		super(message);
	}
}