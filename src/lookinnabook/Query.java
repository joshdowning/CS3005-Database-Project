package lookinnabook;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Query {
	
	
	public static void deleteBook(String isbn) {
		 try {
	            Main.stmt.executeUpdate("delete from bookstore.book where isbn = '" + isbn + "'");
	        } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	        }
	}
	
	public static void changeCartQuantities(String isbn, String cart_id, String newQuantity) {
		 try {
			 Main.stmt.executeUpdate("update bookstore.book_cart set cartquantity = " + newQuantity + " where isbn = '" + isbn + "' and cart_id = '" + cart_id + "'");
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static void updateBookstoreStock(String isbn, int newQuantity) {
		 try {
				Main.stmt = Main.conn.createStatement();
			 Main.stmt.executeUpdate("update bookstore.book set quantity = " + newQuantity + " where isbn = '" + isbn + "'");
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static int getBookQuantity(String isbn) {
		try {
			Main.stmt = Main.conn.createStatement();
			 ResultSet result = Main.stmt.executeQuery("select quantity from bookstore.book where isbn = '" + isbn + "'");
			 result.next();
			 return result.getInt("quantity");
					 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
		return 0;
	}
	
	public static HashMap<String, String> getSentEmailInfo(){
		try {
			 HashMap<String,String>emailMap = new HashMap<>();
			 ResultSet result = Main.stmt.executeQuery("select * from bookstore.book_publisher_backorder");
			 while(result.next()) {
				 emailMap.put(result.getString("isbn"), result.getString("email"));
			 }
			 return emailMap;
					 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
		return null;
	}
	
	
	public static boolean cartGreaterThanStock(String cart_id, String isbn) {
		int bookStock=-1;
		int cartStock=-1;
		try {
            ResultSet result = Main.stmt.executeQuery("select quantity from bookstore.book where isbn = '" + isbn + "'");
            result.next();            
            bookStock = result.getInt("quantity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		try {
            ResultSet result = Main.stmt.executeQuery("select cartquantity from bookstore.book_cart where isbn = '" + isbn + "'");
            result.next();            
            cartStock = result.getInt("cartquantity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		if(cartStock>bookStock) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isBookInStock(String isbn) {
		try {
            ResultSet result = Main.stmt.executeQuery("select quantity from bookstore.book where isbn = '" + isbn + "'");
            result.next();            
            return result.getInt("quantity")==0?false:true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static boolean isBookInCart(String isbn, String cartId) {
		try {
            ResultSet result = Main.stmt.executeQuery("select count(*) as total from bookstore.book_cart where isbn = '" + isbn + "' and cart_id = '"+cartId + "'" );
            result.next();            
            return result.getInt("total")==0?false:true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
		
	}
	
	public static void increaseBookQuantityByOne(String isbn) {
		try {
			Main.stmt.executeUpdate("update bookstore.book set quantity = quantity +1 where isbn = '" + isbn + "'");
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static void decreaseBookQuantityByOne(String isbn) {
		try {
			Main.stmt.executeUpdate("update bookstore.book set quantity = quantity -1 where isbn = '" + isbn + "'");
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static void increaseCartBookQuantityByOne(String isbn, String cart_id) {
		try {
			Main.stmt.executeUpdate("update bookstore.book_cart set cartquantity = cartquantity +1 where isbn = '" + isbn + "' and cart_id = '" + cart_id + "'");
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static void decreaseCartBookQuantityByOne(String isbn, String cart_id) {
		try {
			Main.stmt.executeUpdate("update bookstore.book_cart set cartquantity = cartquantity -1 where isbn = '" + isbn + "' and cart_id = '" + cart_id + "'");
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static void adminUpdateOrderShipping(String shippingMessage, String orderId){
		 try {
			 Main.stmt.executeUpdate("update bookstore.order set shipping_message = '" + shippingMessage + "' where order_id = '" + orderId+ "'");
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static String getPassword(String username) {
		try {
            ResultSet result = Main.stmt.executeQuery("select password from bookstore.user where username = '" + username + "'");
            while (result.next()) {
                return result.getString("password");    
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public static boolean checkIfAdmin(String username) {
		try {
            ResultSet result = Main.stmt.executeQuery("select admin from bookstore.user where username = '" + username + "'");
            while (result.next()) {
                return result.getBoolean("admin");    
            }  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
		return false;
	}
	
	public static ArrayList<String> getUserDetails(String username){
		 try {
	            ArrayList<String> userDetails = new ArrayList<>();
	            ResultSet result = Main.stmt.executeQuery("select * from bookstore.user where username = '" + username + "'");
	            while (result.next()) {
	            	userDetails.add(result.getString("first_name"));
	            	userDetails.add(result.getString("last_name"));
	            }
	            return userDetails;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	}
	
	public static ArrayList<String> getShippingAddressFromUsername(String username){
		try {
            ArrayList<String> userShippingAddress = new ArrayList<>();
            ResultSet result = Main.stmt.executeQuery("select * from bookstore.shipping_information join bookstore.user_shippinginfo on bookstore.shipping_information.shipping_id=bookstore.user_shippinginfo.shipping_id where username = '" + username + "'");
            while (result.next()) {
            	userShippingAddress.add(result.getString("shipping_id"));
            	userShippingAddress.add(result.getString("street"));
            	userShippingAddress.add(result.getString("city"));
            	userShippingAddress.add(result.getString("province"));
            	userShippingAddress.add(result.getString("postal_code"));
            	userShippingAddress.add(result.getString("country"));
            }
            return userShippingAddress;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static String getShippingIdFromUsername(String username) {
		try {
            ResultSet result = Main.stmt.executeQuery("select max(bookstore.user_shippinginfo.shipping_id) as shipid from bookstore.shipping_information join bookstore.user_shippinginfo on bookstore.shipping_information.shipping_id=bookstore.user_shippinginfo.shipping_id where username = '" + username + "'");
            result.next();
            return result.getString("shipid");
            

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static String getBillingIdFromUsername(String username) {
		try {
            ResultSet result = Main.stmt.executeQuery("select max(bookstore.user_billinginfo.billing_id) as billid from bookstore.billing_information join bookstore.user_billinginfo on bookstore.billing_information.billing_id=bookstore.user_billinginfo.billing_id where username = '" + username + "'");
            result.next();
            return result.getString("billid");

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static HashMap<String,Integer> getCartContents(String cartId){
		try {
			HashMap<String,Integer> cartContents = new HashMap<>();
			ResultSet result = Main.stmt.executeQuery("select isbn,cartquantity from bookstore.book_cart WHERE cart_id = '" + cartId + "'");
			while (result.next()) {
				cartContents.put(result.getString("isbn"), result.getInt("cartquantity"));
            }
			
			return cartContents;}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
            
        }
	
	public static boolean isCartEmpty(String cart_id) {
		try {
			ResultSet result = Main.stmt.executeQuery("SELECT * FROM bookstore.book_cart WHERE isbn IS NOT NULL and cart_id = '"+ cart_id + "'");
			if(result.next()) {
				return false;
			}
			else {
				return true;
			}
		}catch (SQLException e) {
            e.printStackTrace();
		}return true;
	}

	
	public static ArrayList<String> getBookInfoFromName(String bookName){
		try {			
			Main.stmt = Main.conn.createStatement();
			ArrayList<String> bookDetails = new ArrayList<>();
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.book WHERE book_name = '" + bookName + "'");
			while (result.next()) {
				String author_id = result.getString("author_id");
				bookDetails.add(result.getString("isbn"));
				bookDetails.add(result.getString("book_name"));
				bookDetails.add(result.getString("price"));
				bookDetails.add(result.getString("num_pages"));
				bookDetails.add(result.getString("quantity"));
				bookDetails.add(result.getString("publishers_cut"));
				bookDetails.add(result.getString("publisher_name"));
				bookDetails.add(result.getString("genre"));				
				Main.stmt = Main.conn.createStatement();		
				ResultSet authorResult = Main.stmt.executeQuery("Select * from bookstore.author where author_id = '" + author_id + "'");
				while(authorResult.next()) {
					bookDetails.add(authorResult.getString("first_name"));
					bookDetails.add(authorResult.getString("last_name"));
				}
            }
			return bookDetails;
		} catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}

	public static ArrayList<ArrayList<String>>getPublishersInfo(){
		try {
			ArrayList<ArrayList<String>> listofPublishers = new ArrayList<>();
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.publisher");
			while (result.next()) {
				ArrayList<String>publisherInfo = new ArrayList<>();
				publisherInfo.add(result.getString("name"));
				publisherInfo.add(result.getString("email"));
				publisherInfo.add(result.getString("phone"));
				publisherInfo.add(result.getString("banking_account"));
				listofPublishers.add(publisherInfo);
			}
			return listofPublishers;}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
            
	}
	
	
	
	public static ArrayList<String> getBookInfoFromIsbn(String isbn){
		try {
			Main.stmt = Main.conn.createStatement();
			ArrayList<String> bookDetails = new ArrayList<>();
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.book WHERE isbn = '" + isbn + "'");
			while (result.next()) {
				String author_id = result.getString("author_id");
				bookDetails.add(result.getString("isbn"));
				bookDetails.add(result.getString("book_name"));
				bookDetails.add(result.getString("price"));
				bookDetails.add(result.getString("num_pages"));
				bookDetails.add(result.getString("quantity"));
				bookDetails.add(result.getString("publishers_cut"));
				bookDetails.add(result.getString("publisher_name"));
				bookDetails.add(result.getString("genre"));			
				Main.stmt = Main.conn.createStatement();			
				ResultSet authorResult = Main.stmt.executeQuery("Select * from bookstore.author where author_id = '" + author_id + "'");
				while(authorResult.next()) {
					bookDetails.add(authorResult.getString("first_name"));
					bookDetails.add(authorResult.getString("last_name"));
				}			
            }
			return bookDetails;
		} catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static ArrayList<ArrayList<String>> getListOfBookInfosFromAuthorName(String authorfirst, String authorLast){
		try {
			ArrayList<ArrayList<String>> listofbookDetails = new ArrayList<>();
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.author WHERE first_name = '" + authorfirst + "'" + "and last_name = '" + authorLast + "'" );
			while (result.next()) {
				String authorId = result.getString("author_id");
				Main.stmt = Main.conn.createStatement();
				ResultSet authorbookResult = Main.stmt.executeQuery("Select * from bookstore.book where author_id = '" + authorId + "'");
				while(authorbookResult.next()) {
					listofbookDetails.add(getBookInfoFromIsbn(authorbookResult.getString("isbn")));	
            }
			}
			return listofbookDetails;}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
            
        }
	
	public static ArrayList<String>getGenres(){
		 try {
	            ArrayList<String> genres = new ArrayList<>();
	            ResultSet result = Main.stmt.executeQuery("select distinct genre from bookstore.book");
	            while (result.next()) {
	            	genres.add(result.getString("genre"));
	            }
	            return genres;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	}
	
	public static ArrayList<ArrayList<String>> getListOfBookInfosFromGenre(String genre){
		try {
			ArrayList<ArrayList<String>> listofbookDetails = new ArrayList<>();
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.book WHERE genre = '" + genre + "'");
			while (result.next()) {
				String isbn = result.getString("isbn");
			    listofbookDetails.add(getBookInfoFromIsbn(isbn));	           
			}
			return listofbookDetails;}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
            
        }
       
    
	
	public static int getIsbnQuantity(String isbn) {
		 try {
			ResultSet result= Main.stmt.executeQuery("select quantity from bookstore.book where isbn = '" + isbn + "'");
			result.next();
			return result.getInt("quantity");
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	        	return 0;
	     }
	}
	
	
	public static void putNewBookInCart(String isbn, String cart_id) {
		 try {
			 Main.stmt.executeUpdate("insert into bookstore.book_cart values( '" + cart_id + "' , '" + isbn + "',1)" );
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static String getUsersCartId(String username) {
		try {
			ResultSet result = Main.stmt.executeQuery("select cart_id from bookstore.cart where username = '" + username + "'");
			ArrayList<Integer>cart_idList = new ArrayList<>();
			
            while(result.next()) {
            	String cart_id = result.getString("cart_id");
            	cart_idList.add(Integer.parseInt(cart_id));

            }
            return String.valueOf(Collections.max(cart_idList));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static void createCart(String username) {
		try {
			 Main.stmt.executeUpdate("insert into bookstore.cart values(default,'"+ username + "')" );			 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static void createUser(String username, String password, boolean admin, String first_name, String last_name) {
		try {
			 Main.stmt.executeUpdate("insert into bookstore.user values( '" + username + "' , '" + password + "' , '" + first_name + "' , '" + last_name + "' , '" + admin + "')" );			 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
		
	public static void insertPublisherInformation(String name, String phonenumber, String email, String bankingaccount, String street, String city, String province, String postalcode, String country) {
		try {
			 Main.stmt.executeUpdate("insert into bookstore.publisher values('" + name + "','"+email+"','"+phonenumber+"','"+bankingaccount+"',default)");
			 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
		try {
			 Main.stmt.executeUpdate("insert into bookstore.shipping_information values(default,'" + street + "','"+city+"','"+province+"','"+postalcode+"','"+ country + "')");
			 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static void insertNewAuthor(String firstname, String lastname) {
		try {
			 Main.stmt.executeUpdate("insert into bookstore.author values(default,'" + firstname + "','"+lastname+"')");
			 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static ArrayList<ArrayList<String>> getAuthorInfos(){
		try {
			ArrayList<ArrayList<String>> listofAuthors = new ArrayList<>();
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.author");
			while (result.next()) {
				ArrayList<String>authorInfo = new ArrayList<>();
				authorInfo.add(result.getString("author_id"));
				authorInfo.add(result.getString("first_name"));
				authorInfo.add(result.getString("last_name"));
				listofAuthors.add(authorInfo);
			}
			return listofAuthors;}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
            
	}
	
	public static String getAuthorFirstNameFromId(String id) {
		try {
			ResultSet result = Main.stmt.executeQuery("select first_name from bookstore.author where author_id = '"+id + "'");
			result.next();
			return result.getString("first_name");}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
	}
	
	public static String getAuthorLastNameFromId(String id) {
		try {
			ResultSet result = Main.stmt.executeQuery("select last_name from bookstore.author where author_id = '"+id + "'");
			result.next();
			return result.getString("last_name");}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
	}
	
	public static boolean doesAuthorExist(String author_id) {
		try {
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.author where author_id = '" + author_id + "'");
			if(result.next()) {
				return true;
			}
			else {
				return false;
			}
			 } catch (SQLException e) {
		        	System.out.println(e.getLocalizedMessage());
		    		return false;
			 }
		
	}
	
	
	public static void insertBook(String isbn, String bookname, String price, String numpages, String quantity, String publishercut, String author_id,
			String genre, String publishername) {
		
		try {
			 if(author_id.equals("default")) {
				 Main.stmt.executeUpdate("insert into bookstore.book values('" + isbn + "','"+bookname+ "','"+price+ "','"+numpages+ "','"+quantity+ "','"+publishercut+"', '"+ publishername +"', currval('bookstore.author_author_id_seq'), '"+ genre + "')");
			 }
			 else {
				 Main.stmt.executeUpdate("insert into bookstore.book values('" + isbn + "','"+bookname+ "','"+price+ "','"+numpages+ "','"+quantity+ "','"+publishercut+"', '"+ publishername +"','"+ author_id+"','"+ genre + "')");
			 }


		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
		
	}
		
	public static void insertShippingAddress(String street, String city, String province, String postalcode, String country) {
		
		try {
			Main.stmt.executeUpdate("insert into bookstore.shipping_information values(default,'" + street + "','"+city+ "','"+province+ "','"+postalcode+ "','"+country+"')");

		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
		
	}
	
public static void insertBillingAddress(String card, String cvv, String firstname, String lastname, String street, String city, String province, String postalcode, String country) {
		
		try {
			Main.stmt.executeUpdate("insert into bookstore.billing_information values(default,'"+ card + "','"+cvv+ "','"+firstname+ "','"+lastname+ "','" + street + "','"+city+ "','"+province+ "','"+postalcode+ "','"+country+"')");

		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
		
	}
	
	public static boolean userHasShippingAddress(String username) {
		try {
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.user_shippinginfo where username= '"+username+"'");
			if(result.next()) {
				return true;
			}
			else {
				return false;
			}
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	        	return false;
	     }
	}
	
	public static boolean userHasBillingAddress(String username) {
		try {
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.user_billinginfo where username= '"+username+"'");
			if(result.next()) {
				return true;
			}
			else {
				return false;
			}
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	        	return false;
	     }
	}

	
	public static void linkUserToShippingAddress(String username) {
		try {
			Main.stmt.executeUpdate("insert into bookstore.user_shippinginfo values('" + username + "', currval('bookstore.shipping_information_shipping_id_seq'))");
			 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	public static void linkUserToBillingAddress(String username) {
		try {
			Main.stmt.executeUpdate("insert into bookstore.user_billinginfo values('" + username + "', currval('bookstore.billing_information_billing_id_seq'))");
			 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	
	public static int getCostFromIsbn(String isbn) {
		try {
		Main.stmt = Main.conn.createStatement();
		ResultSet result = Main.stmt.executeQuery("select price from bookstore.book WHERE isbn = '" + isbn + "'");
		result.next();
		return result.getInt("price");
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	    		return 0;
		 }
	}
	
	
	public static int getOrderTotal(String username) {
		try {
		String cart_id = getUsersCartId(username);
		ArrayList<Integer> prices = new ArrayList<>();
		ResultSet result = Main.stmt.executeQuery("select isbn,cartquantity from bookstore.book_cart WHERE cart_id = '" + cart_id + "'");
		while (result.next()) {
			String isbn = result.getString("isbn");
			int cartQuant = result.getInt("cartquantity");
			updateBookstoreStock(isbn, getBookQuantity(isbn)-cartQuant);   	

			prices.add(getCostFromIsbn(isbn) * cartQuant);
		}
		return prices.stream().mapToInt(Integer::intValue).sum();
		
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	    		return 0;
		 }
	}

	
	public static String createOrder(int orderTotal, String billingid , String shippingid) {
		java.util.Date utilDate = new java.util.Date();		
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    String trackingNum = String.valueOf((long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L);
	    String shippingMessage = "Order Submitted";
	    try {
	    	Main.stmt.executeUpdate("insert into bookstore.order values(default,'" + sqlDate + "','"+trackingNum+ "','"+orderTotal+"','"+shippingMessage + "','" + billingid + "','" + shippingid + "')");
			 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	    try {
	    	ResultSet result = Main.stmt.executeQuery("select order_id from bookstore.order where order_id = (select max(order_id) from bookstore.order)");
			result.next();
			return result.getString("order_id");
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	    return null;
	}
	
	
	
	public static boolean doesUserExist(String username) {
		try {
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.user where username = '" + username + "'");
			if(result.next()) {
				return true;
			}
			else {
				return false;
			}
			 } catch (SQLException e) {
		        	System.out.println(e.getLocalizedMessage());
		    		return false;
			 }
	}
	
	public static boolean doesPublisherExist(String name) {
		try {
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.publisher where name = '" + name + "'");
			if(result.next()) {
				return true;
			}
			else {
				return false;
			}
			 } catch (SQLException e) {
		        	System.out.println(e.getLocalizedMessage());
		    		return false;
			 }
	}
	
	public static boolean doesOrderIdExist(String order_id) {
		try {
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.order where order_id = '" + order_id + "'");
			if(result.next()) {
				return true;
			}
			else {
				return false;
			}
			 } catch (SQLException e) {
		        	System.out.println(e.getLocalizedMessage());
		    		return false;
			 }
	}
	
	public static ArrayList<String> getOrderDetailsFromOrderNum(String orderNum){
		try {
			ArrayList<String>orderdeets = new ArrayList<>();
			ResultSet result = Main.stmt.executeQuery("select * from bookstore.order where order_id = '" + orderNum + "'");
			result.next();
			orderdeets.add(result.getString("order_date"));
			orderdeets.add(result.getString("shipping_message"));
			orderdeets.add(result.getString("tracking_id"));
			orderdeets.add(result.getString("price"));
			return orderdeets;
		}
		catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
    		return null;
		}		
	}
	
	public static void linkCartToOrder(String username) {
		try {
		     String cart_id = getUsersCartId(username);
			 Main.stmt.executeUpdate("insert into bookstore.order_cart values('" + cart_id + "',currval('bookstore.order_order_id_seq'))");
			 
		 } catch (SQLException e) {
	        	System.out.println(e.getLocalizedMessage());
	     }
	}
	
	
	public static HashMap<String, ArrayList<String>> getAuthorReport(String time) {
		try {
			HashMap<String, ArrayList<String>> reportMap = new HashMap<>();
			ResultSet result = Main.stmt.executeQuery("select author_id, sum(cartquantity) as cartquantitysum, sum(bookstore.book.price * cartquantity) as pricesum from bookstore.order "
					+ "natural join bookstore.order_cart natural join bookstore.book_cart join bookstore.book on bookstore.book.isbn = bookstore.book_cart.isbn "
					+ "where date_part('"+time+"',now()-order_date::timestamp) = 0 "
					+ "group by author_id");
			while(result.next()) {
				ArrayList<String>dataList = new ArrayList<>();
				dataList.add(result.getString("cartquantitysum"));
				dataList.add(result.getString("pricesum"));
				reportMap.put(result.getString("author_id"), dataList);
			}
			return reportMap;
			}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
	}
	
	public static HashMap<String, ArrayList<String>> getGenreReport(String time) {
		try {
			HashMap<String, ArrayList<String>> reportMap = new HashMap<>();
			ResultSet result = Main.stmt.executeQuery("select genre, sum(cartquantity) as cartquantitysum, sum(bookstore.book.price * cartquantity) as pricesum from bookstore.order "
					+ "natural join bookstore.order_cart natural join bookstore.book_cart join bookstore.book on bookstore.book.isbn = bookstore.book_cart.isbn "
					+ "where date_part('"+time+"',now()-order_date::timestamp) = 0 "
					+ "group by genre");
			while(result.next()) {
				ArrayList<String>dataList = new ArrayList<>();
				dataList.add(result.getString("cartquantitysum"));
				dataList.add(result.getString("pricesum"));
				reportMap.put(result.getString("genre"), dataList);
			}
			return reportMap;
			}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
	}
	
	public static ArrayList<String> getAllBooks(){
		try {
			ArrayList<String>data = new ArrayList<>();
			ResultSet result = Main.stmt.executeQuery("select book_name from bookstore.book");
			while(result.next()) {
				data.add(result.getString("book_name"));
			}
			return data;
			}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
	
	}
	
	public static ArrayList<String> getSalesReport(String time){
		try {
			ArrayList<String>data = new ArrayList<>();
			ResultSet result = Main.stmt.executeQuery("select sum(bookstore.book.price * cartquantity) as totalrevenue, round(sum(bookstore.book.price * bookstore.book.publishers_cut / 100),2) as totalpublishercut from bookstore.order natural join bookstore.order_cart natural join bookstore.book_cart join bookstore.book on bookstore.book.isbn = bookstore.book_cart.isbn "
					+ "where date_part('"+time+"',now()-order_date::timestamp) = 0 ");
			while(result.next()) {
				data.add(result.getString("totalrevenue"));
				data.add(result.getString("totalpublishercut"));
			}
			return data;
			}
		catch (SQLException e) {
            e.printStackTrace();
		}return null;
	}
	
}
