package sample;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Plant {
    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                ", sort='" + sort + '\'' +
                '}';
    }

    static private ArrayList<Plant> plants=new ArrayList<>();
private long id;
private String name;
private String colour;
private BigDecimal price;
private String sort;

    public Plant() {
    }

    public Plant(String name, String colour, BigDecimal price, String sort) {
        this.name = name;
        this.colour = colour;
        this.price = price;
        this.sort = sort;
    }

    public Plant(long id, String name, String colour, BigDecimal price, String sort) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.price = price;
        this.sort = sort;
    }
    public static void addPlant(long id, String name, String colour, BigDecimal price, String sort)
    {
        plants.add((new Plant(id,name,colour,price,sort)));
    }

   // service.update(new Book(authorField.getText(),nameField.getText(),styleField.getText(),bindingField.getText(),new BigDecimal(priceField.getText())));

    public static ArrayList<Plant> getPlants() {
        return plants;
    }

    public static void setPlants(ArrayList<Plant> plants) {
        Plant.plants = plants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

public static void clearPlantArray(){
        plants.clear();
}
}
