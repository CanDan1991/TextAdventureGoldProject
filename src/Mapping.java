import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /** TODO
     * create a static LocationMap object
     */
    static LocationMap locationMap = new LocationMap();

    /** TODO
     * create a vocabulary HashMap to store all directions a user can go
     */
    HashMap<String, String> vocabulary = new HashMap();

    /** TODO
     * create a FileLogger object
     */
    FileLogger fileLogger  = new FileLogger();

    /** TODO
     * create a ConsoleLogger object
     */
    ConsoleLogger consoleLogger = new ConsoleLogger();


    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /** TODO
         * complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */
        /*for(Location l : locationMap.values()) {
            vocabulary.put(l.getLocationId() + "",  l.getDescription());
        }*/

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

        /** TODO
         * create a Scanner object
         */
        Scanner scanner = new Scanner(System.in);

        /**
         * initialise a location variable with the INITIAL_LOCATION
         */
        Location startLocation = locationMap.get(INITIAL_LOCATION);


        while (true) {

            /** TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            fileLogger.log(startLocation.getDescription());
            consoleLogger.log(startLocation.getDescription());

            /** TODO
             * verify if the location is exit
             */
            if (startLocation.getDescription().equals("YOU ARE SITTING IN FRONT OF A COMPUTER LEARNING JAVA.")){
                break;
            }
             /** TODO
              * get a map of the exits for the location
              */
             Map<String, Integer> exits = startLocation.getExits();

             /** TODO
              * print the available exits (to both console and file)
              * crosscheck with the ExpectedOutput files
              * Hint: you can use a StringBuilder to append the exits
              */
             StringBuilder sb = new StringBuilder();
             for (Map.Entry<String, Integer> entry : startLocation.getExits().entrySet()) {
                 sb.append(entry.getKey() + ", ");
             }
             consoleLogger.log("Available exits are " + sb.toString());
             fileLogger.log("Available exits are " + sb.toString());
             /** TODO
              * input a direction
              * ensure that the input is converted to uppercase
              */
             String userDirection = scanner.nextLine().toUpperCase();
             /** TODO
              * are we dealing with a letter / word for the direction to go to?
              * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
              * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
              * if the input contains multiple words, extract each word
              * find the direction to go to using the vocabulary mapping
              * if multiple viable directions are specified in the input, choose the last one
              */
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

             /** TODO
              * if user can go in that direction, then set the location to that direction
              * otherwise print an error message (to both console and file)
              * check the ExpectedOutput files
              */
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
        /**TODO
         * run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping runner = new Mapping();
        runner.mapping();
    }

}
