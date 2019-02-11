drop table billing_info;
drop table products_in_carts;
drop table product;
drop table product_category;
drop table supplier;
drop table cart;
drop table "_user";

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
        product_category_id INTEGER REFERENCES product_category (id),
        price FLOAT,
        name VARCHAR,
        description VARCHAR,
        supplier_id INTEGER REFERENCES supplier (id)

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