package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Comic book");
        supplierDataStore.add(lenovo);
        Supplier marvel = new Supplier("Marvel", "Comic book");
        supplierDataStore.add(lenovo);
        Supplier dc = new Supplier("DC", "Comic book");
        supplierDataStore.add(lenovo);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory superhero = new ProductCategory("Superhero", "Comic book", "Superhero comics are one of the most common genres of American comic books. The genre rose to prominence in the 1930s and became extremely popular in the 1940s and has remained the dominant form of comic book in North America since the 1960s. Superhero comics feature stories about superheroes and the universes these characters inhabit.");
        productCategoryDataStore.add(tablet);


        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        productDataStore.add(new Product("The Flash: Born to Run", 8.14f, "USD", "Born to Run is a Flash storyline written by Mark Waid with illustrations by Greg LaRocque. It is the first arc of Waid's run on the Flash series, taking over after the departure of Bill Loebs. Waid's next story arc is The Return of Barry Allen. This story is labeled as \"Year One\" and serves as a Post-Crisis rebooted origin story for Wally West.",superhero, dc));
        productDataStore.add(new Product("Spider-Man: Hobgoblin Lives", 5, "USD", "For years, the mystery of the Hobgoblin's identity plagued not only Spider-Man and his friends, but fans, as well! Now, all is revealed, and the answers will shock you! As the Hobgoblin stands exposed at last, the threats of Norman Osborn and an all-new Green Goblin wait in the wings! Featuring all your favorite Spider-Man characters, from Mary Jane to Betty Brant to Robbie Robertson!",superhero, marvel));
        productDataStore.add(new Product("Green Lantern: Agent Orange", 12.78f, "USD", "Test pilot Hal Jordan was chosen to become a Green Lantern, one of an intergalactic police force. Armed with his incredible power ring, which creates anything he can imagine, he protects Earth from extraterrestrial threats of every kind. This graphic novel sequel to \"The Sinestro Corps War\" is the prelude to the next major event in the DC Universe, \"The Blackest Night\", and features the war of light exploding across the Vega System. The Green Lantern Corps led by Hal Jordan battle the bizarre Orange Lantern Corps and their commander, Agent Orange, the most disgusting, filthy and vile being in the universe. Plus, the secrets of the Guardians' pact with the criminals of the universe that had previously kept the Vega System off limits is finally revealed.",superhero, dc));
    }
}
