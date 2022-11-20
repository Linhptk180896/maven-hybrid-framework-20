package exception;

public class BrowserNoSupport extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public BrowserNoSupport(String browserName) {
		super(String.format("Browser with name %s is invalid ", browserName.toUpperCase()));
	}

}
