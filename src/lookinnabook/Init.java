package lookinnabook;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

//Run this on first use. Then run main. Future uses only need to run main.
public class Init {
	private static Connection conn;
    private static Statement stmt;	
    
    public static void createSchema() {
    			
    	try {
			stmt.executeUpdate("CREATE schema bookstore");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		    }
		
    }

    public static void populateAdmin() {
    	try {
			stmt.executeUpdate("insert into bookstore.user values('admin',"
					+"'admin',"
					+ "'admin',"
					+ "'admin',"
					+ "true"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} 
		try {
			stmt.executeUpdate("insert into bookstore.cart values(default,'admin')");
			 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
    
    public static void populateShippingAddressForPublishers() {
  	
    	try {
			stmt.executeUpdate("insert into bookstore.shipping_information values(default,"
					+"'604 King Street',"
					+ "'Toronto',"
					+ "'Ontario',"
					+ "'M5V1E1',"
					+ "'Canada'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}   	
    	try {
			stmt.executeUpdate("insert into bookstore.shipping_information values(default,"
					+"'22 Adelaide Street',"
					+ "'Toronto',"
					+ "'Ontario',"
					+ "'M5H4E3',"
					+ "'Canada'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} 
    	try {
			stmt.executeUpdate("insert into bookstore.shipping_information values(default,"
					+"'401 Richmond Street',"
					+ "'Toronto',"
					+ "'Ontario',"
					+ "'M5V3A8',"
					+ "'Canada'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} 
    	try {
			stmt.executeUpdate("insert into bookstore.shipping_information values(default,"
					+"'1382 Queen Street',"
					+ "'Toronto',"
					+ "'Ontario',"
					+ "'M4L1C9',"
					+ "'Canada'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} 
    	try {
			stmt.executeUpdate("insert into bookstore.shipping_information values(default,"
					+"'320 Front Street',"
					+ "'Toronto',"
					+ "'Ontario',"
					+ "'M5V3B6',"
					+ "'Canada'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} 
    }
    
    
    public static void populatePublishers() {
    	try {
			stmt.executeUpdate("insert into bookstore.publisher values('Scholastic',"
					+"'services@scholastic.com',"
					+"6135551234,"
					+ "1234567890,"
					+ "default"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}   	
    	try {
			stmt.executeUpdate("insert into bookstore.publisher values('HarperCollins',"
					+"'services@harpercollins.com',"
					+"6135551235,"
					+ "1234567891,"
					+ "default"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.publisher values('Macmillan',"
					+"'services@macmillan.com',"
					+"6135551236,"
					+ "1234567892,"
					+ "default"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.publisher values('Sterling',"
					+"'services@sterling.com',"
					+"6135551237,"
					+ "1234567893,"
					+ "default"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.publisher values('Candlewick',"
					+"'services@candlewick.com',"
					+"6135551238,"
					+ "1234567894,"
					+ "default"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    }
    
    
    public static void populateAuthors() {
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'J.K.',"
					+"'Rowling'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Bill',"
					+"'Bryson'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Edward P.',"
					+"'Jones'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Henry',"
					+"'Miller'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Marguerite',"
					+"'Duras'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Robert Louis',"
					+"'Stevenson'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Thomas',"
					+"'Pynchon'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Joan',"
					+"'Didion'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Nikola',"
					+"'Tesla'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Anne',"
					+"'Tyler'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'William',"
					+"'Golding'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Yann',"
					+"'Martel'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Rudolph W.',"
					+"'Giuliani'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Steven',"
					+"'Pressfield'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Charles',"
					+"'Dickens'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Herman',"
					+"'Melville'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Steve',"
					+"'Martini'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Harper',"
					+"'Lee'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'George',"
					+"'Orwell'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
			stmt.executeUpdate("insert into bookstore.author values(default,"
					+"'Ray',"
					+"'Bradbury'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    }
    
    
    public static void populateBooks() {
    	try {
			stmt.executeUpdate("insert into bookstore.book values(9780439785969,"
					+"'Harry Potter and the Half-Blood Prince',"
					+"12.00,"
					+"652,"
					+"20,"
					+"10.00,"
					+ "'Scholastic',"
					+ "1,"
					+ "'Fantasy-Fiction'"
					+")");
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
		stmt.executeUpdate("insert into bookstore.book values(9780767908184,"
				+"'A Short History of Nearly Everything',"
				+"11.00,"
				+"544,"
				+"18,"
				+"8.00,"
				+ "'Scholastic',"
				+ "2,"
				+ "'Non-Fiction'"
				+")");
    	} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780767903868,"
    				+"'In a Sunburned Country',"
    				+"13.00,"
    				+"335,"
    				+"22,"
    				+"9.00,"
    				+ "'Scholastic',"
    				+ "2,"
    				+ "'Travel'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780380727506,"
    				+"'Notes from a Small Island',"
    				+"10.00,"
    				+"324,"
    				+"21,"
    				+"8.00,"
    				+ "'Scholastic',"
    				+ "2,"
    				+ "'Travel'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780060749910,"
    				+"'The Known World',"
    				+"11.00,"
    				+"576,"
    				+"23,"
    				+"7.00,"
    				+ "'Scholastic',"
    				+ "3,"
    				+ "'History'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780802151827,"
    				+"'Tropic of Capricorn',"
    				+"13.00,"
    				+"348,"
    				+"23,"
    				+"10.00,"
    				+ "'HarperCollins',"
    				+ "4,"
    				+ "'Autobiography'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9781565842212,"
    				+"'The War',"
    				+"9.00,"
    				+"192,"
    				+"22,"
    				+"9.00,"
    				+ "'HarperCollins',"
    				+ "5,"
    				+ "'Autobiography'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9781416500292,"
    				+"'Treasure Island',"
    				+"19.00,"
    				+"245,"
    				+"25,"
    				+"9.00,"
    				+ "'HarperCollins',"
    				+ "6,"
    				+ "'Adventure-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780141180632,"
    				+"'Vineland',"
    				+"12.00,"
    				+"385,"
    				+"24,"
    				+"8.00,"
    				+ "'HarperCollins',"
    				+ "7,"
    				+ "'Postmodern-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780679754855,"
    				+"'Democracy',"
    				+"14.00,"
    				+"234,"
    				+"23,"
    				+"9.00,"
    				+ "'HarperCollins',"
    				+ "8,"
    				+ "'Historical-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9781599869940,"
    				+"'My Inventions',"
    				+"9.00,"
    				+"88,"
    				+"21,"
    				+"10.00,"
    				+ "'Macmillan',"
    				+ "9,"
    				+ "'Autobiography'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780399529207,"
    				+"'Lord of the Flies',"
    				+"14.00,"
    				+"224,"
    				+"25,"
    				+"9.00,"
    				+ "'Macmillan',"
    				+ "10,"
    				+ "'Psychological-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780307263940,"
    				+"'Digging to America',"
    				+"13.00,"
    				+"277,"
    				+"23,"
    				+"9.00,"
    				+ "'Macmillan',"
    				+ "11,"
    				+ "'YoungAdult-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780156030205,"
    				+"'Life of Pi',"
    				+"16.00,"
    				+"401,"
    				+"26,"
    				+"10.00,"
    				+ "'Macmillan',"
    				+ "12,"
    				+ "'Adventure-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}    	
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780316861014,"
    				+"'Leadership',"
    				+"14.00,"
    				+"397,"
    				+"22,"
    				+"9.00,"
    				+ "'Macmillan',"
    				+ "13,"
    				+ "'Autobiography'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780553383683,"
    				+"'Gates of Fire',"
    				+"12.00,"
    	 			+"392,"
    				+"21,"
    				+"8.00,"
    				+ "'Sterling',"
    				+ "14,"
    				+ "'Historical-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9781593080556,"
    				+"'A Tale of Two Cities',"
    				+"16.00,"
    	 			+"429,"
    				+"24,"
    				+"7.00,"
    				+ "'Sterling',"
    				+ "15,"
    				+ "'Historical-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9781503280786,"
    				+"'Moby Dick',"
    				+"15.00,"
    	 			+"378,"
    				+"22,"
    				+"6.00,"
    				+ "'Sterling',"
    				+ "16,"
    				+ "'Adventure-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780515121490,"
    				+"'The List',"
    				+"17.00,"
    	 			+"451,"
    				+"26,"
    				+"6.00,"
    				+ "'Sterling',"
    				+ "17,"
    				+ "'Horror-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780060935467,"
    				+"'To Kill a Mockingbird',"
    				+"17.00,"
    	 			+"323,"
    				+"27,"
    				+"7.00,"
    				+ "'Sterling',"
    				+ "18,"
    				+ "'YoungAdult-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9798700301879,"
    				+"'Animal Farm',"
    				+"13.00,"
    	 			+"100,"
    				+"32,"
    				+"8.00,"
    				+ "'Candlewick',"
    				+ "19,"
    				+ "'Satire'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780141036144,"
    				+"'1984',"
    				+"14.00,"
    	 			+"336,"
    				+"33,"
    				+"9.00,"
    				+ "'Candlewick',"
    				+ "19,"
    				+ "'Dystopian-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780062409850,"
    				+"'Go Set a Watchman',"
    				+"15.00,"
    	 			+"288,"
    				+"35,"
    				+"11.00,"
    				+ "'Candlewick',"
    				+ "18,"
    				+ "'YoungAdult-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9781451673319,"
    				+"'Fahrenheit 451',"
    				+"13.00,"
    	 			+"256,"
    				+"30,"
    				+"8.00,"
    				+ "'Candlewick',"
    				+ "20,"
    				+ "'Dystopian-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780553277531,"
    				+"'Dandelion Wine',"
    				+"14.00,"
    	 			+"256,"
    				+"35,"
    				+"9.00,"
    				+ "'Candlewick',"
    				+ "20,"
    				+ "'Fantasy-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780439358071,"
    				+"'Harry Potter and the Order of the Phoenix',"
    				+"18.00,"
    	 			+"870,"
    				+"34,"
    				+"8.00,"
    				+ "'Scholastic',"
    				+ "1,"
    				+ "'Fantasy-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780439554893,"
    				+"'Harry Potter and the Chamber of Secrets',"
    				+"18.00,"
    	 			+"352,"
    				+"34,"
    				+"8.00,"
    				+ "'Scholastic',"
    				+ "1,"
    				+ "'Fantasy-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
    		stmt.executeUpdate("insert into bookstore.book values(9780439655484,"
    				+"'Harry Potter and the Prisoner of Azkaban',"
    				+"18.00,"
    	 			+"435,"
    				+"34,"
    				+"8.00,"
    				+ "'Scholastic',"
    				+ "1,"
    				+ "'Fantasy-Fiction'"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    }
    
     
    public static void createTables() {  	
    	try {
			stmt.executeUpdate("CREATE TABLE bookstore.book (isbn	numeric(13,0) PRIMARY KEY,"
					+"book_name varchar(100),"
					+"price 	numeric(4,2),"
					+"num_pages 	integer,"
					+"quantity 		integer,"
					+"publishers_cut 	numeric(4,2),"
					+ "publisher_name	varchar(50),"
					+ "author_id		integer,"
					+ "genre	varchar(50)"
					+")");
				
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	
    	try {
    	stmt.executeUpdate("CREATE TABLE bookstore.user (username	varchar(20) PRIMARY KEY,"
				+"password	varchar(20),"
				+"first_name	varchar(20),"
				+"last_name 	varchar(20),"
				+ "admin		boolean"
				+")");
    	} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
    	
    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.shipping_information (shipping_id	serial primary key,"
        			+ "street	varchar(25),"
        			+ "city		varchar(25),"
        			+ "province	varchar(20),"
        			+ "postal_code varchar(6),"
        			+ "country	varchar(25))");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.billing_information (billing_id	serial primary key,"
        			+ "card_number	numeric(16,0),"
        			+ "cvv	numeric(3,0),"
        			+ "first_name	varchar(25),"
        			+ "last_name	varchar(25),"
        			+ "street	varchar(25),"
        			+ "city		varchar(25),"
        			+ "province	varchar(20),"
        			+ "postal_code varchar(6),"
        			+ "country	varchar(25))");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.publisher (name	varchar(50) PRIMARY KEY,"
    				+"email	varchar(35) unique,"
    				+"phone	numeric(10,0) unique,"
    				+"banking_account 	numeric(10,0) unique,"
    				+ "shipping_id serial"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.author (author_id serial primary key,"
        			+ "first_name	varchar(20),"
    				+"last_name	varchar(20)"
    				+")");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.cart (cart_id	serial primary key,"
        			+ "username	varchar(20))");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.order (order_id	serial primary key,"
        			+ "order_date	date,"
        			+ "tracking_id	numeric(10,0) unique,"
        			+ "price	numeric(10,2),"
        			+ "shipping_message	varchar(100),"
        			+ "billing_id	integer,"
        			+ "shipping_id	integer)");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    }
    
    public static void createRelations() {
    	
    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.book_cart (cart_id	serial,"
        			+ "isbn	numeric(13,0),"
        			+ "cartquantity int,"
        			+ "primary key (isbn, cart_id),"
        			+ "foreign key (isbn) references bookstore.book on delete cascade,"
        			+ "foreign key (cart_id) references bookstore.cart)");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.order_cart (cart_id	serial,"
        			+ "order_id	serial unique,"
        			+ "primary key (cart_id),"
        			+ "foreign key (cart_id) references bookstore.cart,"
        			+ "foreign key (order_id) references bookstore.order)");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.user_billinginfo (username	varchar(20),"
        			+ "billing_id	serial unique,"
        			+ "primary key (billing_id),"
        			+ "foreign key (billing_id) references bookstore.billing_information,"
        			+ "foreign key (username) references bookstore.user on delete cascade)");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.user_shippinginfo (username	varchar(20),"
        			+ "shipping_id	serial unique,"
        			+ "primary key (shipping_id),"
        			+ "foreign key (shipping_id) references bookstore.shipping_information,"
        			+ "foreign key (username) references bookstore.user on delete cascade)");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}

    	try {
        	stmt.executeUpdate("CREATE TABLE bookstore.book_publisher_backorder (isbn	numeric(13,0) primary key,"
        			+ "email	varchar(35),"
        			+ "foreign key (isbn) references bookstore.book on delete cascade)");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    }
    
    public static void createTriggers() {
    	try {
        	stmt.executeUpdate("CREATE trigger remove_book_from_cart after update on bookstore.book_cart for each row when (new.cartquantity=0) execute procedure remove_book();"
        			);
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
       	try {
        	stmt.executeUpdate("CREATE trigger put_book_on_backorder after update on bookstore.book for each row when (new.quantity<10) execute procedure order_books();"
        			);
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    }
    
    
    public static void createProcedures() {
    	
    	try {
        	stmt.executeUpdate("create or replace function remove_book() returns trigger language plpgsql as $$ begin delete from bookstore.book_cart where cartquantity=0; return null; end; $$;");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	try {
        	stmt.executeUpdate("create or replace function order_books() "
        			+ "returns trigger language plpgsql "
        			+ "as $$ begin "
        			+ "insert into bookstore.book_publisher_backorder values(new.isbn, (select bookstore.publisher.email from bookstore.publisher join bookstore.book on bookstore.publisher.name = bookstore.book.publisher_name where isbn = new.isbn)); return null; end; $$;");
        	} catch (SQLException e) {
    			System.out.println(e.getLocalizedMessage());
    		}
    	
    }
    
    
    
	public static void main(String[] args) {
		try {	
			
			Scanner input = new Scanner(System.in);
			System.out.println("Enter Database name");
			String dataBaseName=input.nextLine();
			System.out.println("Enter Database Username");
			String userName=input.nextLine();
			System.out.println("Enter Database Password");
			String password=input.nextLine();
			
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dataBaseName, userName, password);
			System.out.println("Connected");
    		stmt = conn.createStatement();
			createSchema();
			createTables();
    		createRelations();
    		createProcedures();
    		createTriggers();
    		populateBooks();
    		populateAuthors();
    		populatePublishers();
    		populateShippingAddressForPublishers();
    		populateAdmin();
    	
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
