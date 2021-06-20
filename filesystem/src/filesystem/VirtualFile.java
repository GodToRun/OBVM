package filesystem;
import java.util.*;
public class VirtualFile {
	public String data = "";
	public String name;
	public String Format;
	public String key = null;
	public VirtualAccount user = null;
	String permission = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public VirtualFile(VirtualDisk disk,String Name) {
		disk.AddFile(this);
		name = Name;
	}
	public VirtualFile(VirtualDirectory DIR,String Name) {
		DIR.AddFile(this);
		name = Name;
	}
	public VirtualFile() {
		
	}
	public VirtualFile(VirtualDisk disk,String Name,String permission) {
		disk.AddFile(this);
		name = Name;
		this.permission = permission;
	}
	public VirtualFile(VirtualDirectory DIR,String Name,String permission) {
		DIR.AddFile(this);
		name = Name;
		this.permission = permission;
	}
	boolean Access() {
		if (key != null) {
			Scanner scan = new Scanner(System.in);
			System.out.print("file key: ");
			if (scan.nextLine() != key) {
				System.out.println("Wrong key Number.");
				return false;
			}
		}
		if (permission != null && user.AccountType != permission) {
			if (permission == VirtualAccount.User) {
				return false;
			}
			else if (permission == VirtualAccount.SYSTEM && user.AccountType == VirtualAccount.Adminstrator) {
				return false;
			}
			
		}
		return true;
	}


}
