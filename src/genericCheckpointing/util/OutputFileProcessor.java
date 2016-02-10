package genericCheckpointing.util;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Vector;

public class OutputFileProcessor {
	PrintWriter outfile;
	String fileName;
	public OutputFileProcessor(String output_file){
		fileName = output_file;
		try {
			outfile = new PrintWriter(output_file);
		} catch (IOException e){
			System.out.println("Error opening " + output_file + " for writing");
		} finally {}
		//Logger.writeMessage("Constructing class OutputFileProcessor", Logger.DebugLevel.CONSTRUCTOR);

	}

	public void writeToFile(Vector<String> data){
		if(!data.isEmpty()){
			for(int i = 0; i < data.size(); i++){
				writeLineToFile(data.get(i));
			}	
		}
	}

	public void writeLineToFile(String line){
		outfile.println(line);
	}

	public void closeFile(){
		outfile.close();
	}

	/**
	*@return String
	*/
	public String toString(){
		return fileName;
	}
}