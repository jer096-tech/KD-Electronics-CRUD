package kdelectronicscrud;

import kdelectronics.repository.InMemoryProductRepository;
import kdelectronics.service.ProductService;
import kdelectronics.ui.ConsoleMenu;

public class KDElectronicsCRUD {

    public static void main(String[] args) {
        ProductService service = new ProductService(new InMemoryProductRepository());
        ConsoleMenu menu = new ConsoleMenu(service);
        menu.start();
    }
}