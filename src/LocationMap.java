import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME =  "locations.txt";
    private static final String DIRECTIONS_FILE_NAME =  "directions.txt";

    /** TODO
     * create a static locations HashMap
     */
    static Map<Integer, Location> locationHashMap = new HashMap<>();
    static {
        /** TODO
         * create a FileLogger object
         */
        FileLogger fileLogger = new FileLogger();
        /** TODO
         * create a ConsoleLogger object
         */
        ConsoleLogger consoleLogger = new ConsoleLogger();
        /** TODO
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put the location and a new Location object in the locations HashMap, using temporary empty hashmaps for exits
         */
        String workingDir = System.getProperty("user.dir");
        File locationFile = new File(workingDir + File.separator + LOCATIONS_FILE_NAME);

        try(BufferedReader br = new BufferedReader(new FileReader(locationFile))) {
            System.out.println("Available Locations: ");
            for (String line; (line = br.readLine()) != null;) {
                if(line.trim() != "") {
                    String[] locationArray = line.split(",");
                    int locationId = Integer.parseInt(locationArray[0]);
                    String location = line.substring(line.indexOf(",") + 1);
                    consoleLogger.log(location);
                    //fileLogger.log(location);
                    Location myLocation = new Location(locationId, location, new HashMap<>());
                    locationHashMap.put(locationId, myLocation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * add the exits for each location
         */
        File directionsFile = new File(workingDir + File.separator + DIRECTIONS_FILE_NAME);
        try(BufferedReader br = new BufferedReader(new FileReader(directionsFile))) {
            System.out.println("Available directions:");
            for(String line; (line = br.readLine()) != null; ) {
                if(line.trim() != "") {
                    String[] locArray = line.split(",");
                    int locationID = Integer.parseInt(locArray[0]);
                    String direction = locArray[1];
                    String destination = locArray[2];

                    consoleLogger.log(locationID + ": " + direction + ": " + destination);
                    fileLogger.log(locationID + ": " + direction + ": " + destination);

                    String description = "";
                    if (locationHashMap.get(locationID).getDescription() != null){
                        description = locationHashMap.get(locationID).getDescription();
                    }

                    Map<String, Integer> exits = new HashMap<>();
                    Location l = locationHashMap.get(locationID);//new Location(locationID, description, exits);
                    l.addExit(direction, Integer.parseInt(destination));
                    locationHashMap.replace(locationID, l);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**TODO
     * implement all methods for Map
     * @return
     */
    @Override
    public int size() {
        //TODO
        return locationHashMap.size();
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return locationHashMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        //TODO
        return locationHashMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //TODO
        return locationHashMap.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        //TODO
        return locationHashMap.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        //TODO
        return put(key, value);
    }

    @Override
    public Location remove(Object key) {
        //TODO
        return locationHashMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        //TODO
        locationHashMap.putAll(m);
    }

    @Override
    public void clear() {
        //TODO
        locationHashMap.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locationHashMap.keySet();
    }

    @Override
    public Collection<Location> values() {
        //TODO
        return locationHashMap.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        //TODO
        return locationHashMap.entrySet();
    }
}
