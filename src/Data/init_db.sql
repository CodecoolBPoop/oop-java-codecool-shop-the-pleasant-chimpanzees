drop table if exists billing_info;
drop table if exists products_in_carts;
drop table if exists product;
drop table if exists product_category;
drop table if exists supplier;
drop table if exists cart;
drop table if exists "_user";

CREATE TABLE
  _user(
         id SERIAL PRIMARY KEY,
         email VARCHAR,
         password VARCHAR
);

CREATE TABLE
  billing_info(
         user_id INTEGER REFERENCES _user (id) ON DELETE CASCADE,
         name VARCHAR,
         address VARCHAR,
         city VARCHAR,
         state VARCHAR,
         zip INTEGER
);

CREATE TABLE
  product_category(
         id SERIAL PRIMARY KEY,
         name VARCHAR,
         description VARCHAR,
         department VARCHAR
);

CREATE TABLE
    supplier(
        id SERIAL PRIMARY KEY,
        name VARCHAR,
        description VARCHAR
);

CREATE TABLE
  product(
        id SERIAL PRIMARY KEY,
        category_id INTEGER REFERENCES product_category (id),
        supplier_id INTEGER REFERENCES supplier (id),
        name VARCHAR,
        description VARCHAR,
        price FLOAT,
        image_path VARCHAR

);


CREATE TABLE
  cart(
       id SERIAL PRIMARY KEY,
       user_id INTEGER REFERENCES _user (id)

);

CREATE TABLE
  products_in_carts(
        cart_id INTEGER REFERENCES cart (id),
        product_id INTEGER REFERENCES product (id)
);