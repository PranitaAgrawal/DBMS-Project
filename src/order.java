
public class order {
 	
 	protected int client_id;
 	protected int req_id;
 	protected String status;
 	protected int order_id;
 	protected float amount;
    

    public order() {
    
    }
    
    //public requ(String email, String size, String height, String location, String distance, String treeNote, String reqNote) 
    //{
    	//this(email,size,height, location,  distance,  treeNote,  reqNote);
    	//this.email = email;
    //}
    public order(int order_id, int req_id, String status,int client_id, float amount) 
    {
 
    	this.order_id = order_id;
    	this.req_id = req_id;
    	this.status = status;
    	this.client_id = client_id;
    	this.amount = amount;
    	
        
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
    public void setReq_id(int req_id) {
        this.req_id = req_id;
    }
    
    public int getReq_id() {
        return req_id;
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
    
    
    
    
 

}


	    	
	    