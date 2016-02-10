package genericCheckpointing.server;

import genericCheckpointing.xmlStoreRestore.Strategy;
import genericCheckpointing.util.SerializableObject;

public interface RestoreI extends StoreRestoreI {
      public SerializableObject readObj(Strategy wireFormat);
}