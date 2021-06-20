package filesystem;

public class VirtualAccount {
	String AccountName = "DefaultUser";
	String AccountType;
	static final String Adminstrator = "admin";
	static final String User = "user";
	static final String SYSTEM = "SYSTEM";
	public static void main(String[] args) {
	}
	public VirtualAccount(String username,String UserType) {
		AccountType = UserType;
		AccountName = username;
	}
	

}
