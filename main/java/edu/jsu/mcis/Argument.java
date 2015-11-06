package edu.jsu.mcis;

public class Argument{

	public enum Type {INT, FLOAT, BOOLEAN, STRING};
	private String value;
	private String name;
	private Type type;
	private String description;

	public Argument(){
		value = "0";
		name = "";
		type = Type.STRING;
		description = "";
	}

	public void setValue(String s){
		value = s;
	}

	public String getValue(){
		return value;
	}

	public void setType(Type t){
		type = t;
	}

	public Type getType(){
		return type;
	}

	public void setName(String s){
		name = s;
	}

	public String getName(){
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		description = desc;
	}
}
