/*
 * This class holds information about items in the zuul-world game.
 */
public class Item {
    private String description;
    private String name;
    private double weight;

    public Item(String name, String description, double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    public Double getWeight() {
        return weight;
    }
}