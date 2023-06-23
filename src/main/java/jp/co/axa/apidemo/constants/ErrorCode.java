package jp.co.axa.apidemo.constants;

 public enum ErrorCode {

	  UNAUTHORIZED (1, "Unauthorized User"),
	  MISSING_DATA (2, "Data is missing"),
	  INVALID_DATA (3,"Data is invalid"),
	  SUCCESS (4,"SUCCESS"),
	  NOT_FOUND(5,"NOT_FOUND"),
	  NO_ID(6,"Invalid id"),
	  NO_DATA(6,"No employee data found");
		

	    private final int code;
	    private final String description;

	    private ErrorCode(int code, String description) {
	        this.code = code;
	        this.description = description;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public int getCode() {
	        return code;
	    }

	    @Override
	    public String toString() {
	        return code + ": " + description;
	    }

 
 }

