package filesystem;
import java.util.*;
public class VirtualDirectory{
	List<VirtualFile> FileList = new ArrayList<VirtualFile>();
	List<VirtualDirectory> DirectoryList = new ArrayList<VirtualDirectory>();
	String Path;
	public VirtualDirectory(VirtualDisk disk, String dirPath) {
		disk.AddDirectory(this);
		Path = dirPath;
	}
	public VirtualDirectory(VirtualDirectory dir, String dirPath) {
		dir.DirectoryList.add(this);
		Path = dirPath;
	}
	void AddFile(VirtualFile file) {
		FileList.add(file);
	}
	void DeleteFile(VirtualFile file) {
		FileList.remove(file);
	}
	void DeleteFile(int index) {
		FileList.remove(index);
	}
	void AddDirectory(VirtualDirectory dir) {
		DirectoryList.add(dir);
	}
	void DeleteDirectory(VirtualDirectory dir) {
		DirectoryList.remove(dir);
	}
	void DeleteDirectory(int index) {
		DirectoryList.remove(index);
	}
	VirtualFile GetFile(int index) {
		return this.FileList.get(index);
	}
	VirtualDirectory GetDir(int index) {
		return this.DirectoryList.get(index);
	}
	VirtualDirectory GetDirectory(int index) {
		return this.DirectoryList.get(index);
	}

}
