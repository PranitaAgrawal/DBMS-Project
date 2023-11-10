
public class requ {
 	protected String email;
 	protected int client_id;
 	protected int req_id;
 	protected float price;
    protected String startdate;
    protected String enddate;
    protected String dnote;
 	protected String status;
    protected String size;
    protected String height;
    protected String location;
    protected String distance;
    protected String note;
    

    public requ() {
    
    }
    
    //public requ(String email, String size, String height, String location, String distance, String treeNote, String reqNote) 
    //{
    	//this(email,size,height, location,  distance,  treeNote,  reqNote);
    	//this.email = email;
    //}
    public requ(int client_id, int req_id, String status,float price, String startdate, String enddate, String dnote, String note, String size, String height, String location, String distance ) 
    {
 
    	this.client_id = client_id;
    	this.req_id = req_id;
    	this.status = status;
    	this.price = price;
    	this.startdate=startdate;
    	this.enddate=enddate;
    	this.dnote=dnote;
    	this.note= note;
    	this.size = size;
    	this.height = height;
        this.location = location;
        this.distance = distance;
        
    }
    
    public requ(int client_id, int req_id, String status, String note, String size, String height, String location, String distance ) 
    {
    	this.client_id = client_id;
    	this.req_id = req_id;
    	this.status = status;
    	this.note= note;
    	this.size = size;
    	this.height = height;
        this.location = location;
        this.distance = distance;
        
    }
    public requ(String email, String size, String height, String location, String distance, String note) 
    {
    	this.email = email;
    	this.size = size;
    	this.height = height;
        this.location = location;
        this.distance = distance;
        this.note= note;
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
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getStartdate() {
        return startdate;
    }
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
    public String getEnddate() {
        return enddate;
    }
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    public String getDnote() {
        return dnote;
    }
    public void setDnote(String dnote) {
        this.dnote = dnote;
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


	    	
	    