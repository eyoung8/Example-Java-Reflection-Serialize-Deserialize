package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import genericCheckpointing.xmlStoreRestore.Strategy;
import genericCheckpointing.util.OutputFileProcessor;
import genericCheckpointing.util.InputFileProcessor;

public class StoreRestoreHandler implements InvocationHandler {
	private String checkpointFile;
	private OutputFileProcessor fileProcessorOut;
	private InputFileProcessor fileProcessorIn;

	public StoreRestoreHandler(){}

	public Object invoke (Object proxy, Method m, Object[] args){
		String methodName = m.getName();
		if (methodName.equals("writeObj")){
			Strategy strategy = (Strategy) args[1];
			strategy.writeObj(fileProcessorOut, args[0]);
			return null;
		} 
		if (methodName.equals("readObj")){
			Strategy strategy = (Strategy) args[0];
			Object obj = strategy.readObj(fileProcessorIn);
			return obj;
		}
		return null;
	}

	public void setCheckpointFile(String checkPointFileIn){
		checkpointFile = checkPointFileIn;
	}

	public void openCheckpointFileForWrite(){
		fileProcessorOut = new OutputFileProcessor(checkpointFile);
	}

	public void closeCheckPointFileForWrite(){
		fileProcessorOut.closeFile();
	}

	public void openCheckpointFileForRead(){
		fileProcessorIn = new InputFileProcessor(checkpointFile);
	}

	public void closeCheckPointFileForRead(){
		fileProcessorIn.closeFile();
	}

	
}