package servers.webserver;

public class Message {

	public static final int REQUEST = 0;
	public static final int RESPONSE = 1;
	
	public static final int OK = 100;
	public static final int WARNING = 101;
	public static final int ERROR = 102;
	
	private String requestMsg;
	private String responseMsg;
	private int responseMsgType;
	
	public Message(String requestMsg) {
		// TODO Auto-generated constructor stub
		this.requestMsg = requestMsg;
	}
	
	public Message(String responseMsg, int msgType) {
		// TODO Auto-generated constructor stub
		this.responseMsg = responseMsg;
		this.responseMsgType = msgType;
	}
	
	public String getRequestMsg() {
		return requestMsg;
	}
	
	public String getResponseMsg() {
		return responseMsg;
	}

	public int getResponseMsgType() {
		return responseMsgType;
	}
	
	
}
