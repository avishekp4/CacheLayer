package KeyValue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterObj implements Runnable {
	
	String filename;
	String key;
	String value;
	String isDeleted;

	public FileWriterObj(String filename, String key, String value, String isDeleted) {
		super();
		this.filename = filename;
		this.key = key;
		this.value = value;
		this.isDeleted = isDeleted;
	}

	@Override
	public void run() {
    	System.out.println("task started");

		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
//			writer.append("\n"+key);
//			writer.append("\n"+value);
//			writer.append("\n"+isDeleted);
//			writer.close();
			FileWriter filewriter= new FileWriter((new File(filename)));
			filewriter.write(key);
			filewriter.write(value);
			filewriter.write(isDeleted);
			filewriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.println("task ended");

	}

}
