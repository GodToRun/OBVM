package filesystem;
import java.util.*;
public class OSManager {
	static VirtualDirectory CurrentDirectory = null;
	static VirtualDirectory vdir = null;
	static VirtualAccount user = new VirtualAccount("DefaultUser",VirtualAccount.User);
	static Scanner sc = new Scanner(System.in);
	static VirtualDisk disk = new VirtualDisk("LocalDisk (PC)");
	static VirtualDisk CurrentDisk = disk;
	static VirtualPC vp = new VirtualPC("D-DOS VM");
	static boolean booted = true;
	static Exception e;
	static VirtualFile Clipboard;
	static VirtualDirectory ProgramsDir = new VirtualDirectory(disk,"Program");
	static VirtualDirectory RuntimeProcessorDir = new VirtualDirectory(disk,"System");
	static VirtualDirectory User = new VirtualDirectory(disk,"User");
	static VirtualDirectory Downloads = new VirtualDirectory(User,"Downloads");
	static VirtualDirectory Documents = new VirtualDirectory(User,"Documents");
	static VirtualDirectory MainDir = new VirtualDirectory(User,"Main");
	static VirtualFile SystemFormator = new VirtualFile(RuntimeProcessorDir,"dformat.rps",VirtualAccount.SYSTEM);
	static VirtualStream SystemInput = new VirtualStream(SystemFormator);
	public static void main(String[] args) {
		try {
			if (booted) {
				System.out.println("Welcome to D-DOS VM");
				SystemInput.Write("format");
				vp.DiskList.add(disk);
			}
			booted = false;
		while(true) {
			System.out.print(user.AccountType + ">");
			String value = sc.nextLine();
			String[] arg = value.split(" ");
			if (arg[0].equals("new")) {
				try {
					if (arg[2].equals("disk")) {
						VirtualFile vf = new VirtualFile(CurrentDisk,arg[1]);
					}
				}
				catch (Exception e) {
					if (CurrentDirectory == null) {
						VirtualFile vf = new VirtualFile(CurrentDisk,arg[1]);
					}
					else {
						VirtualFile vf = new VirtualFile(CurrentDirectory,arg[1]);
					}
					
				}
				System.out.println("File " + arg[1] + " was successfully created.");	
			}
			else if (arg[0].equals("newdisk")) {
				VirtualDisk vdisk = new VirtualDisk(arg[1]);
				vp.DiskList.add(vdisk);
			}
			else if (arg[0].equals("cb") || arg[0].equals("clipboard")) {
				if (arg[1].equals("cop") || arg[1].equals("copy")) {
					if (CurrentDirectory == null) {
						VirtualFile vf = CurrentDisk.FileList.get(Integer.parseInt(arg[2]));
						Clipboard = new VirtualFile();
						Clipboard.name = vf.name;
						Clipboard.key = vf.key;
						Clipboard.permission = vf.permission;
						Clipboard.user = vf.user;
						Clipboard.Format = vf.Format;
						Clipboard.data = vf.data;
					}
					else {
						VirtualFile vf = CurrentDirectory.FileList.get(Integer.parseInt(arg[2]));
						Clipboard = new VirtualFile();
						Clipboard.name = vf.name;
						Clipboard.key = vf.key;
						Clipboard.permission = vf.permission;
						Clipboard.user = vf.user;
						Clipboard.Format = vf.Format;
						Clipboard.data = vf.data;
					}
				}
				else if (arg[0].equals("paste")) {
					if (CurrentDirectory == null) {
						CurrentDisk.FileList.add(Clipboard);
					}
					else {
						CurrentDirectory.FileList.add(Clipboard);
					}
					
				}
			}
			else if (arg[0].equals("permission")) {
				if (arg[2].equals("set")) {
					if (CurrentDirectory == null) {
						if (arg[3].equals("admin")) {
							arg[3] = VirtualAccount.Adminstrator;
						}
						else if (arg[3].equals("adminstrator")) {
							arg[3] = VirtualAccount.Adminstrator;
						}
						else if (arg[3].equals("Admin")) {
							arg[3] = VirtualAccount.Adminstrator;
						}
						else if (arg[3].equals("ADMIN")) {
							arg[3] = VirtualAccount.Adminstrator;
						}
						else if (arg[3].equals("ADMINSTRATOR")) {
							arg[3] = VirtualAccount.Adminstrator;
						}
						else if (arg[3].equals("Adminstrator")) {
							arg[3] = VirtualAccount.Adminstrator;
						}
						else if (arg[3].equals("SYSTEM")) {
							arg[3] = VirtualAccount.SYSTEM;
						}
						else if (arg[3].equals("system")) {
							arg[3] = VirtualAccount.SYSTEM;
						}
						else if (arg[3].equals("System")) {
							arg[3] = VirtualAccount.SYSTEM;
						}
						else if (arg[3].equals("User")) {
							arg[3] = VirtualAccount.User;
						}
						else if (arg[3].equals("user")) {
							arg[3] = VirtualAccount.User;
						}
						else if (arg[3].equals("USER")) {
							arg[3] = VirtualAccount.User;
						}
						CurrentDisk.FileList.get(Integer.parseInt(arg[1])).permission = arg[3];
					}
					else {
						CurrentDirectory.FileList.get(Integer.parseInt(arg[1]));
					}
				}
			}
			else if (arg[0].equals("pc")) {
				int i = 0;
				System.out.println(vp.name);
				for (VirtualDisk virdisk : vp.DiskList) {
					System.out.println(i + " " + virdisk.name);
					i++;
				}
			}
			else if (arg[0].equals("dm")) {
				CurrentDisk = vp.DiskList.get(Integer.parseInt(arg[1]));
			}
			else if (arg[0].equals("format")) {
				RpsCompiler(SystemFormator,VirtualAccount.SYSTEM);
			}
			else if (arg[0].startsWith("$")) {
				char[] ch = arg[0].toCharArray();
				int i = Integer.parseInt(String.valueOf(ch[1]));
				if (CurrentDirectory == null) {
					Notepad(CurrentDisk.FileList.get(i));
				}
				else {
					Notepad(CurrentDirectory.FileList.get(i));
				}
			}
			else if (arg[0].startsWith("!")) {
				char[] ch = arg[0].toCharArray();
				int i = Integer.parseInt(String.valueOf(ch[1]));
					if (CurrentDirectory == null) {
						if (user.AccountType == VirtualAccount.User) {
							System.out.println("This file is in the form of a runtime process, which can lead to a serious virus infection.");
							System.out.print("Would you like to go on? (y/n): ");
							Scanner scan = new Scanner(System.in);
							if (scan.next().equals("y")) {
								RpsCompiler(CurrentDisk.FileList.get(i),VirtualAccount.User);
							}
						}
						else {
							RpsCompiler(CurrentDisk.FileList.get(i),VirtualAccount.User);
						}
						
					}
					else {
						if (user.AccountType == VirtualAccount.User) {
							System.out.println("This file is in the form of a runtime process, which can lead to a serious virus infection.");
							System.out.print("Would you like to go on? (y/n): ");
							Scanner scan = new Scanner(System.in);
							if (scan.next().equals("y")) {
								RpsCompiler(CurrentDirectory.FileList.get(i),VirtualAccount.User);
							}
						}
						else {
							RpsCompiler(CurrentDirectory.FileList.get(i),VirtualAccount.User);
						}
						
					}
					
					
				}
				
			
			else if (arg[0].equals("key")) {
				if (arg[2].equals("add")) {
					if (CurrentDirectory == null) {
						CurrentDisk.FileList.get(Integer.parseInt(arg[1])).key = arg[3];
					}
					else {
						CurrentDirectory.FileList.get(Integer.parseInt(arg[1])).key = arg[3];
					}
				}
				else if (arg[2].equals("view")) {
					if (CurrentDirectory == null) {
						System.out.println(CurrentDisk.FileList.get(Integer.parseInt(arg[1])).key);
					}
					else {
						System.out.println(CurrentDirectory.FileList.get(Integer.parseInt(arg[1])).key);
					}
					
				}
			}
			else if (arg[0].equals("user")) {
				if (arg[1].equals("switch")) {
					if (arg[2].equals("adminstrator")) {
						user.AccountType = VirtualAccount.Adminstrator;
					}
					else if (arg[2].equals("admin")) {
						user.AccountType = VirtualAccount.Adminstrator;
					}
					else if (arg[2].equals("user")) {
						user.AccountType = VirtualAccount.User;
					}
					else if (arg[2].equals("system")) {
						Scanner scanner = new Scanner(System.in);
						System.out.println("Switching users to the system will activate all privileges and can be very dangerous.");
						System.out.print("Would you like to Switch? (y/n): ");
						if (scanner.next().equals("y")) {
						user.AccountType = VirtualAccount.SYSTEM;
						}
					}
					else if (arg[2].equals("SYSTEM")) {
						Scanner scanner = new Scanner(System.in);
						System.out.println("Switching users to the system will activate all privileges and can be very dangerous.");
						System.out.print("Would you like to Switch? (y/n): ");
						if (scanner.next().equals("y")) {
						user.AccountType = VirtualAccount.SYSTEM;
						}
					}
				}
			}
			else if (arg[0].equals("newdir")) {
				try {
				if (arg[2].equals("disk")) {
					VirtualDirectory vd = new VirtualDirectory(disk,arg[1]);
					}
				}
				catch (Exception e) {
					if (CurrentDirectory == null) {
						VirtualDirectory vd = new VirtualDirectory(disk,arg[1]);
					}
					else {
						VirtualDirectory vd = new VirtualDirectory(CurrentDirectory,arg[1]);
					}
					
				}
				System.out.println("Directory " + arg[1] + " was successfully created.");	
			}
			else if (arg[0].equals("del")) {
				if (CurrentDirectory == null) {
					int i = Integer.parseInt(arg[1]);
					CurrentDisk.DeleteFile(i);
					
				}
				else {
					int i = Integer.parseInt(arg[1]);
					CurrentDirectory.DeleteFile(i);
				}
			}
			else if (arg[0].equals("deldir")) {
				if (CurrentDirectory == null) {
					int i = Integer.parseInt(arg[1]);
					CurrentDisk.DeleteDirectory(i);
					
				}
				else {
					int i = Integer.parseInt(arg[1]);
					CurrentDirectory.DeleteDirectory(i);
				}
			}
			else if (arg[0].equals("dir")) {
				try {
				if (arg[1].equals("disk")) {
					int i = 0;
					for (VirtualDirectory vd : CurrentDisk.DirectoryList) {
						System.out.println("DIR " + i + " " + vd.Path);
						i++;
					}
					i = 0;
					for (VirtualFile vf : CurrentDisk.FileList) {
						System.out.println(i + " " + vf.name);
						i++;
					}
				}
				}
				catch(Exception e) {
					if (CurrentDirectory != null) {
						int i = 0;
						for (VirtualDirectory vd : CurrentDirectory.DirectoryList) {
							System.out.println("DIR " + i + " " + vd.Path);
							i++;
						}
						i = 0;
						for (VirtualFile vf : CurrentDirectory.FileList) {
							System.out.println(i + " " + vf.name);
							i++;
						}
					}
					else {
						int i = 0;
						for (VirtualDirectory vd : CurrentDisk.DirectoryList) {
							System.out.println("DIR " + i + " " + vd.Path);
							i++;
						}
						i = 0;
						for (VirtualFile vf : CurrentDisk.FileList) {
							System.out.println(i + " " + vf.name);
							i++;
						}
					}
					
				}
			}
			else if (arg[0].equals("shutdown")) {
				System.exit(0);
			}
			else if (arg[0].equals("cd")) {
				if (!arg[1].equals("disk")) {
					if (CurrentDirectory == null) {
						
						vdir = CurrentDisk.DirectoryList.get(Integer.parseInt(arg[1]));
						CurrentDirectory = vdir;
					}
					else if (CurrentDirectory != null) {
						CurrentDirectory = vdir.DirectoryList.get(Integer.parseInt(arg[1]));
					}
					
				}
				else {
					CurrentDirectory = null;
					vdir = null;
				}
				
			}
			else {
				System.out.println("Unknown command.");
				}
			}
		}
		catch (Exception e) {
			System.out.println("An error has occurred.");
			System.out.println("Detail:");
			e.printStackTrace();
			System.exit(0);
		}
	}
	static void Notepad(VirtualFile vf) {
			String value = vf.data;
			VirtualStream stream = new VirtualStream(vf);
			String[] line = stream.ReadAllLine();
			List<String> str = new ArrayList<String>(Arrays.asList(line));
			int showline = 1;
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("------Notepad------");
			//System.out.println(value);
			while(true) {
				for (String s : line) {
					if (s == "") {
						continue;
					}
					System.out.println(showline + " " + s);
					showline++;
				}
				System.out.print(showline + " ");
				Scanner scan = new Scanner(System.in);
				String scanvalue = scan.nextLine();
				String[] arg = scanvalue.split(" ");
				if (scanvalue.equals("$save")) {
					value = "";
					for (String s : str) {
						value += s + "\n";
					}
					stream.WriteLine(value);
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					System.out.println(value);
					break;
				}
				else if (arg[0].equals("$edit")) {
					boolean canremove = false;
					Scanner scan2 = new Scanner(System.in);
					System.out.println("BeforeText: " + str.get(Integer.parseInt(arg[1])));
					System.out.print("ChangeTo: ");
					String scan2value = scan2.nextLine();
					str.set(Integer.parseInt(arg[1]),scan2value);
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	                System.out.println("------Notepad------");
	                int index = 1;
	                int i = 0;
	                if (scan2value == "") {
	                    str.remove(Integer.parseInt(arg[1]));
	                }
	                    
	                for (String s : str) {
	                	i++;
	                    if (s == "") {
	                        //canremove = true;
	                    	i--;
	                        continue;
	                    }
	                    System.out.println(/*index + */i + " " + s);
	                    index++;
	                    }
	                    
	        
	                /*if (canremove == true) {
	                    showline -= 1;
	                }*/
	            }
	            else if (arg[0].equals("$close")) {
	                scan.close();
	                break;
	            }
	            else {
	                str.add(scanvalue);
	                value += scanvalue + "\n";
	                showline++;
	            
				}
		}
		
		
		
	}
	static boolean FileAccess(VirtualFile vf) {
		Scanner scan = new Scanner(System.in);
		if (vf.key != null) {
			System.out.print("file key: ");
			if (scan.nextLine().equals(vf.key)) {
				return true;
			}
			else {
				System.out.println("invailed key.");
				return false;
			}
		}
		if (vf.permission != null && user.AccountType != vf.permission) {
			if (vf.permission == VirtualAccount.User) {
				return false;
			}
			else if (vf.permission == VirtualAccount.SYSTEM && user.AccountType == VirtualAccount.Adminstrator) {
				return false;
			}
			
		}
		return true;
	}
	static void RpsCompiler(VirtualFile vf, String Accessor) {
		try {
		if (FileAccess(vf)) {
			String inputvalue = "";
			Scanner rpscan = new Scanner(System.in);
			VirtualStream stream = new VirtualStream(vf);
			String[] lines = stream.ReadAllLine();
			for (String s : lines) {
				String[] arg = s.split(" ");
				if (arg[0].equals("println")) { //println 컴파일
					arg[0] = "";
					for (String STR : arg) {
						if (STR == "") {
							continue;
						}
						else if (STR.equals("inputvalue")) {
							System.out.println(inputvalue);
							continue;
						}
						System.out.print(STR + " ");
					}
					System.out.println();
				}
				else if (arg[0].equals("print")) { //print 컴파일
					arg[0] = "";
					for (String STR : arg) {
						if (STR == "") {
							continue;
						}
						else if (STR == "inputvalue") {
							System.out.print(inputvalue);
							continue;
						}
						System.out.print(STR + " ");
					}
				}
				else if (arg[0].equals("input")) { //println 컴파일
					inputvalue = rpscan.nextLine();
				}
				else if (arg[0].equals("format")) {
					if (Accessor == VirtualAccount.SYSTEM) {
						try {
							vp.DiskList.get(Integer.parseInt(arg[1])).Format();
						}
						catch(Exception e) {
							CurrentDisk.Format();
						}
					}
					else {
						throw new RpsCompileException("Cannot Compilation this file because You don't have access.");
					}
					
				}
				else {
						System.out.println("An error occurred during compilation.");
						throw new RpsCompileException("Unknown Command.");
				}
		
			}
		}
		else {
			System.out.println("failed to access the file.");
			throw new RpsCompileException("Failed to Access this file.");
			}
		}
		catch (RpsCompileException e) {
			System.out.println(e.getMessage());
		}
	}

}
class RpsCompileException extends Exception {
	public RpsCompileException(String msg) {
		super(msg);
	}
}