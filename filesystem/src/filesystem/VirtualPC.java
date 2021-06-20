package filesystem;

import java.util.ArrayList;
import java.util.List;

public class VirtualPC {
	String name;
	List<VirtualDisk> DiskList = new ArrayList<VirtualDisk>();
	VirtualPC(String PCNAME) {
		name = PCNAME;
	}
}
