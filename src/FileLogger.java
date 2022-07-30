import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME =  "StudentFileOutput.txt";
    static String workingDir = System.getProperty("user.dir");
    static File file = new File(workingDir + File.separator + FILE_LOGGER_NAME);

    static {
        try {
            FileWriter output = new FileWriter(file.getAbsolutePath()); // open the file
            output.close();
        } catch (SecurityException securityException) {
            System.out.println("Write permission denied!");
            System.out.println("There was an error opening the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void log (String message) {
        try {
            // Open given file in append mode by creating an
            FileWriter out = new FileWriter(file, true);
            // Writing on output stream
            out.write(message + "\n");
            // Closing the connection
            out.close();
        } catch (IOException e) {
            System.out.println("An exception occurred while logging to file\n" + e);
        }
    }
}



