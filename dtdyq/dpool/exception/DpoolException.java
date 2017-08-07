package dtdyq.dpool.exception;

public class DpoolException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DpoolException(String msg){
		super(msg);
		this.msg = msg;
	}
	
}
