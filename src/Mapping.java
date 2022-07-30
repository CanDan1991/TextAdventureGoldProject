import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    static LocationMap locationMap = new LocationMap();

    HashMap<String, String> vocabulary = new HashMap();

    FileLogger fileLogger  = new FileLogger();

    ConsoleLogger consoleLogger = new ConsoleLogger();


    public Mapping() {
        vocabulary.put("QUIT", "Q");
        vocabulary.put("U", "U");
        vocabulary.put("UP", "U");
        vocabulary.put("D", "D");
        vocabulary.put("DOWN", "D");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");
        vocabulary.put("NORTH-EAST", "NE");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("NORTH-WEST", "NW");
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("SOUTH-EAST", "SE");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("SOUTH-WEST", "SW");
        vocabulary.put("SOUTHWEST", "SW");



    }

    public void mapping() {
        Scanner scanner = new Scanner(System.in);
        Location startLocation = locationMap.get(INITIAL_LOCATION);


        while (true) {
            fileLogger.log(startLocation.getDescription());
            consoleLogger.log(startLocation.getDescription());
            if (startLocation.getDescription().equals("YOU ARE SITTING IN FRONT OF A COMPUTER LEARNING JAVA.")){
                break;
            }
             Map<String, Integer> exits = startLocation.getExits();
             StringBuilder sb = new StringBuilder();
             for (Map.Entry<String, Integer> entry : startLocation.getExits().entrySet()) {
                 sb.append(entry.getKey() + ", ");
             }
             consoleLogger.log("Available exits are " + sb.toString());
             fileLogger.log("Available exits are " + sb.toString());
             String userDirection = scanner.nextLine().toUpperCase();
             if (userDirection.length() > 3) { //Is a word, which will help get the value(direction)
                 String[] userDirectionsArray = userDirection.split(" ");
                     //There are more words describing direction for example, "go west"
                     for (Map.Entry<String, String> entry : vocabulary.entrySet()) {
                         for (String d : userDirectionsArray) {
                             if (entry.getKey().equals(d.trim())) {
                                 userDirection = entry.getValue();
                             }
                         }
                     }

             } else {
                 for (Map.Entry<String, String> entry : vocabulary.entrySet()) {
                         if (entry.getKey().equals(userDirection)) {
                             userDirection = entry.getValue();
                         }

                 }
             }
             if (sb.toString().contains(userDirection)) {
                 int nextLocationID = startLocation.getExits().get(userDirection);
                 startLocation = locationMap.get(nextLocationID);
             } else {
                 consoleLogger.log("You cannot go in that direction");
                 fileLogger.log("You cannot go in that direction");
             }
        }
    }

    public static void main(String[] args) {
        Mapping runner = new Mapping();
        runner.mapping();
    }

}
