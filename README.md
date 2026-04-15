# LogPulse

LogPulse is a security log analysis and detection engine. It processes log events to identify brute force attacks, suspicious logins, account creations, and multiple IP login attempts.

## Prerequisites

* Java 17
* Maven 3.9+

## Running the Application

To build and run the application locally:

1. Compile the code and build the application:
   ```bash
   mvn clean package
   ```

2. Run the Spring Boot application:
   ```bash
   java -jar target/log-pulse-0.0.1-SNAPSHOT.jar
   ```

The application will start on port 8080.

## Usage

1. Open a web browser and navigate to `http://localhost:8080`
2. Verify that the backend connection status indicates it is online
3. Drag and drop or browse to upload a log file
4. Select the analysis endpoint type
5. Click the scan button
6. Review the dashboard visualizations and tabular breakdown of the threats detected
7. Export the results to a CSV file if necessary
