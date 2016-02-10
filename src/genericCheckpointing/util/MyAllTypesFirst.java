package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
	public int myInt;
	public String myString;
	public double myDouble;
	public long myLong;
	public char myChar;

	public MyAllTypesFirst(){}
	public MyAllTypesFirst(int myIntIn, String myStringIn, double myDoubleIn, long myLongIn, char myCharIn){
		myInt = myIntIn;
		myString = myStringIn;
		myDouble = myDoubleIn;
		myLong = myLongIn;
		myChar = myCharIn;
	}

	public int getMyInt(){
		return myInt;
	}

	public String getMyString(){
		return myString;
	}

	public double getMyDouble(){
		return myDouble;
	}

	public long getMyLong(){
		return myLong;
	}

	public char getMyChar(){
		return myChar;
	}

	public void setMyInt(int myIntIn){
		myInt = myIntIn;
	}

	public void setMyString(String myStringIn){
		myString = myStringIn;
	}

	public void setMyDouble(double myDoubleIn){
		myDouble = myDoubleIn;
	}

	public void setMyLong(long myLongIn){
		myLong = myLongIn;
	}

	public void setMyChar(char myCharIn){
		myChar = myCharIn;
	}
	
	@Override
	public boolean equals(Object obj){
		try {
		return (myInt == ((MyAllTypesFirst) obj).getMyInt()) && (myString.equals(((MyAllTypesFirst) obj).getMyString())) && (myDouble == ((MyAllTypesFirst) obj).getMyDouble()) && (myLong == ((MyAllTypesFirst) obj).getMyLong()) && (myChar == ((MyAllTypesFirst) obj).getMyChar());
		} catch (Exception e){
			return false;
		}
	}

	@Override
	public int hashCode(){
		return (myInt * 769) % 193;
	}

	@Override
	public String toString(){
		return "MyAllTypesFirst: " + getMyString() + " " + Integer.toString(getMyInt()) + " " + Double.toString(getMyDouble()) + " " + Long.toString(getMyLong()) + " " + Character.toString(getMyChar());
	}
}