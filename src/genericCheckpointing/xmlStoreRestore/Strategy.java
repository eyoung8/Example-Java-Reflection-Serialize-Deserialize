package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.OutputFileProcessor;
import genericCheckpointing.util.InputFileProcessor;

public interface Strategy {

	public void writeObj(OutputFileProcessor outputProcessor, Object obj);


	public Object readObj(InputFileProcessor inputProcessor);

}