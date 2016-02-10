package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.OutputFileProcessor;
import genericCheckpointing.util.InputFileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.SerializableObject;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.reflect.*;

public class XmlStrategy implements Strategy {

	public XmlStrategy(){}

	public void writeObj(OutputFileProcessor outputProcessor, Object obj){
		try {
			Class myClass = obj.getClass(); //use reflection to get class 
			outputProcessor.writeLineToFile("<DPSerialization>");
			outputProcessor.writeLineToFile(" <complexType xsi:type=\"" + myClass.getName() + "\">"); //get class name from class

			Field[] fieldList = myClass.getFields(); //get field metadata from class
			//iterate through fields using reflection to get the field name, type, and value in obj
			for (Field field : fieldList){
				String buildString = "  <" + field.getName() + " xsi:type=\"xsd:" + field.getType() + "\">" + field.get(obj).toString() + "</" + field.getName() + ">";
				outputProcessor.writeLineToFile(buildString);
			}
			outputProcessor.writeLineToFile(" </complexType>");
			outputProcessor.writeLineToFile("</DPSerialization>");
		} catch (IllegalAccessException e){
			e.printStackTrace();
			System.exit(0);
		} finally {}
	}



	public Object readObj(InputFileProcessor inputProcessor){
		String line = inputProcessor.readLine();
		line = inputProcessor.readLine();
		String className = line.split("\"")[1];
		Class c = null;
		SerializableObject obj = null;		
		try {
			c = Class.forName(className); //set Class c to class referred to by string className
		} catch (ClassNotFoundException e){
			System.out.println("class: " + className + " not found.");
		} finally {}
		try {
			obj = (SerializableObject) c.newInstance(); //create a new instance of class c for returning
		} catch (InstantiationException e){
			System.out.println("Unable to instantiate class: " + className);
		} catch (IllegalAccessException e){
			System.out.println("Illegal acccess to class: " + className);
		} finally {}
		line = inputProcessor.readLine();

		while(!line.equals(" </complexType>")){
			String varName = line.substring(line.indexOf("<") + 1, line.indexOf(" ",line.indexOf("<")));
			String methodName = "set" + firstCharToUpper(varName);
			String value = line.substring(line.indexOf(">") + 1, line.indexOf("<", line.indexOf("<") + 1));
			Field f = null;
			Method m = null;
			try {
				f = c.getDeclaredField(varName); //get field from name of field in checkpoint file
			} catch (NoSuchFieldException e){
				System.out.println("No such field: " + varName);
			} finally {}
			Class[] signature = new Class[1];
			signature[0] = f.getType(); //get field type as it will be the parameter of the setter
			
			try {
				//get method for setting field
				m = c.getDeclaredMethod(methodName, signature);
				try {
					//pass correct object to the setter method by wrapping primitive types
					//This could probably be done more generically but I can't figure out how
					if (signature[0].getSimpleName().equals("int")){
						m.invoke(obj, new Integer(value));
					} else if (signature[0].getSimpleName().equals("float")){
						m.invoke(obj, new Float(value));
					} else if (signature[0].getSimpleName().equals("double")){
						m.invoke(obj, new Double(value));
					} else if (signature[0].getSimpleName().equals("short")){
						m.invoke(obj, new Short(value));
					} else if (signature[0].getSimpleName().equals("long")){
						m.invoke(obj, new Long(value));
					} else if (signature[0].getSimpleName().equals("char")){
						m.invoke(obj, new Character(value.charAt(0)));
					} else if (signature[0].getSimpleName().equals("String")){
						m.invoke(obj, value);
					}
				} catch (IllegalAccessException | InvocationTargetException e){
					System.out.println("Failed to add value to object");
				} finally {}
			} catch (NoSuchMethodException e){
				System.out.println("Method: " + methodName + " does not exist.");
			} catch (SecurityException e){
				System.out.println("Security exception on method: " + methodName);
			} finally {}
			line = inputProcessor.readLine();
		}
		line = inputProcessor.readLine();
		return obj; //return object with fields set
	}

	private String firstCharToUpper(String str){
		return Character.toString(str.charAt(0)).toUpperCase() + str.substring(1);
	}

	@Override
	public String toString(){
		return "XML Strategy pattern object for MyAllTypesFirst and MyAllTypesSecond";
	}

}