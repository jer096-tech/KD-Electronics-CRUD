package kdelectronics.repository;

import java.util.List;
import java.util.Optional;
import kdelectronics.model.Product;

public interface ProductRepository {

    boolean create(Product product);

    Optional<Product> findByCode(String code); // trae activo o eliminado

    List<Product> findAll();

    boolean update(Product product); // actualiza por code (sin cambiarlo)

    boolean softDelete(String code); // eliminación lógica
}