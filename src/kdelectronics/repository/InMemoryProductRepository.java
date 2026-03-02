package kdelectronics.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import kdelectronics.model.Product;

public class InMemoryProductRepository implements ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @Override
    public boolean create(Product product) {
        if (findByCode(product.getCode()).isPresent()) {
            return false; // ya existe ese código
        }
        products.add(product);
        return true;
    }

    @Override
    public Optional<Product> findByCode(String code) {
        for (Product p : products) {
            if (p.getCode().equalsIgnoreCase(code)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public boolean update(Product product) {
        Optional<Product> existingOpt = findByCode(product.getCode());
        if (existingOpt.isEmpty()) return false;

        Product existing = existingOpt.get();

        // Código NO se modifica
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setBasePrice(product.getBasePrice());
        existing.setSalePrice(product.getSalePrice());
        existing.setCategory(product.getCategory());
        existing.setAvailableQuantity(product.getAvailableQuantity());

        // active se conserva (no se toca aquí)
        return true;
    }

    @Override
    public boolean softDelete(String code) {
        Optional<Product> existingOpt = findByCode(code);
        if (existingOpt.isEmpty()) return false;

        Product existing = existingOpt.get();
        if (!existing.isActive()) return false; // ya estaba eliminado

        existing.setActive(false); // eliminación lógica
        return true;
    }
}