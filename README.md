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

## Supported Data

LogPulse analyzes security logs to detect the following patterns:

* **Brute Force Attacks**: Multiple failed login attempts (Event ID 4625), especially followed by a success (Event ID 4624).
* **Suspicious Logins**: Logins occurring during unusual hours.
* **Account Creations**: Initialization of new user accounts.
* **Multi-IP Logins**: Single users accessing the system from multiple distinct IP addresses.

### Supported File Formats
You can upload log files in the following formats:
`.xml`, `.log`, `.txt`, `.json`, `.csv`, `.sample`

## Database

The application uses an **In-Memory H2 Database**.

* **Persistence**: Data is stored in RAM and will be reset whenever the application is restarted.
* **Setup**: No manual database installation or configuration is required. The schema is automatically generated on startup.
* **Access**: You can view the H2 console at `http://localhost:8080/h2-console` using:
    * **JDBC URL**: `jdbc:h2:mem:logpulsedb`
    * **User**: `sa`
    * **Password**: `password`

## Usage

1. Open a web browser and navigate to `http://localhost:8080`
2. Verify that the backend connection status indicates it is online
3. Drag and drop or browse to upload a log file (or use the **History** button to view sample data)
4. Select the analysis endpoint type
5. Click the scan button
6. Review the dashboard visualizations and tabular breakdown of the threats detected
7. Export the results to a CSV file if necessary
