package filesystem;

import java.util.ArrayList;
import java.util.List;

public class VirtualDisk {
	String name;
	List<VirtualFile> FileList = new ArrayList<VirtualFile>();
	
	List<VirtualDirectory> DirectoryList = new ArrayList<VirtualDirectory>();
	public VirtualDisk(String diskname) {
		this.name = diskname;
	}
	void Format() {
		FileList.clear();
		DirectoryList.clear();
	}
	void AddDirectory(VirtualDirectory dir) {
		DirectoryList.add(dir);
	}
	void AddFile(VirtualFile file) {
		FileList.add(file);
	}
	void DeleteFile(int index) {
		FileList.remove(index);
	}
	void DeleteDirectory(int index) {
		DirectoryList.remove(index);
	}

}
