
public class bill {
 	
 	protected int bill_id;
 	protected int order_id;
 	protected int client_id;
 	protected String status;
 	
 	protected float amount;
 	protected String note;
 	protected String duedate;
 	protected String generateddate;

    public bill() {
    
    }
    
    //public requ(String email, String size, String height, String location, String distance, String treeNote, String reqNote) 
    //{
    	//this(email,size,height, location,  distance,  treeNote,  reqNote);
    	//this.email = email;
    //}
    public bill(int bill_id,int order_id,int client_id,  String status,float amount,String note,String duedate) 
    {
 
    	this.bill_id = bill_id;
    	this.order_id = order_id;
    	
    	this.status = status;
    	this.client_id = client_id;
    	this.amount = amount;
    	this.note =  note;
    	this.duedate = duedate;
    	
        
    }
    public bill(int bill_id,int order_id,int client_id,  String status,float amount,String note,String duedate, String generateddate) 
    {
 
    	this.bill_id = bill_id;
    	this.order_id = order_id;
    	
    	this.status = status;
    	this.client_id = client_id;
    	this.amount = amount;
    	this.note =  note;
    	this.duedate = duedate;
    	this.generateddate = generateddate;
    	
        
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    
    public int getOrder_id() {
        return order_id;
    }
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
    
    public int getClient_id() {
        return client_id;
    }
    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }
    
    public int getBill_id() {
        return bill_id;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getDuedate() {
        return duedate;
    }
    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
    public String getGenerateddate() {
        return generateddate;
    }
    public void setGenerateddate(String generateddate) {
        this.generateddate = generateddate;
    }
    
    
    
    
 

}


	    	
	    