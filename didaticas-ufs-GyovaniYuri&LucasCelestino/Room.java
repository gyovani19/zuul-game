import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;



public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;
    public Room(String description)
    {
        this.description = description;

        items = new HashMap<>();
        exits = new HashMap<>();
    }


    public void addItem(String name, String description, double weight) {
        // check if the item already exists in the map.
        // if exists just don´t add it.
        Set<String> keys = items.keySet();
        for (String item : items.keySet()) {
            if(item.equals(name))
                return;
        }
        Item newItem = new Item(name, description, weight);
        items.put(name, newItem);
    }
    public void printItems() {
        if (items.isEmpty()) {
            System.out.println("Sem itens na sala.");
        } else {
            System.out.println("Items nessa sala:");
            for (Item item : items.values()) {
                System.out.println("- " + item.getName() + ": " + item.getDescription() + " que pesa: " + item.getWeight());
            }
        }
    }

    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    public Item delItem(String itemName) {
        return items.remove(itemName);
    }



    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "Você está em " + description + ".\n" + getExitString();

    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Saídas:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

