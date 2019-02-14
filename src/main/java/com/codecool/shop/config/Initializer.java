package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
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

        DBUtil.ConnectionData data = new DBUtil.ConnectionData(
                "src/Data/prod_config.txt",
                "src/Data/test_config.txt");

        DBUtil.getInstance().configure(data);

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier marvel = new Supplier("Marvel", "Comic book");
        supplierDataStore.add(marvel);
        Supplier dc = new Supplier("DC", "Comic book");
        supplierDataStore.add(dc);
        Supplier netflix = new Supplier("Netflix", "Comic book");
        supplierDataStore.add(netflix);
        Supplier randomHouse = new Supplier("Random House", "Comic book");
        supplierDataStore.add(randomHouse);


        //setting up a new product category
        ProductCategory superhero = new ProductCategory("Superhero", "Comic book", "Superhero comics are one of the most common genres of American comic books. The genre rose to prominence in the 1930s and became extremely popular in the 1940s and has remained the dominant form of comic book in North America since the 1960s. Superhero comics feature stories about superheroes and the universes these characters inhabit.");
        productCategoryDataStore.add(superhero);
        ProductCategory humor = new ProductCategory("Humor", "Comic book", "???");
        productCategoryDataStore.add(humor);
        ProductCategory horror = new ProductCategory("Horror", "Comic book", "???");
        productCategoryDataStore.add(horror);


        //setting up products and printing it
        productDataStore.add(new Product("The Flash: Born to Run", 8.14f, "USD", "Born to Run is a Flash storyline written by Mark Waid with illustrations by Greg LaRocque. It is the first arc of Waid's run on the Flash series, taking over after the departure of Bill Loebs. Waid's next story arc is The Return of Barry Allen. This story is labeled as \"Year One\" and serves as a Post-Crisis rebooted origin story for Wally West.",superhero, dc));
        productDataStore.add(new Product("Spider-Man: Hobgoblin Lives", 5, "USD", "For years, the mystery of the Hobgoblin's identity plagued not only Spider-Man and his friends, but fans, as well! Now, all is revealed, and the answers will shock you! As the Hobgoblin stands exposed at last, the threats of Norman Osborn and an all-new Green Goblin wait in the wings! Featuring all your favorite Spider-Man characters, from Mary Jane to Betty Brant to Robbie Robertson!",superhero, marvel));
        productDataStore.add(new Product("Green Lantern: Agent Orange", 12.78f, "USD", "Test pilot Hal Jordan was chosen to become a Green Lantern, one of an intergalactic police force. Armed with his incredible power ring, which creates anything he can imagine, he protects Earth from extraterrestrial threats of every kind. This graphic novel sequel to \"The Sinestro Corps War\" is the prelude to the next major event in the DC Universe, \"The Blackest Night\", and features the war of light exploding across the Vega System. The Green Lantern Corps led by Hal Jordan battle the bizarre Orange Lantern Corps and their commander, Agent Orange, the most disgusting, filthy and vile being in the universe. Plus, the secrets of the Guardians' pact with the criminals of the universe that had previously kept the Vega System off limits is finally revealed.",superhero, dc));
        productDataStore.add(new Product("Carnage", 99.99f, "USD", "Carnage is a fictional supervillain appearing in American comic books published by Marvel Comics. The character first appeared in The Amazing Spider-Man #360.[2] Carnage was created by writer David Michelinie and artist Mark Bagley. The character belongs to a race of amorphous extraterrestrial parasites known as the Symbiotes. He is usually depicted as an adversary to Spider-Man, as well as serving as the archenemy of Venom. ",superhero, marvel));
        productDataStore.add(new Product("Return of wolverine", 19.99f, "USD", "Return of Wolverine is a comic book miniseries published by Marvel Comics. This miniseries will follow up on the events that transpired in the \"Death of Wolverine\" and \"Hunt for Wolverine\" storylines and explain how Wolverine returned from the dead.",superhero, marvel));
        productDataStore.add(new Product("Alf", 1.99f, "USD", "ALF is an American sitcom that aired on NBC from September 22, 1986, to March 24, 1990. The title character is Gordon Shumway, a sarcastic, friendly extraterrestrial nicknamed ALF (an acronym for Alien Life Form), who crash-lands in the garage of the suburban middle-class Tanner family.[3] The series stars Max Wright as father Willie Tanner, Anne Schedeen as mother Kate Tanner, and Andrea Elson and Benji Gregory as their children, Lynn and Brian Tanner. ALF was performed by puppeteer Paul Fusco, who co-created the show with Tom Patchett.",humor, marvel));
        productDataStore.add(new Product("Batman: the death of the family", 29.99f, "USD", "After having his face sliced off, The Joker makes his horrifying return to Gotham City; shaking Batman to his core! But even for a man who’s committed a lifetime of murder, he’s more dangerous than ever before. How can Batman protect his city and those he’s closest to?",superhero, dc));
        productDataStore.add(new Product("Batman: hush", 15.99f, "USD", "Batman: Hush is an American comic book story arc published by DC Comics, featuring the superhero Batman. It was published in monthly installments within the comic book series Batman, running from issue #608–619 in December 2002 until November 2003. The story arc was written by Jeph Loeb, penciled by Jim Lee, inked by Scott Williams, and colored by Alex Sinclair.",superhero, dc));
        productDataStore.add(new Product("Black mirror: Haven is a place on earth", 15.99f, "USD", "???",horror, netflix));
        productDataStore.add(new Product("Detective comics: Batman", 15.99f, "USD", "Detective Comics is an American comic book series published by DC Comics. The first volume, published from 1937 to 2011 (and later continued in 2016), is best known for introducing the superhero Batman in Detective Comics #27",superhero, dc));
        productDataStore.add(new Product("Garfield: kaboom #1", 15.99f, "USD", "This May, everyone's favorite lasagna-scarfing cat opens the fridge to an all-new ongoing comic book series! Join Garfield as he begins his monthly exploits with Jon, Odie, and the rest of the gang in the latest all-ages must-read from KaBOOM! The Garfield Show writer and comics luminary Mark Evanier teams up with beloved Garfield strip cartoonist Gary Barker to bring you all the laughs (and lasagna) you can handle. With brand new stories featuring everyone's favorite feline, this debut #1 issue is sure to please Garfield fans, both new and old alike!",humor, randomHouse));
        productDataStore.add(new Product("Garfield: S9", 11f, "USD", "???",humor, randomHouse));
        productDataStore.add(new Product("Garfield: S8", 15.99f, "USD", "???",humor, randomHouse));
        productDataStore.add(new Product("Garfield: grumpy cat", 15.99f, "USD", "It's the inevitable meeting of the sourpusses! Garfield, the reigning cynical cat of newspapers and TV crosses paths with Grumpy Cat, the internet sensation whose scowl endeared herself to the world. Who's the most sarcastic? Well, he likes lasagna and not much else...and she doesn't even like lasagna. Can these two inhabit the same comic book mini-series, let alone the same planet? You'll find out in a trio of issues written by Mark Evanier and illustrated by Steve Uy. We'd say it's the cat's meow but neither of these cats meows.",humor, randomHouse));
        productDataStore.add(new Product("Black mirror: play test", 15.99f, "USD", "???",horror, netflix));
        productDataStore.add(new Product("Preacher", 15.99f, "USD", "At first glance, the Reverend Jesse Custer doesn't look like anyone special--just another small-town minister losing his faith. But when he comes face-to-face with proof that God exists, he begins a violent and riotous journey across the country in search of answers from the elusive deity.",superhero, dc));
    }
}
