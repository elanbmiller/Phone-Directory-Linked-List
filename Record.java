public class Record {

	protected String fname;
	protected String lname;
	protected String num;
	
	public Record(String fname, String lname, String num) {
		this.fname = fname;
		this.lname = lname;
		this.num = num;
	}
	
	public String toString(){
		return  fname + " " + lname + " " + num; 
	}
	
//-----------------------------------------------------------------------------
//Sets first name of a record
//precondition: user supplies first name when calling the method
//postcondition: fname is set to the first name supplied by user
//-----------------------------------------------------------------------------
	public void setFname(String first){
		this.fname = first;
	}
	
//-----------------------------------------------------------------------------
//Sets last name of a record
//precondition: user supplies last name when calling the method
//postcondition: lname is set to the last name supplied by user
//-----------------------------------------------------------------------------
	public void setLname(String last){
		this.lname = last;
	}
	
//-----------------------------------------------------------------------------
//Sets phone number of a record
//precondition: user supplies a phone number when calling the method
//postcondition: num is set to the number supplied by user
//-----------------------------------------------------------------------------
	public void setNum(String number){
		this.num = number;
	}
	
	
//-----------------------------------------------------------------------------
//Tests if a record equals another record (object) called other (used in phonedir.java case s when traversing list
//looking for a matching record to what user inputted
//precondition: user supplies other when calling the method
//postcondition: returns true if they're equal and false otherwise
//-----------------------------------------------------------------------------
	
	@Override
	public boolean equals(Object other){
		
		if (null == other) return false;
		if (this == other) return true;
		
		//tests if Object is a record
		if (! (other instanceof Record)) return false;
		
		//if it is a record, cast as record
		Record r = (Record) other;
		return  r.fname.equals(this.fname) && r.lname.equals(this.lname);
	}


}
