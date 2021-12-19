--User insert sample
insert into bookstore.user values('admin','admin','admin','admin',true)

--Cart insert sample
insert into bookstore.cart values(default,'admin')

--Shipping information insert sample
insert into bookstore.shipping_information values(default,'604 King Street','Toronto','Ontario','M5V1E1','Canada')


--Publisher insert sample
insert into bookstore.publisher values('HarperCollins','services@harpercollins.com',6135551235,1234567891,default)

--Author insert sample
insert into bookstore.author values(default,'J.K.','Rowling')

--Book insert sample
insert into bookstore.book values(9780439785969,'Harry Potter and the Half-Blood Prince',12.00,652,20,10.00,'Scholastic',1,'Fantasy-Fiction')


--delete book
delete from bookstore.book where isbn = '" + isbn + "'"

--update book cart quantity
update bookstore.book_cart set cartquantity = " + newQuantity + " where isbn = '" + isbn + "' and cart_id = '" + cart_id + "'"

--update book stock
update bookstore.book set quantity = " + newQuantity + " where isbn = '" + isbn + "'"

--get book stock
select quantity from bookstore.book where isbn = '" + isbn + "'

--get backorder email sent info
select * from bookstore.book_publisher_backorder

--get cart book quantity
select cartquantity from bookstore.book_cart where isbn = '" + isbn + "'"

--get amount of a book in a cart
select count(*) as total from bookstore.book_cart where isbn = '" + isbn + "' and cart_id = '"+cartId + "'"

--increaase book stock by 1
update bookstore.book set quantity = quantity +1 where isbn = '" + isbn + "'"

--decrease book stock by 1
update bookstore.book set quantity = quantity -1 where isbn = '" + isbn + "'"

--increase cart book quantity by 1
update bookstore.book_cart set cartquantity = cartquantity +1 where isbn = '" + isbn + "' and cart_id = '" + cart_id + "'"

--decrease cart book quantity by 1
update bookstore.book_cart set cartquantity = cartquantity -1 where isbn = '" + isbn + "' and cart_id = '" + cart_id + "'"

--update the order shipping message
update bookstore.order set shipping_message = '" + shippingMessage + "' where order_id = '" + orderId+ "'"

--get users password
select password from bookstore.user where username = '" + username + "'"

--get admin status
select admin from bookstore.user where username = '" + username + "'"

--get user details
select * from bookstore.user where username = '" + username + "'"

--get user shipping details
select * from bookstore.shipping_information join bookstore.user_shippinginfo on bookstore.shipping_information.shipping_id=bookstore.user_shippinginfo.shipping_id where username = '" + username + "'"

--get users shipping id
select max(bookstore.user_shippinginfo.shipping_id) as shipid from bookstore.shipping_information join bookstore.user_shippinginfo on bookstore.shipping_information.shipping_id=bookstore.user_shippinginfo.shipping_id where username = '" + username + "'"

--get users billing id
select max(bookstore.user_billinginfo.billing_id) as billid from bookstore.billing_information join bookstore.user_billinginfo on bookstore.billing_information.billing_id=bookstore.user_billinginfo.billing_id where username = '" + username + "'"

--get cart contents
select isbn,cartquantity from bookstore.book_cart WHERE cart_id = '" + cartId + "'"

--get empty cart
SELECT * FROM bookstore.book_cart WHERE isbn IS NOT NULL and cart_id = '"+ cart_id + "'"

--get book info from name
select * from bookstore.book WHERE book_name = '" + bookName + "'"

--get publisher info
select * from bookstore.publisher

--get book info from isbn
select * from bookstore.book WHERE isbn = '" + isbn + "'"

--get books from author name
select * from bookstore.author WHERE first_name = '" + authorfirst + "'" + "and last_name = '" + authorLast + "'"

--get existing genres
select distinct genre from bookstore.book

--get books from genres
select * from bookstore.book WHERE genre = '" + genre + "'"

--get stock from isbn
select quantity from bookstore.book where isbn = '" + isbn + "'"

--put a new book into cart
insert into bookstore.book_cart values( '" + cart_id + "' , '" + isbn + "',1)"

--get users cart id
select cart_id from bookstore.cart where username = '" + username + "'"

--get author details
select * from bookstore.author

--get author firstname from id
select first_name from bookstore.author where author_id = '"+id + "'"

--get author lastname from id
select last_name from bookstore.author where author_id = '"+id + "'"

--get user shipping info from username
select * from bookstore.user_shippinginfo where username= '"+username+"'"

--get user billing info from username
select * from bookstore.user_billinginfo where username= '"+username+"'"

--link user to shipping address
insert into bookstore.user_shippinginfo values('" + username + "', currval('bookstore.shipping_information_shipping_id_seq'))"

--link user to billing address
insert into bookstore.user_billinginfo values('" + username + "', currval('bookstore.billing_information_billing_id_seq'))"

--get price from isbn
select price from bookstore.book WHERE isbn = '" + isbn + "'"

--create an order
insert into bookstore.order values(default,'" + sqlDate + "','"+trackingNum+ "','"+orderTotal+"','"+shippingMessage + "','" + billingid + "','" + shippingid + "')"

--get order id
select order_id from bookstore.order where order_id = (select max(order_id) from bookstore.order)"

--get order details
select * from bookstore.order where order_id = '" + orderNum + "'"

--link cart to order
insert into bookstore.order_cart values('" + cart_id + "',currval('bookstore.order_order_id_seq'))"

--get author report
select author_id, sum(cartquantity) as cartquantitysum, sum(bookstore.book.price * cartquantity) as pricesum from bookstore.order "
					+ "natural join bookstore.order_cart natural join bookstore.book_cart join bookstore.book on bookstore.book.isbn = bookstore.book_cart.isbn "
					+ "where date_part('"+time+"',now()-order_date::timestamp) = 0 

--get genre report
select genre, sum(cartquantity) as cartquantitysum, sum(bookstore.book.price * cartquantity) as pricesum from bookstore.order "
					+ "natural join bookstore.order_cart natural join bookstore.book_cart join bookstore.book on bookstore.book.isbn = bookstore.book_cart.isbn "
					+ "where date_part('"+time+"',now()-order_date::timestamp) = 0 

--get sales report
select sum(bookstore.book.price * cartquantity) as totalrevenue, round(sum(bookstore.book.price * bookstore.book.publishers_cut / 100),2) as totalpublishercut from bookstore.order natural join bookstore.order_cart natural join bookstore.book_cart join bookstore.book on bookstore.book.isbn = bookstore.book_cart.isbn "
					+ "where date_part('"+time+"',now()-order_date::timestamp) = 0 