import java.io.FileNotFoundException;
import java.util.*;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/project?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    public String listingquery() throws SQLException{
    	//list big clients
    	String sql = "SELECT * FROM Orders";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int[][] ord = new int[100][3];
        int i=0;
        while(resultSet.next()) {
        	String status = resultSet.getString("status");
        	if(status.equals("Order done & bill generated")) {
        		ord[i][0]=resultSet.getInt("req_id");
        		ord[i][1]=resultSet.getInt("client_id");
        		ord[i][2]=1;
        		i=i+1;
        	}	
        }
        resultSet.close();
        String sql2 = "select * from Tree";
        ResultSet rs = statement.executeQuery(sql2);
        while(rs.next()) {
        	int a = rs.getInt("req_id");
        	int j=0;
        	while(j<i) {
        		if(a==ord[j][0]) {
        			ord[j][2]+=1;
        			break;
        		}
        		j=j+1;
        	}
        }
        int j=0;
        int m=0;
        int query1=0;
        while(j<i) {
        	if(ord[j][2]>m) {
        		m=ord[j][2];
        		query1=ord[j][1];
        	}
        	j=j+1;
        }
        System.out.println(query1);
        String sql4 = "select * from User where client_id = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql4);
        preparedStatement.setInt(1, 4);

        // Execute the query
        resultSet = preparedStatement.executeQuery();
        String fullName;
        String name = "";
        String lname ="";

        // Process the results
        while (resultSet.next()) {
            name = resultSet.getString("firstName");
            lname = resultSet.getString("lastName");
            
        }
        fullName = name + " " + lname;
        return fullName ;
        
        
    	
    	
    }
    public String[] easyclients() throws SQLException{
    	String sql = "SELECT distinct(client_id) FROM Requesthistory";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int[] ord = new int[100];
        int i=0;
        while(resultSet.next()) {       		
        		ord[i]=resultSet.getInt("client_id");
        		System.out.println("order"+ord[i]);
        		i=i+1;	
        }
        String sql1 = "SELECT distinct(client_id) FROM Request";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet rs = statement.executeQuery(sql1);
        int[] ords = new int[100];
        int j=0;
        int k=0;
        while(rs.next()) {
        	int a= rs.getInt("client_id");
        	j=0;
        	while(j<i) {
        		if(a==ord[j]) {
        			break;
        		}
        		j=j+1;
        	}
        	if(j==i) {
        		System.out.println(a);
        		ords[k]=a;
        		k=k+1;
        	}
        	
        }
        System.out.println("len"+k);
        String[] names =new String[k];
        
    	String sql4 = "select * from User";
    	statement = (Statement) connect.createStatement();
        ResultSet rs1 = statement.executeQuery(sql4);

        // Execute the query
        
        String fullName;
        String name = "";
        String lname ="";
        k=0;

        // Process the results
        while (rs1.next()) {
        	j=0;
        	while(j<i) {
        		int b= rs1.getInt("client_id");
        		if( ords[j]==b) {
        			name = rs1.getString("firstName");
                    lname = rs1.getString("lastName");
                    fullName = name + " " + lname;
                    names[k]=fullName;
                    k=k+1;
                    break;
        		}
        		j=j+1;
        	}
            
            
        }
        
        return names;
    	
    }
    public List<requ> onetreequotes() throws SQLException{
    	String sql = "SELECT distinct(req_id) FROM tree";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int[] ord = new int[100];
        int i=0;
        while(resultSet.next()) {       		
        		ord[i]=resultSet.getInt("req_id");
        		System.out.println("order"+ord[i]);
        		i=i+1;	
        }
        String sql1 = "SELECT * FROM Request";      
        int j=0;
        List<requ> listReq = new ArrayList<requ>();

        statement = (Statement) connect.createStatement();
        ResultSet rs = statement.executeQuery(sql1);
        while(rs.next()) {
        	int a = rs.getInt("req_id");
        	j=0;
        	while(j<i) {
        		if(a==ord[j]) {
        			break;
        		}
        		j=j+1;
        	}
        	if(j==i && rs.getString("Status").equals("Quote Accepted & Order Created")) {
        		System.out.println(a);
        		int client_id = rs.getInt("client_id");
        		int req_id = rs.getInt("req_id");
        		String status= rs.getString("status");
        		String note= rs.getString("note");
        		String size = rs.getString("size"); 
        		String height = rs.getString("height"); 
        		String location = rs.getString("location"); 
        		String distance = rs.getString("distance_from_house");
        		requ requs = new requ(client_id,req_id, status, note, size, height, location, distance);            
                listReq.add(requs);
        	}
        	
        }
        return listReq;
    }
    public String[] prospective() throws SQLException{
    	String sql = "select distinct(client_id) from Request where client_id not in (select  client_id from orders)";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int[] ord = new int[100];
        int i=0;
        while(resultSet.next()) {
        	ord[i]=resultSet.getInt("client_id");
        	i=i+1;
        }
        String[] names =new String[i];
        
    	String sql4 = "select * from User";
    	statement = (Statement) connect.createStatement();
        ResultSet rs1 = statement.executeQuery(sql4);

        // Execute the query
        
        String fullName;
        String name = "";
        String lname ="";
        int j=0;
        int k=0;

        // Process the results
        while (rs1.next()) {
        	j=0;
        	while(j<i) {
        		int b= rs1.getInt("client_id");
        		if( ord[j]==b) {
        			name = rs1.getString("firstName");
                    lname = rs1.getString("lastName");
                    fullName = name + " " + lname;
                    names[k]=fullName;
                    k=k+1;
                    break;
        		}
        		j=j+1;
        	}
            
            
        }
        
        return names;
    }
    public List<requ> highesttree() throws SQLException{
    	String sql = "select max(height) as height from tree \n"
    			+ "where req_id in ( select req_id from orders where status =\"Order done & bill generated\");";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String a ="";
        List<requ> listReq = new ArrayList<requ>();
        while(resultSet.next()) {
        	a = resultSet.getString("height");
        }
        String high =a;
        String sql1 = "select max(height) as height from request \n"
    			+ "where req_id in ( select req_id from orders where status =\"Order done & bill generated\");";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet rs = statement.executeQuery(sql1);
        String b ="";
        int c =0;
        while(rs.next()) {
        	b = rs.getString("height");
        }
        high =b;
        String sql2= "select * from Request where height = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
        preparedStatement.setString(1, high);
         
        ResultSet rs1 = preparedStatement.executeQuery();
        while(rs1.next()) {
        	int client_id = rs1.getInt("client_id");
    		int req_id = rs1.getInt("req_id");
    		String status= rs1.getString("status");
    		String note= rs1.getString("note");
    		String size = rs1.getString("size"); 
    		String height = rs1.getString("height"); 
    		String location = rs1.getString("location"); 
    		String distance = rs1.getString("distance_from_house");
    		requ requs = new requ(client_id,req_id, status, note, size, height, location, distance);            
            listReq.add(requs);
        }
        
        return listReq;
    
    }
    public List<bill> overduebills() throws SQLException{
    	String sql = "select * from bills where generateddate<\"2023-12-07\" and status!=\"Bill Settled\";";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<bill> listBill = new ArrayList<bill>();
        while(resultSet.next()) {
        	int bill_id = resultSet.getInt("bill_id");
            int order_id = resultSet.getInt("order_id");
            int client_id = resultSet.getInt("client_id");
            String status = resultSet.getString("status");
            float amount = resultSet.getFloat("amount");
            String note = resultSet.getString("note");
            String duedate = resultSet.getString("Duedate");
            String generateddate = resultSet.getString("generateddate");
     
            bill bills = new bill(bill_id,order_id,client_id, status,amount,note,duedate,generateddate);          
            listBill.add(bills);
        }
        return listBill;
        
    }
    public String[] badclients() throws SQLException{
    	String sql = "select distinct(client_id) from bills where client_id not in (select distinct(client_id) from Payment) and status!=\"Bill Settled\";";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        int[] ord = new int[100];
        int i=0;
        while(resultSet.next()) {
        	ord[i]=resultSet.getInt("client_id");
        	i=i+1;
        }
        String[] names =new String[i];
        
    	String sql4 = "select * from User";
    	statement = (Statement) connect.createStatement();
        ResultSet rs1 = statement.executeQuery(sql4);

        // Execute the query
        
        String fullName;
        String name = "";
        String lname ="";
        int j=0;
        int k=0;

        // Process the results
        while (rs1.next()) {
        	j=0;
        	while(j<i) {
        		int b= rs1.getInt("client_id");
        		if( ord[j]==b) {
        			name = rs1.getString("firstName");
                    lname = rs1.getString("lastName");
                    fullName = name + " " + lname;
                    names[k]=fullName;
                    k=k+1;
                    break;
        		}
        		j=j+1;
        	}
            
            
        }
        
        return names;
    }
    public String[] goodclients() throws SQLException{
    	String sql = "select distinct(client_id) from bills where status=\"Bill Settled\" and generateddate=\"2023-12-13\";";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        int[] ord = new int[100];
        int i=0;
        while(resultSet.next()) {
        	ord[i]=resultSet.getInt("client_id");
        	i=i+1;
        }
        String[] names =new String[i];
        
    	String sql4 = "select * from User";
    	statement = (Statement) connect.createStatement();
        ResultSet rs1 = statement.executeQuery(sql4);

        // Execute the query
        
        String fullName;
        String name = "";
        String lname ="";
        int j=0;
        int k=0;

        // Process the results
        while (rs1.next()) {
        	j=0;
        	while(j<i) {
        		int b= rs1.getInt("client_id");
        		if( ord[j]==b) {
        			name = rs1.getString("firstName");
                    lname = rs1.getString("lastName");
                    fullName = name + " " + lname;
                    names[k]=fullName;
                    k=k+1;
                    break;
        		}
        		j=j+1;
        	}
            
            
        }
        
        return names;
    }
    public List<stats> statistics() throws SQLException{
    	String sql = "select distinct(client_id) from orders";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int[] ord = new int[100];
        
        int i=0;
        while(resultSet.next()) {
        	ord[i]=resultSet.getInt("client_id");
        	
        	i=i+1;
        }
        int a=1;
        int j=0; 
        j=0;
        float[] amounts = new float[i];
        float b=0;
        while(j<i) {
        	b=0;
        	String sql2 = "select * from orders where status=\"Order done & bill generated\";";   
            statement = (Statement) connect.createStatement();
            ResultSet rs = statement.executeQuery(sql2);
            while(rs.next()) {
            	if(ord[j]==rs.getInt("client_id")) {
        			b=b+rs.getFloat("amount");
            	}
            }
            rs.close();
            amounts[j]=b;
            j=j+1;   
        }
        j=0;
        float[] dues = new float[i];
        b=0;
        while(j<i) {
        	b=0;
        	String sql3 = "select * from orders where status!=\"Order done & bill generated\";";   
            statement = (Statement) connect.createStatement();
            ResultSet rs1 = statement.executeQuery(sql3);
            while(rs1.next()) {
            	if(ord[j]==rs1.getInt("client_id")) {
        			b=b+rs1.getFloat("amount");
            	}
            }
            rs1.close();
            dues[j]=b;
            j=j+1;   
        }
        j=0;
        List<stats> stat = new ArrayList<stats>();
        while(j<i) {
        	stats s = new stats(ord[j],amounts[j],dues[j],1);          
            stat.add(s);
            j=j+1;
        	
        }
        return stat;
        
        
        
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
    public int getClientId(String email) throws SQLException {
    	int clientId = 0;
        String sql = "SELECT * FROM User";
         
        statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	
    
    	
    	while(resultSet.next())
    	{
    		
    		if(resultSet.getString("email").equals(email)){
    			clientId = resultSet.getInt("client_id");
    			break;
    		}		
    	}
    	return clientId;
            
    }
                 
    public int insertReq(requ requs) throws SQLException {
    	connect_func("root","root1234"); 
		int client_id = getClientId(requs.getEmail());
			
			
			
			String sql1 = "insert into Request(client_id, status,  note, size, height, location, distance_from_house) values (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,client_id);
			preparedStatement.setString(2, "new");
			preparedStatement.setString(3, requs.getNote());
			
			preparedStatement.setString(4, requs.getSize());
			preparedStatement.setString(5, requs.getHeight());
			preparedStatement.setString(6, requs.getLocation());
			preparedStatement.setString(7, requs.getDistance());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			int req_id = rs.getInt(1);
			System.out.println("Generated req_id: " + req_id);
			preparedStatement.close();
			return req_id;
			
    }
    public int insertTree(tree trees) throws SQLException {
    	connect_func("root","root1234"); 
		
			
			
			
			String sql1 = "insert into Tree(req_id, size, height, location, distance_from_house, note) values (?, ?, ?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
			preparedStatement.setInt(1,Integer.parseInt(trees.req_id));
			
			
			
			preparedStatement.setString(2, trees.getSize());
			preparedStatement.setString(3, trees.getHeight());
			preparedStatement.setString(4, trees.getLocation());
			preparedStatement.setString(5, trees.getDistance());
			preparedStatement.setString(6, trees.getNote());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			return Integer.parseInt(trees.req_id);
			
    }
    public List<requ> listAllReq() throws SQLException {
    	System.out.println("Hello,request");
    	List<requ> listReq = new ArrayList<requ>();        
        String sql = "SELECT * FROM Request";      
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int client_id = resultSet.getInt("client_id");
            int req_id = resultSet.getInt("req_id");
            String status = resultSet.getString("status");
            String startdate = "nd";
            String enddate = "nd";
            String dnote = "nd";
            float price = resultSet.getFloat("price");
            if(price!=0.0) {
            	startdate = resultSet.getString("startdate");
            	enddate = resultSet.getString("enddate");
                dnote = resultSet.getString("davidnote");
            	
            }
            
            String note= resultSet.getString("note");
            String size = resultSet.getString("size"); 
            String height = resultSet.getString("height"); 
            String location = resultSet.getString("location"); 
            String distance = resultSet.getString("distance_from_house"); 
            requ requs = new requ(client_id,req_id,status,price,startdate,enddate,dnote, note, size, height, location, distance);            
            listReq.add(requs);
        }        
        
        resultSet.close();
        disconnect();        
        return listReq;
    	
    	
    }
    public List<requ> addQuote(String[] req_ids,String[] statuss, String[] prices,String[] startdates,String[] enddates,String[] dnotes) throws SQLException {
    	System.out.println("Hello,quote");
    	//List<requ> listReq = new ArrayList<requ>();        
        
        connect_func("root","root1234");      
       
        
        connect_func();
        for (int i=0;i<req_ids.length;i++) {
        	float a = Float.parseFloat(prices[i]);
        	int b = Integer.parseInt(req_ids[i]);
        	String sql1 = "SELECT * FROM Request";  
    	    
  	      
    	    statement = (Statement) connect.createStatement();
    	    
    	    ResultSet resultSet = statement.executeQuery(sql1);
    	    
    	    while(resultSet.next()) {

    	        int client_id1 = resultSet.getInt("client_id");
    	        float price = resultSet.getFloat("price");
    	        
    	        
    	        int req_id1 = resultSet.getInt("req_id");
    	        String status = resultSet.getString("status");
    	        
    	       
    	        String startdate = resultSet.getString("startdate");
    	        String enddate = resultSet.getString("enddate");
    	        String dnote1 = resultSet.getString("davidnote");
    	        	
    	        
    	        String note= resultSet.getString("note");
    	        String size = resultSet.getString("size"); 
    	        String height = resultSet.getString("height"); 
    	        String location = resultSet.getString("location"); 
    	        String distance = resultSet.getString("distance_from_house");
    	        
    	        if(req_id1==b && status.equals("Negotiated by client")) {
    	        	System.out.println("Req ID1 "+b);
        	        System.out.println("Req ID2 "+req_id1);
        	        System.out.println("Status"+status);
    	        	String sql2 = "insert into Requesthistory(req_id, client_id, status,  note, size, height, distance_from_house, price, startdate, enddate,davidnote,convohead,location) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	            preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
    	            preparedStatement.setInt(1, req_id1);
    	            preparedStatement.setInt(2, client_id1);
    	            preparedStatement.setString(3, status);
    	            preparedStatement.setString(4, note);
    	            preparedStatement.setString(5, size);
    	            preparedStatement.setString(6, height);
    	            
    	            preparedStatement.setString(7, distance);
    	            preparedStatement.setFloat(8, price);
    	            preparedStatement.setString(9, startdate);
    	            preparedStatement.setString(10, enddate);
    	            preparedStatement.setString(11, dnote1);
    	            
    	            preparedStatement.setString(12, "Clients Negotiation");
    	            preparedStatement.setString(13, location);
    	            preparedStatement.executeUpdate();
    				preparedStatement.close();
    				
    				break;
    	        }
    	        
    	        	
    	        if(Float.parseFloat(prices[i])!=0 && status.equals("new")) {
    	        	String sql3 = "update Request set Status=?, price=?, startdate =?,enddate = ?,davidnote =? where req_id = ? and Status = ?";
    	            connect_func();
    	        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql3);
    	        	
    	        	
    	        	preparedStatement.setString(1, "Quote sent");
    	        	preparedStatement.setFloat(2, Float.parseFloat(prices[i]));
    	        	
    	    		preparedStatement.setString(3, startdates[i]);
    	    		preparedStatement.setString(4, enddates[i]);
    	    		preparedStatement.setString(5, dnotes[i]);
    	    		preparedStatement.setInt(6, b);
    	    		preparedStatement.setString(7, "new");
    	    		preparedStatement.executeUpdate();
    	    		preparedStatement.close();
    	        }
    	    }
        	
        	String sql = "update Request set Status=?, price=?, startdate =?,enddate = ?,davidnote =? where req_id = ? and Status = ?";
            connect_func();
        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        	
        	
        	preparedStatement.setString(1, "Quote sent");
        	preparedStatement.setFloat(2, a);
        	
    		preparedStatement.setString(3, startdates[i]);
    		preparedStatement.setString(4, enddates[i]);
    		preparedStatement.setString(5, dnotes[i]);
    		preparedStatement.setInt(6, b);
    		preparedStatement.setString(7, "Negotiated by client");
    		preparedStatement.executeUpdate();
    		preparedStatement.close();
    		
        	
        	
        }
        
        List<requ> listReq = new ArrayList<requ>(); 
        listReq = listAllReq();      
        return listReq;
    	
        
		
        
        
    }
    public void billUp(String[] bill_ids,String[] client_ids, String[] notes,String[] statuss,String[] amounts,String[] duedates) throws SQLException {
    	System.out.println("Hello,billup");
    	//List<requ> listReq = new ArrayList<requ>();        
        
        connect_func("root","root1234");      
       
        
        connect_func();
        for (int i=0;i<bill_ids.length;i++) {
        	int a = Integer.parseInt(bill_ids[i]);
        	int b = Integer.parseInt(client_ids[i]);
        	String sql1 = "SELECT * FROM Bills";  
    	    
  	      
    	    statement = (Statement) connect.createStatement();
    	    
    	    ResultSet resultSet = statement.executeQuery(sql1);
    	    
    	    while(resultSet.next()) {

    	        int client_id1 = resultSet.getInt("client_id");
    	        float amount = resultSet.getFloat("amount");
    	        int order_id1 = resultSet.getInt("order_id");
    	        
    	        int bill_id1 = resultSet.getInt("bill_id");
    	        String status = resultSet.getString("status");
    	        
    	       
    	        String duedate = resultSet.getString("duedate");
    	        
    	        String note = resultSet.getString("note");
    	        	
    	        
    	        
    	        
    	        if(bill_id1==a && status.equals("Negotiated by client")) {
    	        	System.out.println("Hello insret query");
    	        	String sql2 = "insert into BillsHistory(bill_id, order_id,client_id,amount, status,  note,Duedate,detail) values (?, ?, ?, ?, ?, ?, ?, ?)";
                    preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
                    preparedStatement.setInt(1, bill_id1);
                    preparedStatement.setInt(2, order_id1);
                    preparedStatement.setInt(3, client_id1);
                    preparedStatement.setFloat(4, amount);
                    
                    preparedStatement.setString(5, status);
                    preparedStatement.setString(6, note);
                    
                    
                    preparedStatement.setString(7, duedate);
                    
                    preparedStatement.setString(8, "Clients Negotiation");
                    
                    preparedStatement.executeUpdate();
        			preparedStatement.close();
                    
    				
    				break;
    	        }
    	        
    	    }
    	    String sql = "update Bills set Status=?,note =?, amount=? where bill_id = ? and status = ?";
            connect_func();
        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        	
        	
        	preparedStatement.setString(1, "Bill Updated");
        	preparedStatement.setString(2, notes[i]);
        	
    		preparedStatement.setFloat(3, Float.parseFloat(amounts[i]));
    		preparedStatement.setInt(4, a);
    		preparedStatement.setString(5,"Negotiated by Client");
    		
    		
    		preparedStatement.executeUpdate();
    		preparedStatement.close();
        }
    	
        
		
        
        
    }
    public void paybill(String client_id, String bill_id, String amount, String creditcard, String expiry, String cvv) throws SQLException{
    	connect_func("root","root1234"); 
    	 System.out.println("Payment started");
    	 System.out.println(Integer.parseInt(client_id));
    	 
    	String sql2 = "insert into Payment(client_id,bill_id,amount, creditcard,expiry,cvv,paymentdate) values (?, ?, ?, ?, ?, ?,?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
       
        preparedStatement.setInt(1, Integer.parseInt(client_id));
        preparedStatement.setInt(2, Integer.parseInt(bill_id));

        preparedStatement.setFloat(3, Float.parseFloat(amount));
        
        preparedStatement.setString(4, creditcard);
        preparedStatement.setString(5, expiry);
        
        
        preparedStatement.setInt(6, Integer.parseInt(cvv));
        preparedStatement.setString(7, "2023-12-13");
        
        preparedStatement.executeUpdate();
		preparedStatement.close();
		String sql = "update Bills set Status=? where bill_id = ?";
        
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, "Bill Settled");
    	preparedStatement.setInt(2, Integer.parseInt(bill_id));
    	preparedStatement.executeUpdate();
    	preparedStatement.close();
		
    	
    }
    public List<order> listOrders() throws SQLException {
    	System.out.println("Hello,Order");
    	List<order> listOrder = new ArrayList<order>();        
        String sql = "SELECT * FROM Orders";  
        
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        
        
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	
            int client_id = resultSet.getInt("client_id");
            int req_id = resultSet.getInt("req_id");
            String status = resultSet.getString("status");
            int order_id = resultSet.getInt("order_id");
            float amount = resultSet.getFloat("amount");
            order orders = new order(order_id,req_id,status,client_id,amount);          
            listOrder.add(orders);
            
        }        
        
        resultSet.close();
        disconnect(); 
        System.out.println("SIze of list"+listOrder.size());
        return listOrder;
    	
    	
    }
    public List<bill> listBills() throws SQLException {
    	System.out.println("Hello,Bill");
    	List<bill> listBill = new ArrayList<bill>();        
        String sql = "SELECT * FROM Bills";  
        
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        
        
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	
            int bill_id = resultSet.getInt("bill_id");
            int order_id = resultSet.getInt("order_id");
            int client_id = resultSet.getInt("client_id");
            String status = resultSet.getString("status");
            float amount = resultSet.getFloat("amount");
            String note = resultSet.getString("note");
            String duedate = resultSet.getString("Duedate");
            bill bills = new bill(bill_id,order_id,client_id, status,amount,note,duedate);          
            listBill.add(bills);
            
        }        
        
        resultSet.close();
        disconnect(); 
        
        return listBill;
    	
    	
    }
    public List<requ> listClientReqs(String username) throws SQLException {
    	System.out.println("Hello,request");
    	List<requ> listReq = new ArrayList<requ>();        
        String sql = "SELECT * FROM Request";  
        String sql1 = "SELECT * FROM User";
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        
        ResultSet resultSet1 = statement.executeQuery(sql1);
        int client_id1 = 0;
        while(resultSet1.next()) {
        	if(resultSet1.getString("email").equals(username)) {
        		client_id1 = resultSet1.getInt("client_id");
        	}
        }
        System.out.println("client "+client_id1);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	
            int client_id = resultSet.getInt("client_id");
            if(client_id == client_id1) {
            int req_id = resultSet.getInt("req_id");
            String status = resultSet.getString("status");
            
            
            String note= resultSet.getString("note");
            String size = resultSet.getString("size"); 
            String height = resultSet.getString("height"); 
            String location = resultSet.getString("location"); 
            String distance = resultSet.getString("distance_from_house"); 
            requ requs = new requ(client_id,req_id,status, note, size, height, location, distance);            
            listReq.add(requs);
            }
        }        
        resultSet1.close();
        resultSet.close();
        disconnect();        
        return listReq;
    	
    	
    }
    public List<requ> listClientQuotes(String username) throws SQLException {
    	System.out.println("Hello,Quote");
    	List<requ> listQuote = new ArrayList<requ>();        
        String sql = "SELECT * FROM Request";  
        String sql1 = "SELECT * FROM User";
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        
        ResultSet resultSet1 = statement.executeQuery(sql1);
        int client_id1 = 0;
        while(resultSet1.next()) {
        	if(resultSet1.getString("email").equals(username)) {
        		client_id1 = resultSet1.getInt("client_id");
        	}
        }
        System.out.println("client "+client_id1);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	
            int client_id = resultSet.getInt("client_id");
            float price = resultSet.getFloat("price");
            System.out.println("price "+price);
            if(client_id == client_id1 && price!=0.0) {
            int req_id = resultSet.getInt("req_id");
            String status = resultSet.getString("status");
            
           
            String startdate = resultSet.getString("startdate");
            String enddate = resultSet.getString("enddate");
            String dnote = resultSet.getString("davidnote");
            	
            
            
            String note= resultSet.getString("note");
            String size = resultSet.getString("size"); 
            String height = resultSet.getString("height"); 
            String location = resultSet.getString("location"); 
            String distance = resultSet.getString("distance_from_house"); 
            requ requs = new requ(client_id,req_id,status,price,startdate,enddate,dnote, note, size, height, location, distance);          
            listQuote.add(requs);
            }
        }        
        resultSet1.close();
        resultSet.close();
        disconnect(); 
        System.out.println("SIze of list"+listQuote.size());
        return listQuote;
    	
    	
    }
    public List<order> listClientOrders(String username) throws SQLException {
    	System.out.println("Hello,Quote");
    	List<order> listOrder = new ArrayList<order>();        
        String sql = "SELECT * FROM Orders";  
        String sql1 = "SELECT * FROM User";
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        
        ResultSet resultSet1 = statement.executeQuery(sql1);
        int client_id1 = 0;
        while(resultSet1.next()) {
        	if(resultSet1.getString("email").equals(username)) {
        		client_id1 = resultSet1.getInt("client_id");
        	}
        }
        System.out.println("client "+client_id1);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	
            int client_id = resultSet.getInt("client_id");
            
            if(client_id == client_id1) {
            int req_id = resultSet.getInt("req_id");
            String status = resultSet.getString("status");
            int order_id = resultSet.getInt("order_id");
            Float amount = resultSet.getFloat("amount");
            
           
           
            order orders = new order(order_id,req_id,status,client_id,amount);          
            listOrder.add(orders);
            }
        }        
        resultSet1.close();
        resultSet.close();
        disconnect(); 
        System.out.println("SIze of list"+listOrder.size());
        return listOrder;
    	
    	
    }
    public List<bill> listClientBills(String username) throws SQLException {
    	System.out.println("Hello,Bill");
    	List<bill> listBill = new ArrayList<bill>();        
        String sql = "SELECT * FROM Bills";  
        String sql1 = "SELECT * FROM User";
        connect_func("root","root1234");      
        statement = (Statement) connect.createStatement();
        
        ResultSet resultSet1 = statement.executeQuery(sql1);
        int client_id1 = 0;
        while(resultSet1.next()) {
        	if(resultSet1.getString("email").equals(username)) {
        		client_id1 = resultSet1.getInt("client_id");
        	}
        }
        System.out.println("client "+client_id1);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	
            int client_id = resultSet.getInt("client_id");
            
            if(client_id == client_id1) {
            int bill_id = resultSet.getInt("bill_id");
            String status = resultSet.getString("status");
            int order_id = resultSet.getInt("order_id");
            Float amount = resultSet.getFloat("amount");
            String note = resultSet.getString("note");
            String duedate = resultSet.getString("Duedate");
            
           
           
            bill bills = new bill(bill_id,order_id,client_id,status,amount,note,duedate);          
            listBill.add(bills);
            }
        }        
        resultSet1.close();
        resultSet.close();
        disconnect(); 
       
        return listBill;
    	
    	
    }
    public List<requ> updateNegotiate(String client_id, String req_id,String dnote) throws SQLException {
    	System.out.println("Hello,updatequote");
    	//List<requ> listReq = new ArrayList<requ>();        
        
        connect_func("root","root1234");      
       
        
        connect_func();
        
        int a = Integer.parseInt(client_id);
        int b = Integer.parseInt(req_id);
        String sql1 = "SELECT * FROM Request";  
        
        
        statement = (Statement) connect.createStatement();
        
        ResultSet resultSet = statement.executeQuery(sql1);
      
        
        
        while (resultSet.next()) {
        	
            int client_id1 = resultSet.getInt("client_id");
            float price = resultSet.getFloat("price");
            System.out.println("price "+price);
            if(client_id1 == a && price!=0.0) {
            int req_id1 = resultSet.getInt("req_id");
            String status = resultSet.getString("status");
            
           
            String startdate = resultSet.getString("startdate");
            String enddate = resultSet.getString("enddate");
            String dnote1 = resultSet.getString("davidnote");
            	
            
            String note= resultSet.getString("note");
            String size = resultSet.getString("size"); 
            String height = resultSet.getString("height"); 
            String location = resultSet.getString("location"); 
            String distance = resultSet.getString("distance_from_house"); 
            if(req_id1==b) {
            	String sql2 = "insert into Requesthistory(req_id, client_id, status,  note, size, height, distance_from_house, price, startdate, enddate,davidnote,convohead,location) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
                preparedStatement.setInt(1, req_id1);
                preparedStatement.setInt(2, client_id1);
                preparedStatement.setString(3, status);
                preparedStatement.setString(4, note);
                preparedStatement.setString(5, size);
                preparedStatement.setString(6, height);
                
                preparedStatement.setString(7, distance);
                preparedStatement.setFloat(8, price);
                preparedStatement.setString(9, startdate);
                preparedStatement.setString(10, enddate);
                preparedStatement.setString(11, dnote);
                
                preparedStatement.setString(12, "Davids quote");
                preparedStatement.setString(13, location);
                preparedStatement.executeUpdate();
    			preparedStatement.close();
                
            
            }
            
            
            
            }
        }    
        resultSet.close();
        	
        	
        String sql = "update Request set Status=?,davidnote =? where req_id = ?";
            connect_func();
        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        	
        	
        	preparedStatement.setString(1, "Negotiated by client");
        	preparedStatement.setString(2, dnote);
        	
    		preparedStatement.setInt(3, b);
    		
    		preparedStatement.executeUpdate();
    		preparedStatement.close();
        	
        	
        
    	List<requ> listQuote = new ArrayList<requ>();
    	String sql2 = "SELECT * FROM Request";  
    
      
    statement = (Statement) connect.createStatement();
    
    ResultSet resultSet1 = statement.executeQuery(sql2);
  
    
    
    while (resultSet1.next()) {
    	
        int client_id1 = resultSet1.getInt("client_id");
        float price = resultSet1.getFloat("price");
        System.out.println("price "+price);
        if(client_id1 == a && price!=0.0) {
        int req_id1 = resultSet1.getInt("req_id");
        String status = resultSet1.getString("status");
        
       
        String startdate = resultSet1.getString("startdate");
        String enddate = resultSet1.getString("enddate");
        String dnote1 = resultSet1.getString("davidnote");
        	
        
        String note= resultSet1.getString("note");
        String size = resultSet1.getString("size"); 
        String height = resultSet1.getString("height"); 
        String location = resultSet1.getString("location"); 
        String distance = resultSet1.getString("distance_from_house"); 
        requ requs = new requ(client_id1,req_id1,status,price,startdate,enddate,dnote1, note, size, height, location, distance);          
        listQuote.add(requs);
        
        
        
        
        }
    }        
    
    resultSet1.close();
    disconnect(); 
    System.out.println("SIze of list"+listQuote.size());
    return listQuote;
    	
        
		
        
        
    }
    public List<bill> updateNegotiateBill(String client_id, String bill_id,String note) throws SQLException {
    	System.out.println("Hello,updateBill");
    	//List<requ> listReq = new ArrayList<requ>();        
        
        connect_func("root","root1234");      
       
        
        connect_func();
        
        int a = Integer.parseInt(client_id);
        int b = Integer.parseInt(bill_id);
        String sql1 = "SELECT * FROM Bills";  
        
        
        statement = (Statement) connect.createStatement();
        
        ResultSet resultSet = statement.executeQuery(sql1);
      
        
        
        while (resultSet.next()) {
        	
            int client_id1 = resultSet.getInt("client_id");
            float amount = resultSet.getFloat("amount");
            int bill_id1 = resultSet.getInt("bill_id");
            if(client_id1 == a && bill_id1 == b) {
            
            String status = resultSet.getString("status");
            int order_id = resultSet.getInt("order_id");
            
           
            
            String note1 = resultSet.getString("note");
            	
            
             
            String duedate = resultSet.getString("Duedate"); 
            if(bill_id1==b) {
            	String sql2 = "insert into BillsHistory(bill_id, order_id,client_id,amount, status,  note,Duedate,detail) values (?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
                preparedStatement.setInt(1, bill_id1);
                preparedStatement.setInt(2, order_id);
                preparedStatement.setInt(3, client_id1);
                preparedStatement.setFloat(4, amount);
                
                preparedStatement.setString(5, status);
                preparedStatement.setString(6, note1);
                
                
                preparedStatement.setString(7, duedate);
                
                preparedStatement.setString(8, "Davids bill");
                
                preparedStatement.executeUpdate();
    			preparedStatement.close();
                
            
            }
            
            
            
            }
        }    
        resultSet.close();
        	
        	
        String sql = "update Bills set Status=?,note =? where bill_id = ?";
            connect_func();
        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        	
        	
        	preparedStatement.setString(1, "Negotiated by client");
        	preparedStatement.setString(2, note);
        	
    		preparedStatement.setInt(3, b);
    		
    		preparedStatement.executeUpdate();
    		preparedStatement.close();
        	
        	
        
    	List<bill> listBills = new ArrayList<bill>();
    	String sql2 = "SELECT * FROM Bills";  
    
      
    statement = (Statement) connect.createStatement();
    
    ResultSet resultSet1 = statement.executeQuery(sql2);
  
    
    
    while (resultSet1.next()) {
    	int bill_id1 = resultSet1.getInt("bill_id");
    	int order_id1 = resultSet1.getInt("order_id");
        int client_id1 = resultSet1.getInt("client_id");
        float amount = resultSet1.getFloat("amount");
        String status =resultSet1.getString("status");
        String note1 =resultSet1.getString("note");
        String duedate =resultSet1.getString("duedate");
        if(client_id1 == a) {
        
        	
        
        
        bill bills = new bill(bill_id1,order_id1,client_id1,status,amount, note1,duedate);          
        listBills.add(bills);
        
        
        
        
        }
    }        
    
    resultSet1.close();
    disconnect(); 
    
    return listBills;
    	
        
		
        
        
    }
    public List<requ> acceptQuote(String req_id,String client_id,String username) throws SQLException {
    	System.out.println("Hello,quote");
    	//List<requ> listReq = new ArrayList<requ>();        
        
        connect_func("root","root1234");
        
       
        
        connect_func();
        int b = Integer.parseInt(req_id);
        int a = Integer.parseInt(client_id);
        String sql2 = "select price from Request where req_id=?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
        preparedStatement.setInt(1, b);
        ResultSet rs = preparedStatement.executeQuery();
        float c =0;
        while(rs.next()) {
        	c = rs.getFloat("price");
        	
        }
        String sql1 = "insert into Orders( req_id,  status, client_id,amount) values (?, ?, ?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setInt(1,b);
		preparedStatement.setString(2, "Pending");
		preparedStatement.setInt(3, a);
		preparedStatement.setFloat(4, c);
		preparedStatement.executeUpdate();
		preparedStatement.close();
        	
        	
        String sql = "update Request set Status=? where req_id = ?";
            connect_func();
        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        	
        	
        	preparedStatement.setString(1, "Quote Accepted & Order Created");
        	preparedStatement.setFloat(2, b);
        	
    		
    		preparedStatement.executeUpdate();
    		preparedStatement.close();
        	
        	
        
        
        List<requ> listReq = new ArrayList<requ>(); 
        listReq = listClientQuotes(username);      
        return listReq;
    	
        
		
        
        
    }
    public List<requ> rejectQuote(String req_id,String client_id,String username) throws SQLException {
    	System.out.println("Hello,quote");
    	//List<requ> listReq = new ArrayList<requ>();        
        
        connect_func("root","root1234");      
       
        
        
        int b = Integer.parseInt(req_id);
        int a = Integer.parseInt(client_id);
        
        String sql = "update Request set Status=? where req_id = ?";
            connect_func();
        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        	
        	
        	preparedStatement.setString(1, "Quote Rejected and closed");
        	preparedStatement.setFloat(2, b);
        	
    		
    		preparedStatement.executeUpdate();
    		preparedStatement.close();
        	
        	
        
        
        List<requ> listReq = new ArrayList<requ>(); 
        listReq = listClientQuotes(username);      
        return listReq;
    	
        
		
        
        
    }
    public List<requ> placeOrders(String client_id, String req_id) throws SQLException {
    	System.out.println("Hello,order");
    	//List<requ> listReq = new ArrayList<requ>();        
        
        connect_func("root","root1234"); 
        int a = Integer.parseInt(client_id);
        int b = Integer.parseInt(req_id);
        String sql1 = "insert into Orders(req_id,  status, client_id) values (?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setInt(1,b);
		preparedStatement.setString(2, "Pending");
		preparedStatement.setInt(3, a);
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
        	
        String sql = "update Request set Status=? where req_id = ?";
            connect_func();
        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        	
        	
        	preparedStatement.setString(1, "Ordered Placed");
        	preparedStatement.setInt(2, b);
        	
    		
    		preparedStatement.executeUpdate();
    		preparedStatement.close();
        	
        	
        
        
        List<requ> listReq = new ArrayList<requ>(); 
        listReq = listAllReq();      
        return listReq;
    	
        
		
        
        
    }
    public void billGeneration(String order_id,String client_id, String amount) throws SQLException {
    	System.out.println("Hello,bill");
    	//List<requ> listReq = new ArrayList<requ>();        
        
        connect_func("root","root1234");      
       
        
        
        int a = Integer.parseInt(order_id);
        int b = Integer.parseInt(client_id);
        float c = Float.parseFloat(amount);
        
        
        
        String sql1 = "insert into Bills(order_id,   client_id, status, amount, Duedate,generateddate) values (?, ?, ?, ?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
		preparedStatement.setInt(1,a);
		preparedStatement.setInt(2, b);
		preparedStatement.setString(3, "Bill Created");
		preparedStatement.setFloat(4, c);
		preparedStatement.setString(5, "2023-12-31");
		preparedStatement.setString(6, "2023-12-13");
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
		String sql = "update Orders set Status=? where order_id = ?";
        
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	
    	
    	preparedStatement.setString(1, "Order done & bill generated");
    	preparedStatement.setFloat(2, a);
    	
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
    	
        	
        	
        
        
       
        
		
        
        
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
