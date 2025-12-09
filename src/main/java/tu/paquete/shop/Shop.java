package tu.paquete.shop;

public class Shop {
    private final String name;
    private final int price;

    public Shop(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){ return name; }
    public int getPrice(){ return price; }
}
