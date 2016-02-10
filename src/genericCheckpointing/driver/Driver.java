package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import genericCheckpointing.xmlStoreRestore.XmlStrategy;
import genericCheckpointing.xmlStoreRestore.Strategy;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.SerializableObject;

import java.lang.reflect.InvocationHandler;
import java.util.Vector;

// import the other types used in this file

public class Driver {

	public static void main(String[] args) {
	//args:
    //1 - checkpoint file name
	//2 - NUM OF OBJECTS
    //3 - ser, deser, serdeser
    //4 Wire method (xml)
		String checkpointFile = null;
		int NUM_OF_OBJECTS = 0;
		String serdeser = null;
		String wireMethod = null;
		if (args.length == 4){
			checkpointFile = args[0];
			try {
				NUM_OF_OBJECTS = new Integer(args[1]);
			} catch (NumberFormatException e){
				System.out.println("Please pass a valid integer for number of objects.");
				System.exit(0);
			}
			serdeser = args[2];
			wireMethod = args[3];
		} else {
			System.out.println("Wrong number of args");
			System.exit(0);
		}
		
		Strategy myStrategy = null;
		if (wireMethod.equals("XML")){
			myStrategy = new XmlStrategy();
		}

		ProxyCreator pc = new ProxyCreator();

	// create an instance of StoreRestoreHandler (which implements
	// the InvocationHandler
		StoreRestoreHandler handler = new StoreRestoreHandler();
	// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
			new Class[] {
				StoreI.class, RestoreI.class
			}, 
			handler
			);


		
	//invoke a method on the handler instance to set the file name for checkpointFile and open the file
		handler.setCheckpointFile(checkpointFile);
		if(serdeser.equals("ser") || serdeser.equals("serdeser")){
			handler.openCheckpointFileForWrite();
		}
		MyAllTypesFirst myFirst;
		MyAllTypesSecond  mySecond;

	// create a vector to store the objects being serialized

		int myInt = 0;
		String myString = "a";
		double myDouble = 1.0;
		long myLong = 2;
		char myChar = 'a';
		float myFloat = 3;
		short myShort = 4;
		Vector<SerializableObject> myVector = new Vector<SerializableObject>();
		for (int i=0; i<NUM_OF_OBJECTS; i++) {
			myFirst = new MyAllTypesFirst(myInt, myString + String.valueOf(i), myDouble, myLong, myChar);
			mySecond = new MyAllTypesSecond(myInt, myString + String.valueOf(i), myFloat, myShort, myChar);
			myVector.add(myFirst);
			myVector.add(mySecond);
	    //store myFirst and mySecond in the vector 
			if (serdeser.equals("ser") || serdeser.equals("serdeser")){
				((StoreI) cpointRef).writeObj(myFirst, myStrategy);
				((StoreI) cpointRef).writeObj(mySecond, myStrategy);
			}
			myInt++;
			myDouble++;
			myLong++;
			myChar++;
			myFloat++;
			myShort++;

		}
		if(serdeser.equals("ser") || serdeser.equals("serdeser")){
			handler.closeCheckPointFileForWrite();
		}

		SerializableObject myRecordRet;
		handler.openCheckpointFileForRead();
		Vector<SerializableObject> myVector2 = new Vector<SerializableObject>();
	// create a vector to store the returned ojects
		for (int j=0; j<2*NUM_OF_OBJECTS; j++) {
			if (serdeser.equals("deser") || serdeser.equals("serdeser")){
				myRecordRet = ((RestoreI) cpointRef).readObj(myStrategy);
				myVector2.add(myRecordRet);
			}

		}
		if (serdeser.equals("deser") || serdeser.equals("serdeser")){
			int objsNotTheSame = 0;
			boolean check = true;
			handler.closeCheckPointFileForRead();
			for (int p=0; p<2*NUM_OF_OBJECTS; p++){
		//System.out.println(myVector2.get(p));
				if (!myVector.get(p).equals(myVector2.get(p))){
					check = false;
					System.out.println("These two are different:");
					System.out.println(myVector.get(p));
					System.out.println(myVector2.get(p));
					objsNotTheSame++;
				}
			}
			System.out.println("Number of unequal objects = " + objsNotTheSame);
			if (!check){
				System.out.println("Failure.");
			} else {
				System.out.println("Success!");
			}
		}

	}
}
