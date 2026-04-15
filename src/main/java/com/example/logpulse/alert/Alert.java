package com.example.logpulse.alert;

import jakarta.persistence.*;

@Entity
@Table(name = "alerts")
public class Alert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String message;
	private String severity;

	public Alert() {}

	public Alert(String m, String s) {
		this.message = m;
		this.severity = s;
	}

	public Long getId() {
		return id;
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
