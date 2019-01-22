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

