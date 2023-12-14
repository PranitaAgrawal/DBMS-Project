import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	case "/req":
        		req(request,response);
        		break;
        	case "/tree":
        		tree(request,response);
        		break;
        	case "/reque":
        		reque(request, response);
        		break;
        	case "/listReq":
        		listReq(request,response);
        		break;
        	case "/listOrder":
        		listOrder(request,response);
        		break;
        	case "/listBill":
        		listBill(request,response);
        		break;
        	case "/pay":
        		pay(request, response);
        		break;
        	case "/payment":
        		payment(request, response);
        		break;
        	case "/quoteCreation":
        		quoteCreation(request,response);
        		break;
        	case "/listClientReq":
        		listClientReq(request,response);
        		break;
        	case "/listClientQuote":
        		listClientQuote(request,response);
        		break;
        	case "/listClientOrder":
        		listClientOrder(request,response);
        		break;
        	case "/listClientBill":
        		listClientBill(request,response);
        		break;
        	case "/negotiatebill":
        		negotiatebill(request,response);
        		break;
        	case "/billUpdate":
        		billUpdate(request,response);
        		break;
        	case "/negotiate":
        		negotiate(request,response);
        		break;
        	case "/accept":
        		accept(request,response);
        		break;
        	case "/reject":
        		reject(request,response);
        		break;
        	case "/generatebill":
        		generatebill(request,response);
        		break;
        	
        	 case "/listUser": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");
	        

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
	    	String query1 = userDAO.listingquery();
			request.setAttribute("listUser", userDAO.listAllUsers());
			request.setAttribute("query1", query1);
			String[] query2 = userDAO.easyclients();
			request.setAttribute("query2", query2);
			List<requ> query3 = userDAO.onetreequotes();
			request.setAttribute("query3", query3);
			String[] query4 = userDAO.prospective();
			request.setAttribute("query4", query4);
			List<requ> query5 = userDAO.highesttree();
			request.setAttribute("query5", query5);
			List<bill> query6 = userDAO.overduebills();
			request.setAttribute("query6", query6);
			String[] query7 = userDAO.badclients();
			request.setAttribute("query7", query7);
			String[] query8 = userDAO.goodclients();
			request.setAttribute("query8", query8);
			List<stats> query9 = userDAO.statistics();
			request.setAttribute("query9", query9);
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 HttpSession session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 if(email.equals("davidsmith@example.com")) {
					 HttpSession session = request.getSession();
					 session.setAttribute("username", email);
					 request.getRequestDispatcher("david.jsp").forward(request, response);
				 }
				 else {
					 HttpSession session = request.getSession();
					 session.setAttribute("username", email);
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
				 }
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	
	   	 	String adress_street_num = request.getParameter("adress_street_num"); 
	   	 	String adress_street = request.getParameter("adress_street"); 
	   	 	String adress_city = request.getParameter("adress_city"); 
	   	 	String adress_state = request.getParameter("adress_state"); 
	   	 	String adress_zip_code = request.getParameter("adress_zip_code");
	   	    String phnum = request.getParameter("phnum");
	   	    String card_num = request.getParameter("card_num");
	   	    String role = request.getParameter("role");
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(email,firstName, lastName, password,  adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, phnum, card_num,role);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	    
	    //request page redirecting from login page
	    
	    private void req(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	request.getRequestDispatcher("req.jsp").forward(request, response);
	    }  
	    private void tree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	    	String req_id = request.getParameter("req_id");
	   	 	String size = request.getParameter("size");
	   	 	String height = request.getParameter("height");
	   	    String location = request.getParameter("location"); 
	   	 	String distance = request.getParameter("distance"); 
	   	 	String note = request.getParameter("note");
	   	 	
	   	 	
	   	 	tree trees = new tree(email, req_id, size, height,location,distance, note);
	   	 	int req_id1 = userDAO.insertTree(trees);
	   	 request.setAttribute("req_id", req_id1);
	   	 request.getRequestDispatcher("reqs.jsp").forward(request, response);
	   	 	}
	    
	    
	    private void reque(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String size = request.getParameter("size");
	   	 	String height = request.getParameter("height");
	   	    String location = request.getParameter("location"); 
	   	 	String distance = request.getParameter("distance"); 
	   	 	String note = request.getParameter("note");
	   	 	
	   	 	
	   	 	requ requs = new requ(email, size, height,location,distance, note);
	   	 	int req_id = userDAO.insertReq(requs);
	   	 request.setAttribute("req_id", req_id);
	   	 request.getRequestDispatcher("reqs.jsp").forward(request, response);
	   	 	}
	    
	    private void listReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("listReq started: 00000000000000000000000000000000000");
	    	List<requ> listReqs = userDAO.listAllReq();
	    	System.out.println("printsize"+listReqs.size());
	    	for(int i = 0; i < listReqs.size(); i++) {
	            System.out.println(listReqs.get(i).client_id);
	            System.out.println(listReqs.get(i).req_id);
	            System.out.println(listReqs.get(i).status);
	            System.out.println(listReqs.get(i).price);
	            System.out.println(listReqs.get(i).startdate);
	            System.out.println(listReqs.get(i).enddate);
	            System.out.println(listReqs.get(i).dnote);
	            System.out.println(listReqs.get(i).note);
	            System.out.println(listReqs.get(i).size);
	            System.out.println(listReqs.get(i).height);
	            System.out.println(listReqs.get(i).location);
	            System.out.println(listReqs.get(i).distance);
	        }
	    	request.setAttribute("listReqs", listReqs);
	    	System.out.println(request.getAttribute("listReqs").getClass());
	    	
	        request.getRequestDispatcher("listreq.jsp").forward(request, response);     
	       
	     
	        
	        System.out.println("listReq closed: 1111111111111111111");
	        
	        }
	    private void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("listOrder started: 00000000000000000000000000000000000");
	    	List<order> listOrders = userDAO.listOrders();
	    	System.out.println("printsize"+listOrders.size());
	    	
	    	request.setAttribute("listOrders", listOrders);
	    	System.out.println(request.getAttribute("listOrders").getClass());
	    	
	        request.getRequestDispatcher("listorder.jsp").forward(request, response);     
	       
	     
	        
	        System.out.println("listOrder closed: 1111111111111111111");
	        
	        }
	    private void listBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("listBill started: 00000000000000000000000000000000000");
	    	List<bill> listBills = userDAO.listBills();
	    	
	    	
	    	request.setAttribute("listBills", listBills);
	    	System.out.println(request.getAttribute("listBills").getClass());
	    	
	        request.getRequestDispatcher("listbill.jsp").forward(request, response);     
	       
	     
	        
	        System.out.println("listBill closed: 1111111111111111111");
	        
	        }
	    
	    private void quoteCreation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("quoteCreation started: 00000000000000000000000000000000000");
	    	String[] req_ids = request.getParameterValues("req_id");
	    	String[] statuss = request.getParameterValues("status");
	    	String[] prices = request.getParameterValues("price");
	    	String[] startdates = request.getParameterValues("startdate");
	    	String[] enddates = request.getParameterValues("enddate");
	    	String[] dnotes = request.getParameterValues("dnote");
	    	
	    	
	    	
	    	
	    	userDAO.addQuote(req_ids,statuss,prices,startdates,enddates,dnotes);
	    	
	    	
	    	/*String price = request.getParameter("price");
	   	 	String startdate = request.getParameter("startdate");
	   	 	String enddate = request.getParameter("enddate");
	   	 	String dnote = request.getParameter("dnote");*/
	    	
	    	List<requ> listReqs = userDAO.listAllReq();
	    	request.setAttribute("listReqs", listReqs);
	    	System.out.println(request.getAttribute("listReqs").getClass());
	    	
	        request.getRequestDispatcher("listreq.jsp").forward(request, response);
	        
	        System.out.println("quotes closed: 1111111111111111111");
	        
	        }
	    private void billUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("Billcreation started: 00000000000000000000000000000000000");
	    	String[] bill_ids = request.getParameterValues("bill_id");
	    	String[] client_ids = request.getParameterValues("client_id");
	    	String[] notes = request.getParameterValues("note");
	    	String[] statuss = request.getParameterValues("status");
	    	String[] amounts = request.getParameterValues("amount");
	    	String[] duedates = request.getParameterValues("duedate");
	    	
	    	
	    	
	    	
	    	userDAO.billUp(bill_ids,client_ids,notes,statuss,amounts,duedates);
	    	List<bill> listBills = userDAO.listBills();
	    	
	    	
	    	request.setAttribute("listBills", listBills);
	    	System.out.println(request.getAttribute("listBills").getClass());
	    	
	        request.getRequestDispatcher("listbill.jsp").forward(request, response); 
	    	
	    	
	    	/*String price = request.getParameter("price");
	   	 	String startdate = request.getParameter("startdate");
	   	 	String enddate = request.getParameter("enddate");
	   	 	String dnote = request.getParameter("dnote");*/
	    	
	    	
	    	
	        
	        }
	    private void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("paybill");
	    	String client_id = request.getParameter("client_id");
	   	 	String bill_id = request.getParameter("bill_id");
	   	 	String amount = request.getParameter("amount");
	   	 	request.setAttribute("client_id", client_id);
	   	 	request.setAttribute("bill_id", bill_id);
	   	 	request.setAttribute("amount", amount);
	   	 	request.getRequestDispatcher("clientpay.jsp").forward(request, response);
	   	 	
	   	 	
	   	   
	    	
	    }
	    private void payment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("paybill payment");
	    	String client_id = request.getParameter("client_id");
	   	 	String bill_id = request.getParameter("bill_id");
	   	 	String amount = request.getParameter("amount");
	   	 	String creditcard = request.getParameter("creditcard");
	   	 	String expiry =request.getParameter("expiry");
	   	 	String cvv = request.getParameter("cvv");
	   	 	userDAO.paybill(client_id,bill_id,amount,creditcard,expiry,cvv);
	   	 	request.getRequestDispatcher("activitypage.jsp").forward(request, response);
	   	 	
	   	 	
	   	 	
	   	   
	    	
	    }
	    
	       
	    private void listClientReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("listReq started: 00000000000000000000000000000000000");
	    	HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			System.out.println("username "+username);
	    	List<requ> listReqs = userDAO.listClientReqs(username);
	    	request.setAttribute("listReqs", listReqs);
	    	System.out.println(request.getAttribute("listReqs").getClass());
	    	
	        request.getRequestDispatcher("listclientreq.jsp").forward(request, response);
	    	
	    }
	    private void listClientQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("listQuote started: 00000000000000000000000000000000000");
	    	HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			System.out.println("username "+username);
	    	List<requ> listQuotes = userDAO.listClientQuotes(username);
	    	request.setAttribute("listQuotes", listQuotes);
	    	System.out.println("Size of listQUote"+listQuotes.size());
	    	
	        request.getRequestDispatcher("listclientquote.jsp").forward(request, response);
	    	
	    }
	    private void listClientOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("listOrder started: 00000000000000000000000000000000000");
	    	HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			System.out.println("username "+username);
	    	List<order> listOrders = userDAO.listClientOrders(username);
	    	request.setAttribute("listOrders", listOrders);
	    	System.out.println("Size of listOrder"+listOrders.size());
	    	
	        request.getRequestDispatcher("listclientorder.jsp").forward(request, response);
	    	
	    }
	    private void listClientBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("listBill started: 00000000000000000000000000000000000");
	    	HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			System.out.println("username "+username);
	    	List<bill> listBills = userDAO.listClientBills(username);
	    	request.setAttribute("listBills", listBills);
	    	
	    	
	        request.getRequestDispatcher("listclientbill.jsp").forward(request, response);
	    	
	    }
	    private void negotiatebill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("Negotiate started: 00000000000000000000000000000000000");
	    	String client_id = request.getParameter("client_id");
	    	String bill_id = request.getParameter("bill_id");
	    	String note = request.getParameter("note");
	    	HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			List<bill> listBills = userDAO.updateNegotiateBill(client_id,bill_id, note);
			request.setAttribute("listBills", listBills);
			request.getRequestDispatcher("listclientbill.jsp").forward(request, response);
			
	    	
	    	
	    }
	    private void negotiate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("Negotiate started: 00000000000000000000000000000000000");
	    	String client_id = request.getParameter("client_id");
	    	String req_id = request.getParameter("req_id");
	    	String dnote = request.getParameter("dnote");
	    	HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			System.out.println("Client_id from quotes "+client_id);
			System.out.println("Req_id from quotes "+req_id);
			System.out.println("Note from quotes "+dnote);
			List<requ> listQuotes = userDAO.updateNegotiate(client_id,req_id,dnote);
			request.setAttribute("listQuotes", listQuotes);
			request.getRequestDispatcher("listclientquote.jsp").forward(request, response);
			
	    	
	    	
	    }
	    private void accept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("Accept started: 00000000000000000000000000000000000");
	 
	    	String req_id = request.getParameter("req_id");
	    	String client_id = request.getParameter("client_id");
	    	
	    	HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			
			System.out.println("Req_id from quotes "+req_id);
			
			List<requ> listQuotes = userDAO.acceptQuote(req_id,client_id,username);
			request.setAttribute("listQuotes", listQuotes);
			request.getRequestDispatcher("listclientquote.jsp").forward(request, response);
			
	    	
	    	
	    }
	    private void reject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("Reject started: 00000000000000000000000000000000000");
	 
	    	String req_id = request.getParameter("req_id");
	    	String client_id = request.getParameter("client_id");
	    	
	    	HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			
			System.out.println("Req_id from quotes "+req_id);
			
			List<requ> listQuotes = userDAO.rejectQuote(req_id,client_id,username);
			request.setAttribute("listQuotes", listQuotes);
			request.getRequestDispatcher("listclientquote.jsp").forward(request, response);
			
	    	
	    	
	    }
	    private void generatebill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("Bill Generation started: 00000000000000000000000000000000000");
	 
	    	String order_id = request.getParameter("order_id");
	    	String client_id = request.getParameter("client_id");
	    	String amount = request.getParameter("amount");
	    	
			
			
			
			userDAO.billGeneration(order_id,client_id,amount);
			request.getRequestDispatcher("david.jsp").forward(request, response);
			
			
	    	
	    	
	    }
	    
	   
	
	    

	     
        
	    
	    
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


