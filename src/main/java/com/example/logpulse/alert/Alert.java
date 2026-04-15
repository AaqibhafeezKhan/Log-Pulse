
package com.example.logpulse.alert;

public class Alert {
	private String message;
	private String severity;

	public Alert(String m, String s) {
		this.message = m;
		this.severity = s;
	}

	public String getMessage() {
		return message;
	}

	public String getSeverity() {
		return severity;
	}

	@Override
	public String toString() {
		return "[" + severity + "] " + message;
	}
}
