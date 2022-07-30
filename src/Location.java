import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Location {
    private final int locationID;
    private final String description;
    private Map<String, Integer> exits = new LinkedHashMap<>();

    public Location(int locationId, String description, Map<String, Integer> exits) {
        this.locationID = locationId;
        this.description = description;

        if(exits != null){
            this.exits = exits;
        }else{
            exits = new HashMap<>();
        }
    }

    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }

    public int getLocationId() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        Map<String, Integer> newExits = exits;
        return newExits;
    }
}
