package genericCheckpointing.server;

import genericCheckpointing.xmlStoreRestore.Strategy;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public interface StoreI extends StoreRestoreI {
    public void writeObj(MyAllTypesFirst aRecord, Strategy wireFormat);
    public void writeObj(MyAllTypesSecond aRecord, Strategy wireFormat);
}