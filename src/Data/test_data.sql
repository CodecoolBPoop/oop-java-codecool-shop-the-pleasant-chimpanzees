

INSERT INTO _user (email, password)
VALUES ('bob@gmail.com','test');

INSERT INTO _user (email, password)
VALUES ('marti@gmail.com','test');

INSERT INTO _user (email, password)
VALUES ('helen@gmail.com','test');



INSERT INTO billing_info (user_id, name, address, city, state, zip)
VALUES (1, 'Bob', 'at the mall', 'pecs', 'ott', 007);

INSERT INTO billing_info (user_id, name, address, city, state, zip)
VALUES (2, 'Marti', 'homeless', ':(', ':(', 000);

INSERT INTO billing_info (user_id, name, address, city, state, zip)
VALUES (3, 'Helena', 'there', 'Buda', 'Pest', 1007);



INSERT INTO cart (user_id)
VALUES (1);

INSERT INTO cart (user_id)
VALUES (2);

INSERT INTO cart (user_id)
VALUES (3);



INSERT INTO product_category (name, description, department)
VALUES ('Superhero', 'About Superheroes', '???');

INSERT INTO product_category (name, description, department)
VALUES ('Humor', 'About Humor', '???');

INSERT INTO product_category (name, description, department)
VALUES ('Horror', 'About Horror', '???');



INSERT INTO supplier (name, description)
VALUES ('Marvel', 'Marvel comic books');

INSERT INTO supplier (name, description)
VALUES ('DC', 'DC comic books');

INSERT INTO supplier (name, description)
VALUES ('Netflix', 'Netflix comic books');

INSERT INTO supplier (name, description)
VALUES ('Random House', 'Random House comic books');



INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (1, 2, 'The Flash: Born to Run', '???', 8.15, 'src/main/webapp/static/img/product_images/product_1.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (1, 1, 'Spider-Man: Hobgoblin Lives', '???', 8.15, 'src/main/webapp/static/img/product_images/product_2.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (1, 2, 'Green Lantern: Agent Orange', '???', 8.15, 'src/main/webapp/static/img/product_images/product_3.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (1, 1, 'Carnage', '???', 8.15, 'src/main/webapp/static/img/product_images/product_4.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (1, 1, 'Return of wolverine', '???', 8.15, 'src/main/webapp/static/img/product_images/product_5.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (2, 1, 'Alf', '???', 8.15, 'src/main/webapp/static/img/product_images/product_6.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (1, 2, 'Batman: the death of the family', '???', 8.15, 'src/main/webapp/static/img/product_images/product_7.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (1, 2, 'Batman: hush', '???', 8.15, 'src/main/webapp/static/img/product_images/product_8.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (3, 3, 'Black mirror: Haven is a place on earth', '???', 8.15, 'src/main/webapp/static/img/product_images/product_9.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (3, 3, 'Black mirror: play test', '???', 8.15, 'src/main/webapp/static/img/product_images/product_10.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (1, 2, 'Detective comics: Batman', '???', 8.15, 'src/main/webapp/static/img/product_images/product_11.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (2, 4, 'Garfield: kaboom #1', '???', 8.15, 'src/main/webapp/static/img/product_images/product_12.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (2, 4, 'Garfield: S9', '???', 8.15, 'src/main/webapp/static/img/product_images/product_13.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (2, 4, 'Garfield: S8', '???', 8.15, 'src/main/webapp/static/img/product_images/product_14.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (2, 4, 'Garfield: grumpy cat', '???', 8.15, 'src/main/webapp/static/img/product_images/product_15.jpg');

INSERT INTO product (category_id, supplier_id, name, description, price, image_path)
VALUES (1, 2, 'Preacher', '???', 8.15, 'src/main/webapp/static/img/product_images/product_16.jpg');



INSERT INTO products_in_carts (cart_id, product_id)
VALUES (1, 6);

INSERT INTO products_in_carts (cart_id, product_id)
VALUES (1, 7);

INSERT INTO products_in_carts (cart_id, product_id)
VALUES (2, 4);

INSERT INTO products_in_carts (cart_id, product_id)
VALUES (2, 2);

INSERT INTO products_in_carts (cart_id, product_id)
VALUES (3, 2);

INSERT INTO products_in_carts (cart_id, product_id)
VALUES (3, 2);