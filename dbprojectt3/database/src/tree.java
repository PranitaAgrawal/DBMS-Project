
public class tree {
 	protected String email;
 	protected String req_id;
    protected String size;
    protected String height;
    protected String location;
    protected String distance;
    protected String note;
    

    public tree() {
    
    }
    
    //public requ(String email, String size, String height, String location, String distance, String treeNote, String reqNote) 
    //{
    	//this(email,size,height, location,  distance,  treeNote,  reqNote);
    	//this.email = email;
    //}
    public tree(String email, String req_id, String size, String height, String location, String distance, String note ) 
    {
 
    	this.email=email;
    	this.req_id = req_id;
    	this.note= note;
    	this.size = size;
    	this.height = height;
        this.location = location;
        this.distance = distance;
        
    }
    
    
    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }
    
    public String getReq_id() {
        return req_id;
    }
    
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
  
    
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDistance() {
        return distance;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
 

}


	    	
	    