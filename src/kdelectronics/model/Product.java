package kdelectronics.model;

public class Product {

    private final String code; // No se puede modificar
    private String name;
    private String description;
    private double basePrice;
    private double salePrice;
    private Category category;
    private int availableQuantity;
    private boolean active; // eliminación lógica

    public Product(String code, String name, String description,
                   double basePrice, double salePrice,
                   Category category, int availableQuantity) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.salePrice = salePrice;
        this.category = category;
        this.availableQuantity = availableQuantity;
        this.active = true;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getBasePrice() { return basePrice; }
    public double getSalePrice() { return salePrice; }
    public Category getCategory() { return category; }
    public int getAvailableQuantity() { return availableQuantity; }
    public boolean isActive() { return active; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public void setSalePrice(double salePrice) { this.salePrice = salePrice; }
    public void setCategory(Category category) { this.category = category; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Producto {" +
                "codigo='" + code + '\'' +
                ", nombre='" + name + '\'' +
                ", descripcion='" + description + '\'' +
                ", precioBase=" + basePrice +
                ", precioVenta=" + salePrice +
                ", categoria=" + category +
                ", cantidadDisponible=" + availableQuantity +
                ", estado=" + (active ? "ACTIVO" : "ELIMINADO") +
                '}';
    }
}