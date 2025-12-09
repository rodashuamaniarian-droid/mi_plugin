
package tu.paquete.shop;

public enum Upgrade {
    SHARPNESS("Sharpness", 5),
    REINFORCED_ARMOR("Armor", 3),
    RESOURCE_PACKER("Resources", 4);

    private final String name;
    private final int maxLevel;

    Upgrade(String name, int maxLevel) {
        this.name = name;
        this.maxLevel = maxLevel;
    }

    public String getName() { return name; }
    public int getMaxLevel() { return maxLevel; }

    public static Upgrade fromName(String s) {
        for (Upgrade u : values()) if (u.name().equalsIgnoreCase(s) || u.getName().equalsIgnoreCase(s)) return u;
        return null;
    }
}
