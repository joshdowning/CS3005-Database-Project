package lookinnabook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
	public static Connection conn;
    public static Statement stmt;
    private  static Scanner input;
    private static String currentUser="";
    
    
    public static void register() {
		System.out.println("Beginning Registration. Enter a username: ");
		String username = input.nextLine();
		while(username.equals("")) {
			System.out.println("Username cannot be empty. Try again: ");
			username = input.nextLine();
		}
		if(Query.doesUserExist(username)) {
			System.out.println("Someone already has that username, please enter a new one");
			register();
		}
		System.out.println("Enter a password: ");
		String password = input.nextLine();
		
		System.out.println("Enter your first name: ");
		String firstname = input.nextLine();
		
		System.out.println("Enter your last name: ");
		String lastname = input.nextLine();
		
		Query.createUser(username, password, false, firstname, lastname);
		Query.createCart(username);
		currentUser=username;
		browse(true);
    }
    
    
    public static void login() {
    	System.out.println("Beginning Login. Enter a username: ");
		String username = input.nextLine();
		
		System.out.println("Enter your password: ");
		String password = input.nextLine();
		
		if(password.equals(Query.getPassword(username))) {
			if(Query.checkIfAdmin(username)) {
				currentUser = username;
				adminBrowse();
			}
			else {
				currentUser=username;
				browse(true);
			}
		}
		else {
			System.out.println("Incorrect login information. Type 1 to register. Type anything else to retry.");
			if(input.nextLine().equals("1")) {
				register();
			}
			else {
				login();
			}

		}
    }
    
    public static void searchBook() {
		System.out.println("Welcome to the search tool! Type 1 to search for a book using isbn. Type 2 to search using book name. Type 3 to search authors. Type 4 to search genres."
				+ "Type anything else to return to main menu");
		switch(input.nextLine()) {
				case "1":
					searchIsbn();	
					break;

				
				case "2":
					searchBookName();
					break;

					
				case "3":
					searchAuthors();	
					break;

				
				case "4":
					searchGenres();	
					break;

				
				default:
					browse(true);	
					break;

		}
    }
		
    public static void searchIsbn() {
    	System.out.println("Enter the ISBN to search: ");
    	String isbn = input.nextLine();
    	ArrayList<String>bookInfo = new ArrayList<>();
    	bookInfo = Query.getBookInfoFromIsbn(isbn);
    	if(bookInfo.size()==0) {
    		System.out.println("No book found with that isbn. Type 1 to try again. Type anything else to return to main menu: ");
    		if(input.nextLine().equals("1")) {
    			searchIsbn();
    		}
    		else {
    			browse(true);
    		}		
    	}
    	else {
    		printBook(bookInfo);
    		
    		
    	}
    }
    
    public static void searchBookName() {
    	System.out.println("Enter the book name to search: ");
    	String bookname = input.nextLine();
    	ArrayList<String>bookInfo = new ArrayList<>();
    	bookInfo = Query.getBookInfoFromName(bookname);
    	if(bookInfo.size()==0) {
    		System.out.println("No book found with that name. Type 1 to try again. Type anything else to return to main menu: ");
    		if(input.nextLine().equals("1")) {
    			searchIsbn();
    		}
    		else {
    			browse(true);
    		}		
    	}
    	else {
    		
    		printBook(bookInfo);
    		
    		System.out.println("\nType 1 to search another ISBN. Type anything else to return to main menu: ");
    		if(input.nextLine().equals("1")) {
    			searchIsbn();
    		}
    		else {
    			browse(true);
    		}		
    	}
    }
    
    public static void printBook(ArrayList<String> book) {
    	String isbn = book.get(0);
    	String bookname = book.get(1);
		String price = book.get(2);
		String pageCount = book.get(3);
		String quantity = book.get(4);
		String publisherName = book.get(6);
		String genre = book.get(7);
		String authorFirstname = book.get(8);
		String authorLastname = book.get(9);
		
    	System.out.println("Book found with name: " + bookname);
		System.out.println("Isbn: " + isbn);
		System.out.println("Price: $" + price);
		System.out.println("Page count: " + pageCount);
		System.out.println("Quantity: " + quantity);
		System.out.println("Publisher: " + publisherName);
		System.out.println("Author: " + authorFirstname + " " + authorLastname);
		System.out.println("Genre: " + genre);    	
		
		System.out.println("Would you like to add this book to your cart? Type 1 to add to cart. Type anything else to return to menu.");
		switch(input.nextLine()) {
		case "1":
			if(!currentUser.equals("")) {
				putBookInCart(isbn);
				System.out.println("Returning to menu.");
				browse(true);
				break;		
			}
			else {
				System.out.println("You need to be logged in to do that. returning to menu");
				browse(false);
				break;
			}
		default:
			if(!currentUser.equals("")) {
				browse(true);
			}
			else {
				browse(false);
			}
			break;
		}
    }
    
    
    
    public static void searchAuthors() {
    	System.out.println("Enter the Author name to search for their books");
    	System.out.println("Enter the Author first name: ");
    	String firstname = input.nextLine();
    	System.out.println("Enter the Author last name: ");
    	String lastname = input.nextLine();

    	ArrayList<ArrayList<String>>authorBooks = new ArrayList<>();
    	authorBooks = Query.getListOfBookInfosFromAuthorName(firstname,lastname);
    	if(authorBooks.size()==0) {
    		System.out.println("No books found from that author. Type 1 to try again. Type anything else to return to main menu: ");
    		if(input.nextLine().equals("1")) {
    			searchAuthors();
    		}
    		else {
    			browse(true);
    		}		
    	}
    	else {
    		System.out.println("The following book(s) were found from author " +firstname + " " + lastname + ": ");
    		authorBooks.forEach(book -> {
    			System.out.println(book.get(1));			
    		});
    		getAuthorBookDetails(authorBooks);
    		
    	}
    }
    
    public static void getAuthorBookDetails(ArrayList<ArrayList<String>> authorBooks) {
    	System.out.println("Enter the index of the book you would like to see more details of (Starting from 0). Or type menu to return to the menu");
		String answer = input.nextLine();
		while(answer.matches("[+-]?\\d*(\\.\\d+)?") && Integer.parseInt(answer)>authorBooks.size()-1) {
			System.out.println("That book index does not exist! Try again: ");
			answer = input.nextLine();
		}
		if(answer.matches("[+-]?\\d*(\\.\\d+)?")) {
			printBook(authorBooks.get(Integer.parseInt(answer)));
			System.out.println("\n Type 1 to search for another book. Type 2 to search a new author. Type anything else to return to menu.");
			switch(input.nextLine()) {
			case "1":
				getAuthorBookDetails(authorBooks);
				break;

			
			case "2":
				searchAuthors();
				break;

				
			default:
				browse(true);
			}
		}
		else if(answer.equals("menu")) {
			browse(true);
		}
		
		else {
	    	System.out.println("You need to enter a number index or type menu!");
	    	getAuthorBookDetails(authorBooks);
		}
    }
    
    
    public static void searchGenres() {
    	System.out.println("Available genres are: ");
    	ArrayList<String> genres =  Query.getGenres();
    	genres.forEach(genre->System.out.println(genre));
    	System.out.println("Enter the genre to find books listed under it");    	
    	String genre = input.nextLine();
    	
    	ArrayList<ArrayList<String>>genreBooks = new ArrayList<>();
    	genreBooks = Query.getListOfBookInfosFromGenre(genre);
    	if(genreBooks.size()==0) {
    		System.out.println("No books found in that genre. Type 1 to try again. Type anything else to return to main menu: ");
    		if(input.nextLine().equals("1")) {
    			searchGenres();
    		}
    		else {
    			browse(true);
    		}		
    	}
    	else {
    		System.out.println("The following book(s) were found under genre " +genre +": ");
    		genreBooks.forEach(book -> {
    			System.out.println(book.get(1));			
    		});
    		getGenreBookDetails(genreBooks);
    		
    	}
    }
    
    public static void getGenreBookDetails(ArrayList<ArrayList<String>> genreBooks) {
    	System.out.println("Enter the index of the book you would like to see more details of (Starting from 0). Or type menu to return to the menu");
		String answer = input.nextLine();
		while(answer.matches("[+-]?\\d*(\\.\\d+)?") && Integer.parseInt(answer)>genreBooks.size()) {
			System.out.println("That book index does not exist! Try again: ");
			answer = input.nextLine();
		}
		if(answer.matches("[+-]?\\d*(\\.\\d+)?")) {
			printBook(genreBooks.get(Integer.parseInt(answer)));
			System.out.println("\n Type 1 to search for another book. Type 2 to search a new genre. Type anything else to return to menu.");
			switch(input.nextLine()) {
			case "1":
				getGenreBookDetails(genreBooks);
				break;
			
			case "2":
				searchGenres();
				break;
				
			default:
				browse(true);
			}
		}
		else if(answer.equals("menu")) {
			browse(true);
		}
		
		else {
	    	System.out.println("You need to enter a number index or type menu!");
	    	getAuthorBookDetails(genreBooks);
		}
    }
		
    
    public static void putBookInCart(String isbn) {
    	String cartId = Query.getUsersCartId(currentUser);
    	if(Query.isBookInCart(isbn, cartId)) {
    		if(Query.isBookInStock(isbn) && !Query.cartGreaterThanStock(cartId,isbn)) {
    			Query.increaseCartBookQuantityByOne(isbn, cartId);
    			//Query.decreaseBookQuantityByOne(isbn);   	
    			System.out.println("increased quantity in your cart by 1");
    		}
    		else {
    			System.out.println("Not enough of that book left in stock");
    		}
    		
    	}
    	else {
    		if(Query.isBookInStock(isbn)) {
    			Query.putNewBookInCart(isbn, cartId);
    			//Query.decreaseBookQuantityByOne(isbn);   
    			System.out.println("Added book to cart");
    		}
    	}
    }
    
    
    public static void viewCart() {
		System.out.println("Isbn's in your cart are listed below");
		HashMap<String,Integer> cartMap = Query.getCartContents(Query.getUsersCartId(currentUser));
		cartMap.keySet().forEach(key->System.out.println("ISBN: "+key + " Quantity: "+cartMap.get(key)));
		System.out.println("\nType 1 to return to menu or Enter the isbn you would like to modify");
		String answer = input.nextLine();
		if(answer.equals("1")) {
			browse(true);
		}
		else {
			updateCart(answer);
		}

    }
    
    public static void updateCart(String answer) {
    	if(!Query.isBookInCart(answer, Query.getUsersCartId(currentUser))) {
    		System.out.println("That book is not in your cart!");
    		viewCart();
    	}
    	System.out.println("Type 1 to increase the cart quantity of this isbn by 1. Type 2 to decrease by 1");
		String response = input.nextLine();
		if(response.equals("1")) {
			Query.increaseCartBookQuantityByOne(answer, Query.getUsersCartId(currentUser));
	    	System.out.println("Added another copy to cart");
	    	viewCart();
		}
		else if(response.equals("2")) {
			Query.decreaseCartBookQuantityByOne(answer, Query.getUsersCartId(currentUser));
	    	System.out.println("Removed 1 copy from cart");
			viewCart();
		}
		else {
			System.out.println("Enter 1 or 2!");
		}
    }
    
    
    public static void checkOrder() {
    	System.out.println("Enter your order number: ");
    	String orderNum = input.nextLine();
    	if(Query.doesOrderIdExist(orderNum)) {
    		ArrayList<String> orderdeets = Query.getOrderDetailsFromOrderNum(orderNum);
    		System.out.println("Order found for order number : "+ orderNum);
    		System.out.println("Date placed: "+ orderdeets.get(0));
    		System.out.println("Shipping Status Message: "+ orderdeets.get(1));
    		System.out.println("Tracking number: "+ orderdeets.get(2));
    		System.out.println("Order total: "+ orderdeets.get(3));
    		browse(true);
    	}
    	else {
    		System.out.println("Wrong order number. Type 1 to try again. Type anything else to go back to main menu");
			String answer = input.nextLine();
			if(answer.equals("1")){
				checkOrder();
			}
			else {
				browse(true);
			}
    	}
    }
    
    public static void adminAddBook() {
    	System.out.println("Will add book with the following inputs");
    	System.out.println("Enter book isbn: ");
    	String isbn = input.nextLine();
    	System.out.println("Enter book name: ");
    	String bookname = input.nextLine();
    	System.out.println("Enter book price: ");
    	String price = input.nextLine();
    	System.out.println("Enter number of pages: ");
    	String numpage = input.nextLine();
    	System.out.println("Enter quantity: ");
    	String quantity = input.nextLine();
    	System.out.println("Enter publishers cut: ");
    	String publishercut = input.nextLine();
    	
    	System.out.println("Author Ids are listed below: ");
    	Query.getAuthorInfos().forEach(author->System.out.println(author.get(0) + " "+ author.get(1) + " "+  author.get(2)));
    	String authorId ="";
    	System.out.println("Does the author already exist? Type 1 if yes and the authors name will be autommatically added. Type anything else if no (author id will be added automatically).");
    	if(input.nextLine().equals("1")) {
    		System.out.println("Enter author id of book to add: ");
    		authorId = input.nextLine();
    		while(!Query.doesAuthorExist(authorId)) {
        		System.out.println("That author id does not exist! Try again or type menu to return to admin menu: ");
        		String answer = input.nextLine();
        		if(answer.equals("menu")) {
        			adminBrowse();
        		}
        		else {
        			authorId = answer;
        		}
    		}
    		String author_firstname = Query.getAuthorFirstNameFromId(authorId);
    		String author_lastname = Query.getAuthorLastNameFromId(authorId);
    	}
    	else {
    		authorId = "default";
        	System.out.println("Enter author first name: ");
    		String author_firstname = input.nextLine();
        	System.out.println("Enter author last name: ");
    		String author_lastname = input.nextLine();
    		Query.insertNewAuthor(author_firstname, author_lastname);
    	}
    	
    	System.out.println("Enter genre: ");
		String genre = input.nextLine();
    	System.out.println("Enter publisher: ");
		String publisher = input.nextLine();
		while(!Query.doesPublisherExist(publisher)) {
			System.out.println("Please enter an existing publisher. Try again or type menu to return to admin menu: ");
			String answer = input.nextLine();
    		if(answer.equals("menu")) {
    			adminBrowse();
    		}
    		else {
    			publisher = answer;
    		}
		}
		Query.insertBook(isbn, bookname, price, numpage, quantity, publishercut, authorId, genre, publisher);
		System.out.println("Book added. returning to admin menu.");
		adminBrowse();
    }
    
    public static void adminRemoveBook() {
		System.out.println("Please enter the isbn of the book to delete: ");
		String isbn = input.nextLine();
		Query.deleteBook(isbn);
		System.out.println("Book with isbn: "+ isbn + " is no longer in the bookstore. Returning to admin menu");
		adminBrowse();
    }
    
    public static void adminViewPublishers() {
		System.out.println("Existing publishers: ");
		Query.getPublishersInfo().forEach(publisher->System.out.println("\nPublisher name: " + publisher.get(0) + "\nEmail: "+publisher.get(1) + "\nPhone: " + publisher.get(2) + "\nBanking account: "+ publisher.get(3)));
		
		System.out.println("Would you like to add a new publisher? Type 1 if yes. Anything else to return to admin menu: ");
		if(input.nextLine().equals("1")) {
			adminAddPublisher();
		}
		else {
			adminBrowse();
		}
    }
    
    public static void adminAddPublisher() {
    	System.out.println("Enter publisher name: ");
    	String publishername = input.nextLine();
    	System.out.println("Enter publisher email: ");
    	String email = input.nextLine();
    	System.out.println("Enter publisher phone number: ");
    	String phone = input.nextLine();
    	System.out.println("Enter publisher banking account: ");
    	String bank = input.nextLine();
    	System.out.println("Enter publisher street name: ");
    	String street = input.nextLine();
    	System.out.println("Enter publisher city: ");
    	String city = input.nextLine();
    	System.out.println("Enter publisher province: ");
    	String province = input.nextLine();
    	System.out.println("Enter publisher postalcode: ");
    	String pcode = input.nextLine();
    	System.out.println("Enter publisher country: ");
    	String country = input.nextLine();
    	Query.insertPublisherInformation(publishername, phone, email, bank, street, city, province, pcode, country);
    	System.out.println("Added publisher. Returning to admin menu.");
    	adminBrowse();
    }
    
    public static void viewAuthorReports() {
    	System.out.println("Type 1 to see author reports for the last day. Type 2 for the last month. Type 3 for the last year.");
    	HashMap<String, ArrayList<String>>authorReports = new HashMap<>();
    	switch(input.nextLine()) {
		case "1":
			authorReports = Query.getAuthorReport("day");
			break;
		
		case "2":
			authorReports = Query.getAuthorReport("month");
			break;
		
		case "3":
			authorReports = Query.getAuthorReport("year");
			break;
    	default:
    		System.out.println("Thats not a valid option!");
    		viewAuthorReports();
    	}
    	
    	for (Entry<String, ArrayList<String>> entry : authorReports.entrySet()) {
    		System.out.println("\nReport for author: " + Query.getAuthorFirstNameFromId(entry.getKey()) + " " + Query.getAuthorLastNameFromId(entry.getKey()));
    		System.out.println("Total amount of books sold: "+ entry.getValue().get(0));
    		System.out.println("Total amount of revenue made: "+ entry.getValue().get(1));
    	}
    	adminViewReports();
	}
    
  
    public static void viewGenreReports() {
    	System.out.println("Type 1 to see genre reports for the last day. Type 2 for the last month. Type 3 for the last year.");
    	HashMap<String, ArrayList<String>>authorReports = new HashMap<>();
    	switch(input.nextLine()) {
		case "1":
			authorReports = Query.getGenreReport("day");	
			break;
		
		case "2":
			authorReports = Query.getGenreReport("month");	
			break;

		case "3":
			authorReports = Query.getGenreReport("year");	
			break;
    	default:
    		System.out.println("Thats not a valid option!");
    		viewGenreReports();
    	}
    	
    	for (Entry<String, ArrayList<String>> entry : authorReports.entrySet()) {
    		System.out.println("\nReport for genre: " + entry.getKey());
    		System.out.println("Total amount of books sold: "+ entry.getValue().get(0));
    		System.out.println("Total amount of revenue made: "+ entry.getValue().get(1));
    	}
    	adminViewReports();
    }
    
    public static void viewProfitReports() {
    	System.out.println("Type 1 to see profit reports for the last day. Type 2 for the last month. Type 3 for the last year.");
    	ArrayList<String>profitReports = new ArrayList<>();
    	switch(input.nextLine()) {
		case "1":
			profitReports = Query.getSalesReport("day");
    		System.out.println("Total amount of revenue earned in the last day: " + profitReports.get(0));
    		System.out.println("Total amount owed to publishers in the last day: " + profitReports.get(1));
    		break;
		
		case "2":
			profitReports = Query.getSalesReport("month");	
    		System.out.println("Total amount of revenue earned in the last month: " + profitReports.get(0));
    		System.out.println("Total amount owed to publishers in the last month: " + profitReports.get(1));
    		break;
		
		case "3":
			profitReports = Query.getSalesReport("year");
    		System.out.println("Total amount of revenue earned in the last year: " + profitReports.get(0));
    		System.out.println("Total amount owed to publishers in the last year: " + profitReports.get(1));
    		break;
    	
    	default:
    		System.out.println("Thats not a valid option!");
    		viewProfitReports();
    	}
    	adminViewReports();
    }
    
    public static void adminViewReports() {
    	System.out.println("Type 1 to view author reports. Type 2 to view genre reports. Type 3 to view profit reports. Anything else to return to admin menu.");
    		switch(input.nextLine()) {
			
			case "1":
				viewAuthorReports();
				break;
			
			case "2":
				viewGenreReports();
				break;

			case "3":
				viewProfitReports();
				break;
			
			default:
				adminBrowse();
    	}
    }
    
    public static void adminViewEmails() {
    	System.out.println("Sent emails: ");
    	HashMap<String,String>emailMap = Query.getSentEmailInfo();
    	for (Entry<String, String> entry : emailMap.entrySet()) {
    		System.out.println("\nisbn: " + entry.getKey() + " email sent to " + entry.getValue() + " to order more books.");  		
    	}
    	adminBrowse();
    }
    
    public static void adminAlterShippingMessage() {
    	System.out.println("Enter an order number to modify: ");
    	String orderNum = input.nextLine();
    	if(Query.doesOrderIdExist(orderNum)) {
        	System.out.println("Enter the new shipping message: ");
        	String shipmes = input.nextLine();
        	Query.adminUpdateOrderShipping(shipmes, orderNum);
        	System.out.println("Order shipping message updated.");
        	adminBrowse();
    	}
    	else {
    		System.out.println("Wrong order number. Type 1 to try again. Type anything else to go back to admin menu");
			String answer = input.nextLine();
			if(answer.equals("1")){
				adminAlterShippingMessage();
			}
			else {
				adminBrowse();
			}
    	}

    }
    
    public static void logout() {
    	currentUser = "";
    	System.out.println("Logging out..");
    	initialMenu();
    }
    
    public static void adminBrowse() {
    	System.out.println("Welcome to the admin screen. Type 1 to add a book to the store. Type 2 to remove a book from the store. Type 3 to view publishers. Type 4 to add a new publisher. Type 5 to see reports. Type 6 to see sent emails to publishers. Type 7 to alter shipping messages. Type 8 to logout");
		switch(input.nextLine()) {
				
				case "1":
					adminAddBook();
					break;

				
				case "2":
					adminRemoveBook();
					break;

				
				case "3":
					adminViewPublishers();
					break;

				
				case "4":
					adminAddPublisher();
					break;

				
				case "5":
					adminViewReports();
					break;

				case "6":
					adminViewEmails();
					break;
				
				case "7":
					adminAlterShippingMessage();
				
				case "8":
					logout();
					break;
				default:
					System.out.println("thats not a valid option");
					adminBrowse();
				}
    }
    
    public static void completeOrder() {
    	int orderTotal = Query.getOrderTotal(currentUser);
    	String shippingId =  Query.getShippingIdFromUsername(currentUser);
    	String billingId =  Query.getBillingIdFromUsername(currentUser);
    	String ordernum = Query.createOrder(orderTotal, shippingId, billingId);
    	Query.linkCartToOrder(currentUser);
    	Query.createCart(currentUser);

    	System.out.println("Order Complete your order number is " + ordernum + ". Returning to main menu.");
    	browse(true);
    }
    
    public static void enterBillingInfo() {
    	System.out.println("Enter Credit Card Number: ");
    	String cardnum = input.nextLine();
    	System.out.println("Enter CVV: ");
    	String cvv = input.nextLine();

    	System.out.println("Enter First Name on Card: " );
    	String firstname = input.nextLine();

    	System.out.println("Enter Last Name on Card: " );
    	String lastname = input.nextLine();
    	
    	System.out.println("Enter Street: ");
    	String street = input.nextLine();

    	System.out.println("Enter City: " );
    	String city = input.nextLine();

    	System.out.println("Enter Province: " );
    	String province = input.nextLine();

    	System.out.println("Enter Postal Code: ");
    	String postalcode = input.nextLine();
    	
    	System.out.println("Enter Country: ");
    	String country = input.nextLine();
    	
    	Query.insertBillingAddress(cardnum, cvv, firstname, lastname, street, city, province, postalcode, country);
    	Query.linkUserToBillingAddress(currentUser);
    	completeOrder();
    }
    
    public static void billingProcess() {
    	if(Query.userHasBillingAddress(currentUser)) {
    		System.out.println("Use existing billing information? Type 1 if yes. Anything else to input new billing address");
    		String answer = input.nextLine();
    		if(answer.equals("1")) {
    			completeOrder();
    		}
    		else {
    			enterBillingInfo();
    		}
    	}
    	else {
    		enterBillingInfo();
    	}
    }
    
    public static void enterShippingInfo() {
    	System.out.println("Enter Street: ");
    	String street = input.nextLine();
    	
    	System.out.println("Enter City: " );
    	String city = input.nextLine();

    	System.out.println("Enter Province: " );
    	String province = input.nextLine();

    	System.out.println("Enter Postal Code: ");
    	String postalcode = input.nextLine();
    	
    	System.out.println("Enter Country: ");
    	String country = input.nextLine();

    	Query.insertShippingAddress(street,city,province,postalcode,country);
    	Query.linkUserToShippingAddress(currentUser);
    	
    	billingProcess();
    	
    }
    
    public static void beginPurchase() {
    	String cart_id = Query.getUsersCartId(currentUser);
    	if(Query.isCartEmpty(cart_id)) {
    		System.out.println("Your cart is empty! Please add some books to your cart before checking out");
    		browse(true);
    	}
    	if(Query.userHasShippingAddress(currentUser)) {
    		System.out.println("Use existing shipping information? Type 1 if yes. anything else to input new shipping address");
    		String answer = input.nextLine();
    		if(answer.equals("1")) {
    			billingProcess();
    		}
    		else {
    			enterShippingInfo();
    		}
    	}
    	else {
    		enterShippingInfo();
    	}
    }
    
    
    
    
    public static void browse(boolean loggedIn) {    	
    	System.out.println("Welcome to the book store main menu! Type 1 to begin a search for a book. Type 2 to check your cart. Type 3 to begin purchasing process. Type 4 to see your order. Type 5 to return to login screen");
		switch(input.nextLine()) {
		
		case "1":
			searchBook();	
			break;

		
		case "2":
			if(loggedIn) {
				viewCart();
			}
			else {
				System.out.println("You need to be logged in to do that");
				System.out.println("Type 1 to return to login/registration menu. Type anything else to return to book store main menu");
				String answer = input.nextLine();
				if(answer.equals("1")){
					initialMenu();
				}
				else {
					browse(loggedIn);
				}
			}
			break;

			
		case "3":
			if(loggedIn) {
				beginPurchase();
			}
			else {
				System.out.println("You need to be logged in to do that");
				System.out.println("Type 1 to return to login/registration menu. Type anything else to return to book store main menu");
				String answer = input.nextLine();
				if(answer.equals("1")){
					initialMenu();
				}
				else {
					browse(loggedIn);
				}
			}
			break;

		case "4":
			if(loggedIn) {
				checkOrder();
			}
			else {
				System.out.println("You need to be logged in to do that");
				System.out.println("Type 1 to return to login/registration menu. Type anything else to return to book store main menu");
				String answer = input.nextLine();
				if(answer.equals("1")){
					initialMenu();
				}
				else {
					browse(loggedIn);
				}
				break;

		}
		case "5":
			logout();
			break;

		default:
			System.out.println("Thats not a valid option!");
			browse(loggedIn);
		}
    }
    
    public static void initialMenu() {
    	System.out.println("Connected to Look Inna Book. Type 1 to register. Type 2 to login. Type 3 to browse ");
		
		switch(input.nextLine()) {
		
		case "1":
			register();
			break;
		
		case "2":
			login();
			break;
		
		case "3":
			browse(false);
			break;
		
		default:
			System.out.println("Thats not a valid option");
			initialMenu();
		}
    }
	
	public static void main(String[] args) {
		try {		
			input = new Scanner(System.in);			
			System.out.println("Enter Database name: ");
			String dataBaseName=input.nextLine();
			System.out.println("Enter Database Username: ");
			String userName=input.nextLine();
			System.out.println("Enter Database Password: ");
			String password=input.nextLine();
				
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dataBaseName, userName, password);
			stmt = conn.createStatement();
					
			initialMenu();	
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
