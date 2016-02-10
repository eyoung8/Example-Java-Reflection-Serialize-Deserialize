package genericCheckpointing.util;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;
import java.io.PrintWriter;
/**
*File processor class for reading files.
*@author Evan Young
*/
public class InputFileProcessor {
	private String fileName;
	private File file;
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	/**
	*Constructor
	*/
	public InputFileProcessor(String fileName){
		this.fileName = fileName;
		file = new File(fileName);
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
		} catch (IOException e) {
			System.out.println("File could not be opened.");
			e.printStackTrace();
			System.exit(1);
		} finally {
		}
		//Logger.writeMessage("Constructing class InputFileProcessor", Logger.DebugLevel.CONSTRUCTOR);

	}

	/**
	*Returns the next line of the file or null if empty.
	*@return String
	*/
	public synchronized String readLine(){
		try {
			return bufferedReader.readLine();
		} catch (IOException e){
			System.out.println("File could not be read.");
			e.printStackTrace();
			System.exit(1);
		} finally {
		}
		return null;
	}

	/**
	*Closes the file.
	*@return void
	*/
	public void closeFile(){
		try {
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e){
			System.out.println("File could not be closed correctly.");
			e.printStackTrace();
			System.exit(1);
		} finally {
		}
	}


	

	/**
	*@return String
	*/
	public String toString(){
		return fileName;
	}
}