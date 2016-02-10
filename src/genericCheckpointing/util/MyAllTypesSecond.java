package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject {
	public int myIntS;
	public String myStringS;
	public float myFloatS;
	public short myShortS;
	public char myCharS;

	public MyAllTypesSecond(){}
	public MyAllTypesSecond(int myIntSIn, String myStringSIn, float myFloatSIn, short myShortSIn, char myCharSIn){
		myIntS = myIntSIn;
		myStringS = myStringSIn;
		myFloatS = myFloatSIn;
		myShortS = myShortSIn;
		myCharS = myCharSIn;
	}

	public int getMyIntS(){
		return myIntS;
	}

	public String getMyStringS(){
		return myStringS;
	}

	public float getMyFloatS(){
		return myFloatS;
	}

	public short getMyShortS(){
		return myShortS;
	}

	public char getMyCharS(){
		return myCharS;
	}

	public void setMyIntS(int myIntSIn){
		myIntS = myIntSIn;
	}

	public void setMyStringS(String myStringSIn){
		myStringS = myStringSIn;
	}

	public void setMyFloatS(float myFloatSIn){
		myFloatS = myFloatSIn;
	}

	public void setMyShortS(short myShortSIn){
		myShortS = myShortSIn;
	}

	public void setMyCharS(char myCharSIn){
		myCharS = myCharSIn;
	}
	@Override
	public boolean equals(Object obj){
		try {
			return (myIntS == ((MyAllTypesSecond) obj).getMyIntS()) && (myStringS.equals(((MyAllTypesSecond) obj).getMyStringS())) && (myFloatS == ((MyAllTypesSecond) obj).getMyFloatS()) && (myShortS == ((MyAllTypesSecond) obj).getMyShortS()) && (myCharS == ((MyAllTypesSecond) obj).getMyCharS());
		} catch (Exception e){
			return false;
		}
	}

	@Override
	public int hashCode(){
		return (myIntS * 769) % 97;
	}

	@Override
	public String toString(){
		return "MyAllTypesSecond: " + getMyStringS() + " " + Integer.toString(getMyIntS()) + " " + Float.toString(getMyFloatS()) + " " + Short.toString(getMyShortS()) + " " + Character.toString(getMyCharS());
	}
}