package filesystem;

public class VirtualStream {
	VirtualFile File;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public VirtualStream(VirtualFile file) {
		File = file;
	}
	void Write(String INPUT_DATA) {
		File.data += INPUT_DATA;
	}
	void WriteLine(String INPUT_DATA) {
		File.data += INPUT_DATA + "\n";
	}
	String Read() {
		return File.data;
	}
	String[] ReadAllLine() {
		String[] line = File.data.split("\n");
		return line;
	}
}
