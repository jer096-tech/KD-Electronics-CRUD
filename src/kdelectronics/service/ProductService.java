package kdelectronics.service;

import java.util.List;
import java.util.Optional;
import kdelectronics.model.Product;
import kdelectronics.repository.ProductRepository;

public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public boolean register(Product product) {
        validate(product);
        return repo.create(product);
    }

    public Optional<Product> getByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        return repo.findByCode(code.trim());
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public boolean update(Product updatedProduct) {
        validate(updatedProduct);
        return repo.update(updatedProduct);
    }

    public boolean deleteLogical(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        return repo.softDelete(code.trim());
    }

    private void validate(Product p) {
        if (p.getCode() == null || p.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("El código es obligatorio.");
        }
        if (p.getName() == null || p.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (p.getDescription() == null) {
            throw new IllegalArgumentException("La descripción no puede ser null.");
        }
        if (p.getBasePrice() < 0 || p.getSalePrice() < 0) {
            throw new IllegalArgumentException("Los precios no pueden ser negativos.");
        }
        if (p.getSalePrice() < p.getBasePrice()) {
            throw new IllegalArgumentException("El precio de venta no puede ser menor que el precio base.");
        }
        if (p.getAvailableQuantity() < 0) {
            throw new IllegalArgumentException("La cantidad disponible no puede ser negativa.");
        }
        if (p.getCategory() == null) {
            throw new IllegalArgumentException("La categoría es obligatoria.");
        }
    }
}