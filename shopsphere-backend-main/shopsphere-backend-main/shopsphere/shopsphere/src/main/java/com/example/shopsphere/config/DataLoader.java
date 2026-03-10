package com.example.shopsphere.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.shopsphere.entity.Product;
import com.example.shopsphere.entity.User;
import com.example.shopsphere.repository.ProductRepository;
import com.example.shopsphere.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        loadAdmin();
        loadProducts();

    }

    private void loadAdmin(){

        if(userRepository.findByEmail("admin@shop.com").isEmpty()){

            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@shop.com");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole("ADMIN");

            userRepository.save(admin);
        }

    }

    private void loadProducts(){

        if(productRepository.count() > 0) return;

        List<Product> products = List.of(

                createProduct("Gaming Laptop",75000,"High performance gaming laptop","Electronics",
                        "https://m.media-amazon.com/images/I/61Dw5Z8LzJL._SL1500_.jpg"),

                createProduct("Wireless Headphones",2500,"Noise cancelling headphones","Electronics",
                        "https://m.media-amazon.com/images/I/61CGHv6kmWL._SL1500_.jpg"),

                createProduct("Smart Watch",4500,"Fitness tracking smartwatch","Electronics",
                        "https://m.media-amazon.com/images/I/61y2VVWcGBL._SL1500_.jpg"),

                createProduct("Running Shoes",3200,"Comfortable running shoes","Fashion",
                        "https://m.media-amazon.com/images/I/61uyqhEgl7L._AC_SX679_.jpg"),

                createProduct("Bluetooth Speaker",1800,"Portable bluetooth speaker","Electronics",
                        "https://m.media-amazon.com/images/I/41b77upnZFL._SY300_SX300_QL70_FMwebp_.jpg"),

                createProduct("Office Chair",8500,"Ergonomic office chair","Furniture",
                        "https://m.media-amazon.com/images/I/31a+Mbej2sL._SY300_SX300_QL70_FMwebp_.jpg"),

                createProduct("Mechanical Keyboard",4200,"RGB mechanical keyboard","Electronics",
                        "https://m.media-amazon.com/images/I/71kr3WAj1FL._SL1500_.jpg"),

                createProduct("Gaming Mouse",1200,"High precision gaming mouse","Electronics",
                        "https://m.media-amazon.com/images/I/61mpMH5TzkL._SL1500_.jpg"),

                createProduct("Backpack",1500,"Laptop travel backpack","Fashion",
                        "https://eumeworld.com/cdn/shop/files/Artboard_4_02ee670a-de4b-4a99-9cd3-89fc80b7e863.jpg?v=1770361999&width=493"),

                createProduct("LED Monitor",13500,"24 inch full HD monitor","Electronics",
                        "https://m.media-amazon.com/images/I/71kr3WAj1FL._SL1500_.jpg"),

                createProduct("Tablet",22000,"Android tablet device","Electronics",
                        "https://m.media-amazon.com/images/I/71Swqqe7XAL._SL1500_.jpg"),

                createProduct("Coffee Maker",3400,"Automatic coffee maker","Home",
                        "https://m.media-amazon.com/images/I/61uA2UVnYWL._SL1500_.jpg"),

                createProduct("Desk Lamp",900,"LED desk lamp","Home",
                        "https://m.media-amazon.com/images/I/410YQdaPxdL._SY445_SX342_QL70_FMwebp_.jpg"),

                createProduct("Sunglasses",700,"UV protection sunglasses","Fashion",
                        "https://m.media-amazon.com/images/I/51GYi1kNaBL._SY450_.jpg"),

                createProduct("Fitness Band",1800,"Activity tracker band","Electronics",
                        "https://m.media-amazon.com/images/I/31h5vMziyqL._SY300_SX300_QL70_FMwebp_.jpg"),

                createProduct("Smartphone",55000,"Latest android smartphone","Electronics",
                        "https://m.media-amazon.com/images/I/71xb2xkN5qL._SL1500_.jpg"),

                createProduct("Water Bottle",300,"Steel water bottle","Home",
                        "https://m.media-amazon.com/images/I/41voeClYYLL._SX425_.jpg"),

                createProduct("Notebook",120,"A5 ruled notebook","Stationery",
                        "https://m.media-amazon.com/images/I/71d7rfSl0wL._SL1500_.jpg"),

                createProduct("Office Table",9500,"Wooden office table","Furniture",
                        "https://m.media-amazon.com/images/I/81Boo5TWkcL._SX425_.jpg"),

                createProduct("Gaming Chair",15500,"Professional gaming chair","Furniture",
                        "https://m.media-amazon.com/images/I/41r+lis7tnL._SY300_SX300_QL70_FMwebp_.jpg")

        );

        productRepository.saveAll(products);

    }

    private Product createProduct(String name,double price,String description,String category,String image){

        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setDescription(description);
        p.setCategory(category);
        p.setStock(20);
        p.setImageUrl(image);

        return p;
    }

}