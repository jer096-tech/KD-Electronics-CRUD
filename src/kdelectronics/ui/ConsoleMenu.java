package kdelectronics.ui;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import kdelectronics.model.Category;
import kdelectronics.model.Product;
import kdelectronics.service.ProductService;

public class ConsoleMenu {

    private final ProductService service;
    private final Scanner sc;

    public ConsoleMenu(ProductService service) {
        this.service = service;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        int option;
        do {
            printMenu();
            option = readInt("Seleccione una opción: ");

            try {
                switch (option) {
                    case 1 -> createProduct();
                    case 2 -> readByCode();
                    case 3 -> updateProduct();
                    case 4 -> deleteProduct();
                    case 5 -> listAll();
                    case 0 -> System.out.println("Saliendo... listo.");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("=== KD-Electronics | Inventario (CRUD) ===");
        System.out.println("1. Registrar producto (Create)");
        System.out.println("2. Consultar producto por código (Read)");
        System.out.println("3. Actualizar producto (Update - excepto código)");
        System.out.println("4. Eliminar lógico (Delete)");
        System.out.println("5. Listar todos");
        System.out.println("0. Salir");
    }

    private void createProduct() {
        System.out.println("--- Registrar producto ---");
        String code = readString("Código: ");
        String name = readString("Nombre: ");
        String desc = readString("Descripción: ");
        double basePrice = readDouble("Precio base: ");
        double salePrice = readDouble("Precio venta: ");
        Category category = readCategory();
        int qty = readInt("Cantidad disponible: ");

        Product p = new Product(code, name, desc, basePrice, salePrice, category, qty);
        boolean ok = service.register(p);

        if (ok) {
            System.out.println("Producto registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar: ya existe un producto con ese código.");
        }
    }

    private void readByCode() {
        System.out.println("--- Consultar por código ---");
        String code = readString("Código: ");

        Optional<Product> productOpt = service.getByCode(code);
        if (productOpt.isPresent()) {
            System.out.println(productOpt.get());
        } else {
            System.out.println("No existe un producto con ese código.");
        }
    }

    private void updateProduct() {
        System.out.println("--- Actualizar producto (excepto código) ---");
        String code = readString("Código del producto a actualizar: ");

        Optional<Product> existingOpt = service.getByCode(code);
        if (existingOpt.isEmpty()) {
            System.out.println("No existe un producto con ese código.");
            return;
        }

        Product existing = existingOpt.get();
        System.out.println("Actual (referencia): " + existing);

        String name = readString("Nuevo nombre: ");
        String desc = readString("Nueva descripción: ");
        double basePrice = readDouble("Nuevo precio base: ");
        double salePrice = readDouble("Nuevo precio venta: ");
        Category category = readCategory();
        int qty = readInt("Nueva cantidad disponible: ");

        // Importante: el código se conserva
        Product updated = new Product(existing.getCode(), name, desc, basePrice, salePrice, category, qty);
        // conservar estado (si estaba eliminado, seguirá eliminado)
        updated.setActive(existing.isActive());

        boolean ok = service.update(updated);
        System.out.println(ok ? "Producto actualizado correctamente." : "No se pudo actualizar.");
    }

    private void deleteProduct() {
        System.out.println("--- Eliminación lógica ---");
        String code = readString("Código: ");

        boolean ok = service.deleteLogical(code);
        if (ok) {
            System.out.println("Producto marcado como ELIMINADO (lógico).");
        } else {
            System.out.println("No se pudo eliminar: no existe o ya estaba eliminado.");
        }
    }

    private void listAll() {
        System.out.println("--- Listado de productos ---");
        List<Product> products = service.getAll();

        if (products.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Product p : products) {
            System.out.println(p);
        }
    }

    private Category readCategory() {
        System.out.println("Categorías disponibles:");
        Category[] values = Category.values();

        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }

        int opt = readInt("Seleccione categoría (1-" + values.length + "): ");
        if (opt < 1 || opt > values.length) {
            throw new IllegalArgumentException("Categoría inválida.");
        }

        return values[opt - 1];
    }

    private String readString(String msg) {
        System.out.print(msg);
        String s = sc.nextLine();
        return (s == null) ? "" : s.trim();
    }

    private int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String s = sc.nextLine();
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private double readDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String s = sc.nextLine();
                return Double.parseDouble(s.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número decimal válido.");
            }
        }
    }
}