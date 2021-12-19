CREATE schema bookstoreCREATE schema bookstore


--tables
CREATE TABLE bookstore.book (	isbn	numeric(13,0) PRIMARY KEY,
				book_name varchar(100),
				price 	numeric(4,2),
				num_pages 	integer,
				quantity 		integer,
				publishers_cut 	numeric(4,2),
				publisher_name	varchar(50),
				author_id		integer,
				genre	varchar(50)"
				)

CREATE TABLE bookstore.user (username	varchar(20) PRIMARY KEY,
				password	varchar(20),
				first_name	varchar(20),
				last_name 	varchar(20),
				admin		boolean
				)

CREATE TABLE bookstore.shipping_information (shipping_id	serial primary key,
        			street	varchar(25),
        			city		varchar(25),
        			province	varchar(20),
        			postal_code varchar(6),
        			country	varchar(25))

CREATE TABLE bookstore.billing_information (billing_id	serial primary key,
        			card_number	numeric(16,0),
        			cvv	numeric(3,0),
        			first_name	varchar(25),
        			last_name	varchar(25),
        			street	varchar(25),
        			city		varchar(25),
        			province	varchar(20),
        			postal_code varchar(6),
        			country	varchar(25))

CREATE TABLE bookstore.publisher (name	varchar(50) PRIMARY KEY,
    				email	varchar(35) unique,
    				phone	numeric(10,0) unique,
    				banking_account 	numeric(10,0) unique,
    				shipping_id serial
    				)

CREATE TABLE bookstore.author (author_id serial primary key,
        			first_name	varchar(20),
    				last_name	varchar(20)
    				)

CREATE TABLE bookstore.cart (cart_id	serial primary key,
        			username	varchar(20))


CREATE TABLE bookstore.order (order_id	serial primary key,
        			order_date	date,
        			tracking_id	numeric(10,0) unique,
        			price	numeric(10,2),
        			shipping_message	varchar(100),
        			billing_id	integer,
        			shipping_id	integer)


--relations


CREATE TABLE bookstore.book_cart (cart_id	serial,
        			isbn	numeric(13,0),
        			cartquantity int,
        			primary key (isbn, cart_id),
        			foreign key (isbn) references bookstore.book on delete cascade,
        			foreign key (cart_id) references bookstore.cart)


CREATE TABLE bookstore.order_cart (cart_id	serial,
        			order_id	serial unique,
        			primary key (cart_id),
        			foreign key (cart_id) references bookstore.cart,
        			foreign key (order_id) references bookstore.order)

CREATE TABLE bookstore.user_billinginfo (username	varchar(20),
        			billing_id	serial unique,
        			primary key (billing_id),
        			foreign key (billing_id) references bookstore.billing_information,
        			foreign key (username) references bookstore.user on delete cascade)


CREATE TABLE bookstore.user_shippinginfo (username	varchar(20),
        			shipping_id	serial unique,
        			primary key (shipping_id),
        			foreign key (shipping_id) references bookstore.shipping_information,
        			foreign key (username) references bookstore.user on delete cascade)

CREATE TABLE bookstore.book_publisher_backorder (isbn	numeric(13,0) primary key,
        			email	varchar(35),
        			foreign key (isbn) references bookstore.book on delete cascade)

