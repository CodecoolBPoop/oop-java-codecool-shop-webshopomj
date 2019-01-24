ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS pk_products_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.suppliers DROP CONSTRAINT IF EXISTS pk_suppliers_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_categories DROP CONSTRAINT IF EXISTS pk_product_categories_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.shopping_cart DROP CONSTRAINT IF EXISTS pk_shopping_cart_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.shopping_cart DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;


DROP TABLE IF EXISTS public.products;
DROP SEQUENCE IF EXISTS public.products_id_seq;
CREATE TABLE products (
    id serial NOT NULL,
    name varchar(50),
    default_price float,
    currency varchar(5),
    description text,
    product_category_id integer,
    supplier_id integer
);

DROP TABLE IF EXISTS public.suppliers;
DROP SEQUENCE IF EXISTS public.suppliers_id_seq;
CREATE TABLE suppliers (
    id serial NOT NULL,
    name varchar(50),
    description text
);

DROP TABLE IF EXISTS public.product_categories;
DROP SEQUENCE IF EXISTS public.product_categories_id_seq;
CREATE TABLE product_categories (
    id serial NOT NULL,
    name varchar(50),
    department varchar(50),
    description text
);

DROP TABLE IF EXISTS public.shopping_cart;
DROP SEQUENCE IF EXISTS public.shopping_cart_id_seq;
CREATE TABLE shopping_cart (
    id serial NOT NULL,
    product_id integer,
    amount integer,
    price float
);


ALTER TABLE ONLY products
    ADD CONSTRAINT pk_products_id PRIMARY KEY (id);

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT pk_suppliers_id PRIMARY KEY (id);

ALTER TABLE ONLY product_categories
    ADD CONSTRAINT pk_product_categories_id PRIMARY KEY (id);

ALTER TABLE ONLY shopping_cart
    ADD CONSTRAINT pk_shopping_cart_id PRIMARY KEY (id);


ALTER TABLE ONLY products
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category_id) REFERENCES product_categories(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES suppliers(id);

ALTER TABLE ONLY shopping_cart
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products(id);


INSERT INTO suppliers (name, description)
    VALUES ('Amazon', 'Digital content and services');
INSERT INTO suppliers (name, description)
    VALUES ('Lenovo', 'Computers');
INSERT INTO suppliers (name, description)
    VALUES ('Samsung', 'Phones');

INSERT INTO product_categories (name, department, description)
    VALUES ('Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO product_categories (name, department, description)
    VALUES ('Phone', 'Hardware', '...');

INSERT INTO products (name, default_price, currency, description, product_category_id, supplier_id)
    VALUES ('Amazon Fire', 49.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 1);
INSERT INTO products (name, default_price, currency, description, product_category_id, supplier_id)
    VALUES ('Lenovo IdeaPad Miix 700', 479, 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 1, 2);
INSERT INTO products (name, default_price, currency, description, product_category_id, supplier_id)
    VALUES ('Amazon Fire HD 8', 89, 'USD', 'Amazons latest Fire HD 8 tablet is a great value for media consumption.', 1, 1);
INSERT INTO products (name, default_price, currency, description, product_category_id, supplier_id)
    VALUES ('Samsung Galaxy S8', 499, 'USD', 'A Samsung phone... There were at least seven before this.', 2, 3);
INSERT INTO products (name, default_price, currency, description, product_category_id, supplier_id)
    VALUES ('Samsung Galaxy Tab S4', 179, 'USD', 'Full-size USB ports. Fantastic price. Great value.', 1, 3);