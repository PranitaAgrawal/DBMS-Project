
public class stats {
 	
 	protected int client_id;
 	protected float amount;
 	protected float dueamount;
 	protected int tree;
 	
 	

    public stats() {
    
    }
    
    //public requ(String email, String size, String height, String location, String distance, String treeNote, String reqNote) 
    //{
    	//this(email,size,height, location,  distance,  treeNote,  reqNote);
    	//this.email = email;
    //}
    public stats(int client_id,float amount, float dueamount,int tree) 
    {
 
    	this.client_id = client_id;
    	this.amount = amount;
    	
    	this.dueamount = dueamount;
    	this.tree = tree;
    	
        
    }
    
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
    
    public int getClient_id() {
        return client_id;
    }
    
    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    public float getAmount() {
        return amount;
    }
    
    
    public float getDueamount() {
        return amount;
    }
    public void setDueamount(float dueamount) {
        this.dueamount = dueamount;
    }
    public int getTree() {
        return tree;
    }
    public void setTree(int tree) {
        this.tree = tree;
    }
    
    
    
    
    
 

}


	    	
	    