import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Location {

    /** TODO
     * declare private final locationId, description, exits
     */

    private final int locationID;
    private final String description;
    private Map<String, Integer> exits = new LinkedHashMap<>();



    public Location(int locationId, String description, Map<String, Integer> exits) {
        /** TODO
         * set the locationId and the description
         */
        this.locationID = locationId;
        this.description = description;

        /** TODO
         * if exits are not null, set the exit
         * Note that exits should be of type LinkedHashMap to maintain the insertion order
         * otherwise, set the exits LinkedHashMap to (Q,0)
         */
        if(exits != null){
            this.exits = exits;
        }else{
            exits = new HashMap<>();
        }
    }

    protected void addExit(String direction, int location) {
        /** TODO
         * put the direction and the location in the exits LinkedHashMap
         */
        exits.put(direction, location);
    }

    public int getLocationId() {
        /** TODO
         * complete getter to return the location id
         */
        return locationID;
    }

    public String getDescription() {
        /** TODO
         * complete getter to return the description
         */
        return description;
    }

    public Map<String, Integer> getExits() {
        /** TODO
         * complete getter to return a copy of exits
         * (preventing modification of exits from outside the Location instance)
         */
        Map<String, Integer> newExits = exits;
        return newExits;
    }
}
