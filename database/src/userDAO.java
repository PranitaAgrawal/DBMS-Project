import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            String phnum = resultSet.getString("phnum");
            String card_num = resultSet.getString("card_num");
            String role = resultSet.getString("role");

             
            user users = new user(email,firstName, lastName, password, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, phnum, card_num,role);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, adress_street_num, adress_street,adress_city,adress_state,adress_zip_code,phnum, card_num,role) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getAdress_street_num());		
			preparedStatement.setString(6, users.getAdress_street());		
			preparedStatement.setString(7, users.getAdress_city());		
			preparedStatement.setString(8, users.getAdress_state());		
			preparedStatement.setString(9, users.getAdress_zip_code());		
			preparedStatement.setString(10, users.getPhnum());		
			preparedStatement.setString(11, users.getCard_num());	
			preparedStatement.setString(12, users.getRole());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set firstName=?, lastName =?,password = ?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, phnum=?, card_num =?, role=? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getAdress_street_num());		
		preparedStatement.setString(6, users.getAdress_street());		
		preparedStatement.setString(7, users.getAdress_city());		
		preparedStatement.setString(8, users.getAdress_state());		
		preparedStatement.setString(9, users.getAdress_zip_code());		
		preparedStatement.setString(10, users.getPhnum());		
		preparedStatement.setString(11, users.getCard_num());
		preparedStatement.setString(11, users.getRole());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            String phnum = resultSet.getString("phnum");
            String card_num = resultSet.getString("card_num");
            String role = resultSet.getString("role");
            user = new user(email, firstName, lastName, password,  adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code,phnum, card_num,role);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists project; ",
					        "create database project; ",
					        "use project; ",
					        "drop table if exists User; ",
					        ("CREATE TABLE if not exists User( " +
					        	"client_id INT AUTO_INCREMENT," +
					            "email VARCHAR(50) NOT NULL, " + 
					            "firstName VARCHAR(10) NOT NULL, " +
					            "lastName VARCHAR(10) NOT NULL, " +
					            "password VARCHAR(20) NOT NULL, " +					          
					            "adress_street_num VARCHAR(4) , "+ 
					            "adress_street VARCHAR(30) , "+ 
					            "adress_city VARCHAR(20)," + 
					            "adress_state VARCHAR(2),"+ 
					            "adress_zip_code VARCHAR(5),"+ 
					            "phnum VARCHAR(11),"+ 
					            "card_num VARCHAR(16),"+
					            "role VARCHAR(30) NOT NULL," +
					            "PRIMARY KEY (client_id) "+"); "), 
					            ("CREATE TABLE if not exists Tree( "+
					            		"tree_id INT auto_increment,"+
					            	    "size VARCHAR(50) NOT NULL, "+
					            	    "height VARCHAR(30) NOT NULL, "+
					            	    "location VARCHAR(30) NOT NULL,"+ 
					            	    "distance_from_house VARCHAR(20) NOT NULL,"+ 
					            	    "note VARCHAR(100) ,"+ 
					            	    "PRIMARY KEY (tree_id) "+");"),
					            ("CREATE TABLE if not exists Request( "+
					            		"req_id INT auto_increment,"+
					            		"tree_id INT, "+
					            		"client_id INT,"+
					            		"Status VARCHAR(30) NOT NULL, "+
					            		"note VARCHAR(100), "+
					            		"PRIMARY KEY (req_id),"+
					            		"FOREIGN KEY (tree_id) REFERENCES Tree(tree_id),"+
					            		"FOREIGN KEY (client_id) REFERENCES User(client_id)"+");"),
					            ("CREATE TABLE if not exists Quotes("+ 
					            		"quote_id INT auto_increment,"+ 
					            		"req_id INT,"+ 
					            		"price decimal NOT NULL,"+ 
					            		"time varchar(50) NOT NULL,"+ 
					            		"Status VARCHAR(30) NOT NULL,"+ 
					            		"note VARCHAR(100),"+ 
					            		"PRIMARY KEY (quote_id),"+ 
					            		"FOREIGN KEY (req_id) REFERENCES Request(req_id)"+");"),
					            ("CREATE TABLE if not exists Orders("+ 
					            		"	order_id INT auto_increment,"+ 
					            		"    req_id INT,"+ 
					            		"    Status VARCHAR(30) NOT NULL,"+ 
					            		"    PRIMARY KEY (order_id),"+ 
					            		"    FOREIGN KEY (req_id) REFERENCES Request(req_id)"+");"),
					            ("CREATE TABLE if not exists Bills("+ 
					            		"	bill_id INT auto_increment,"+ 
					            		"    order_id INT,"+
					            		 "    client_id INT,"+ 
					            		"    amount decimal NOT NULL,"+ 
					            		"    Status VARCHAR(30) NOT NULL, "+ 
					            		"    note VARCHAR(100), "+ 
					            		"    PRIMARY KEY (bill_id),"+ 
					            		"    FOREIGN KEY (client_id) REFERENCES User(client_id),"+ 
					            		"    FOREIGN KEY (order_id) REFERENCES Orders(order_id)"+"); ")
					        
        					};
        String[] TUPLES = {("insert into User(email, firstName, lastName, password, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, phnum, card_num, role)"+
    			"values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '1234', 'whatever street', 'detroit', 'MI', '48202', '7456789078', '5009675867893456', 'client'),"+
  			  "('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234',  '2468', 'yolos street', 'ides', 'CM', '24680','6786543567', '7689567456786754', 'client'),"+
  			"('angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '4680', 'egypt street', 'lolas', 'DT', '13579', '3452347895', '2345678901234567', 'client'),"+
  			 "('rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '1234', 'sign street', 'samo ne tu','MH', '09876','3137895674', '7896547889065436', 'client'),"+
  			 "('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '0981', 'snoop street', 'kojik', 'HW', '87654', '7856478907', '7689456723450987', 'client'),"+
			"('client1@example.com', 'John', 'Doe', 'password1', '123', 'Main Street', 'Cityville', 'CA', '12345', '12345678901', '1111222233334444', 'Client'),"+
				"('client2@example.com', 'Alice', 'Johnson', 'password2', '456', 'Elm Avenue', 'Townsburg', 'NY', '54321', '98765432109', '4444333322221111', 'Client'),"+
				"('client3@example.com', 'Bob', 'Smith', 'password3', '789', 'Oak Lane', 'Villagetown', 'TX', '98765', '55544433322', '1111999966668888', 'Client'),"+
				"('client4@example.com', 'Sarah', 'Williams', 'password4', '321', 'Cedar Road', 'Hamletville', 'WA', '43210', '12398765432', '9999888877776666', 'Client'),"+
				"('client5@example.com', 'Michael', 'Brown', 'password5', '567', 'Pine Street', 'Countryville', 'GA', '24680', '66655544433', '7777666655554444', 'Client'),"+
				"('davidsmith@example.com', 'David', 'Smith', 'davidspassword', '432', 'Maple Avenue', 'Citytown', 'CA', '12345', '12345678901', '1111222233334444', 'David Smith'),"+
  			 "('root', 'default', 'default','pass1234', '0000', 'Default', 'Default', 'De', '0', '00000','0000000000', 'root');"),
("INSERT INTO Tree (size, height, location, distance_from_house, note)"+
"VALUES ('Large', '20 feet', 'Backyard', '10 meters', 'Healthy tree with no issues'),"+
"('Medium', '15 feet', 'Front yard', '5 meters', 'Pruning required in spring'),"+
"('Small', '10 feet', 'Garden', '2 meters', 'Needs to be removed due to disease'),"+
"('Medium', '18 feet', 'Front yard', '7 meters', 'Trim branches near the house'),"+
"('Large', '25 feet', 'Backyard', '12 meters', 'No special notes'),"+
"('Small', '12 feet', 'Garden', '3 meters', 'Needs watering and fertilization'),"+
"('Medium', '17 feet', 'Front yard', '6 meters', 'Healthy but needs pruning'),"+
"('Large', '22 feet', 'Backyard', '11 meters', 'Insect infestation, needs treatment'),"+
"('Small', '11 feet', 'Garden', '4 meters', 'Needs support for weak branches'),"+
"('Large', '24 feet', 'Backyard', '13 meters', 'Healthy tree, no issues'),"+
"('Medium', '16 feet', 'Front yard', '6 meters', 'Pruning needed for shaping'),"+
"('Small', '9 feet', 'Garden', '2.5 meters', 'Needs regular care and watering'),"+
"('Large', '28 feet', 'Backyard', '14 meters', 'Healthy, but roots near the house'),"+
"('Medium', '20 feet', 'Front yard', '8 meters', 'Trimming for safety near the house'),"+
"('Medium', '19 feet', 'Front yard', '8 meters', 'Minor pest issue, needs attention');"),
("INSERT INTO Request (tree_id, client_id, Status, note)"+
"VALUES(1, 1, 'Pending', 'Interested in tree trimming services'),"+
"(2, 2, 'Pending', 'Requesting a quote for tree removal'),"+
"(3, 3, 'Pending', 'Looking for tree health assessment'),"+
"(4, 4, 'Pending', 'Requesting a quote for tree pruning'),"+
"(5, 5, 'Pending', 'Interested in tree cutting services'),"+
"(6, 6, 'Pending', 'Requesting a quote for tree trimming'),"+
"(7, 7, 'Pending', 'Requesting tree removal and stump grinding'),"+
"(8, 8, 'Pending', 'Interested in emergency tree services'),"+
"(9, 9, 'Pending', 'Looking for tree consultation and advice'),"+
"(10, 10, 'Pending', 'Requesting a quote for tree planting');"),
("INSERT INTO Quotes (req_id, price, time, Status, note)"+
"VALUES(1, 500.00, '2023-11-01 to 2023-11-15', 'Pending', 'Initial quote for tree trimming'),"+
"(2, 800.00, '2023-10-28 to 2023-11-10', 'Pending', 'Initial quote for tree removal'),"+
"(3, 200.00, '2023-11-05 to 2023-11-20', 'Pending', 'Initial quote for tree health assessment'),"+
"(4, 300.00, '2023-10-30 to 2023-11-12', 'Pending', 'Initial quote for tree pruning'),"+
"(5, 600.00, '2023-11-15 to 2023-11-30', 'Pending', 'Initial quote for tree cutting'),"+
"(6, 400.00, '2023-11-10 to 2023-11-25', 'Pending', 'Initial quote for tree trimming'),"+
"(7, 900.00, '2023-11-12 to 2023-11-27', 'Pending', 'Initial quote for tree removal and stump grinding'),"+
"(8, 750.00, '2023-11-02 to 2023-11-18', 'Pending', 'Initial quote for emergency tree services'),"+
"(9, 150.00, '2023-11-04 to 2023-11-19', 'Pending', 'Initial quote for tree consultation and advice'),"+
"(10, 350.00, '2023-11-08 to 2023-11-23', 'Pending', 'Initial quote for tree planting');"),
("INSERT INTO Orders (req_id, Status)"+
"VALUES (1, 'Completed'),"+
"(2, 'Completed'),"+
"(3, 'Completed'),"+
"(4, 'Completed'),"+
"(5, 'Completed'),"+
"(6, 'Completed'),"+
"(7, 'Completed'),"+
"(8, 'Completed'),"+
"(9, 'Completed'),"+
"(10, 'Completed');"),
("INSERT INTO Bills (order_id, client_id, amount, Status, note)"+
"VALUES(1, 1, 500.00, 'Pending', 'Initial bill for tree trimming services'),"+
"(2, 2, 800.00, 'Pending', 'Initial bill for tree removal services'),"+
"(3, 3, 200.00, 'Pending', 'Initial bill for tree health assessment'),"+
"(4, 4, 300.00, 'Pending', 'Initial bill for tree pruning services'),"+
"(5, 5, 600.00, 'Pending', 'Initial bill for tree cutting services'),"+
"(6, 6, 400.00, 'Pending', 'Initial bill for tree trimming services'),"+
"(7, 7, 900.00, 'Pending', 'Initial bill for tree removal and stump grinding services'),"+
"(8, 8, 750.00, 'Pending', 'Initial bill for emergency tree services'),"+
"(9, 9, 150.00, 'Pending', 'Initial bill for tree consultation and advice'),"+
"(10, 10, 350.00, 'Pending', 'Initial bill for tree planting services');")
		    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
