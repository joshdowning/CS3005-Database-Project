--remove books from cart where quantity is 0
create or replace function remove_book() returns trigger language plpgsql as
$$ begin delete from bookstore.book_cart where cartquantity=0; return null; end; $$;

--put the email and isbn into the list that needs to be ordered
create or replace function order_books() 
        			returns trigger language plpgsql 
        			as $$ begin "
        			insert into bookstore.book_publisher_backorder 
				values(new.isbn, (select bookstore.publisher.email 
				from bookstore.publisher join bookstore.book
				on bookstore.publisher.name = bookstore.book.publisher_name
				where isbn = new.isbn)); return null; end; $$;