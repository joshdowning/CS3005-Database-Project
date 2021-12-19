--remove a book from the cart when its cart quantity is 0
CREATE trigger remove_book_from_cart after update on bookstore.book_cart for each row when (new.cartquantity=0) execute procedure remove_book();

--send email when book quantity drops below 10
CREATE trigger put_book_on_backorder after update on bookstore.book for each row when (new.quantity<10) execute procedure order_books();