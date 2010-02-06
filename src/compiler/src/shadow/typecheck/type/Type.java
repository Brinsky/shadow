package shadow.typecheck.type;

import java.util.HashMap;

public class Type {
	//types should not change after construction
	protected final String typeName;	/** A string the represents the type */
	protected final int modifiers; //do we need modifiers for types or just for references?  private inner classes, perhaps?
	protected final Type outer; //outer class	
	protected Kind kind;
	
	public static enum Kind { CLASS, ENUM, ERROR, EXCEPTION, INTERFACE, METHOD,  VIEW};
	
	public static final ClassType OBJECT = new ClassType( "Object", 0, null ); 
	public static final ClassType BOOLEAN = new ClassType( "boolean" );
	public static final ClassType BYTE = new ClassType( "byte" );
	public static final ClassType CODE = new ClassType( "code" );	
	public static final ClassType SHORT = new ClassType( "short" );
	public static final ClassType INT = new ClassType( "int" );
	public static final ClassType LONG = new ClassType( "long" );	  
	public static final ClassType FLOAT = new ClassType( "float" );
	public static final ClassType DOUBLE = new ClassType( "double" );
	public static final ClassType STRING = new ClassType( "String" );
	public static final ClassType UBYTE = new ClassType( "ubyte" );
	public static final ClassType UINT = new ClassType( "uint" );
	public static final ClassType ULONG = new ClassType( "ulong" );
	public static final ClassType USHORT = new ClassType( "ushort" );
	
	public static final Type NULL = new Type( "null" );
	
	public Type(String typeName) {
		this( typeName, 0 );
	}
	
	public Type(String typeName, int modifiers) {
		this( typeName, modifiers, null );
	}
	
	public Type(String typeName, int modifiers, Type outer ) {
		this( typeName, modifiers, outer, Kind.CLASS );
	}	
	
	public Type(String typeName, int modifiers, Type outer, Kind kind ) {
		this.typeName = typeName;
		this.modifiers = modifiers;
		this.outer = outer;		
		this.kind = kind;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public int getModifiers() {
		return modifiers;
	}
	
	public String toString() {
		return typeName;
	}

	public boolean equals(Object o) {
		Type t = (Type)o;

		return  typeName.equals(t.typeName);
	}
	
	public boolean isSubtype(Type t) {				
		//
		// Put in sub-typing logic here
		//
	
		//null is the subtype of everything
		return equals(NULL) || equals( t );
	}
	
	// TODO: Will this work? 
	public int hashCode() {
		return typeName.hashCode();
	}
	
	public boolean isString() {
		return this.equals(ClassType.STRING);
	}
	
	public Type getOuter()
	{
		return outer;
	}
	
	public void setKind(Kind kind ) {
		  this.kind = kind;
	  }
	  
	  public Kind getKind() {
		  return this.kind;
	  }
	
	
	/**
	 * Given an unsigned type, returns the signed version or the same type otherwise.
	 * @param type The type to convert.
	 * @return The signed version of the type.
	 */
	public static ClassType makeSigned(ClassType type) {
		if(type.equals(UBYTE))
			return BYTE;
		
		if(type.equals(USHORT))
			return SHORT;
		
		if(type.equals(UINT))
			return INT;
		
		if(type.equals(ULONG))
			return LONG;
		
		return type;
	}
	
	//for math
	public boolean isNumerical()
	{
		return
		this.equals(BYTE) ||
		this.equals(CODE) ||	// ??? REALLY ???	
		this.equals(SHORT) ||
		this.equals(INT) ||
		this.equals(LONG) ||	  
		this.equals(FLOAT) ||
		this.equals(DOUBLE) ||		
		this.equals(UBYTE) ||
		this.equals(UINT) ||
		this.equals(ULONG) ||
		this.equals(USHORT);
	}
	
	//for cases where integers are required (bitwise operations, array bounds, switch statements, etc.)
	public boolean isIntegral()
	{
		return
		this.equals(BYTE) ||
		this.equals(CODE) ||	
		this.equals(SHORT) ||
		this.equals(INT) ||
		this.equals(LONG) ||	  
		this.equals(UBYTE) ||
		this.equals(UINT) ||
		this.equals(ULONG) ||
		this.equals(USHORT);
	}
	
	
	public boolean isBuiltIn()
	{
		return
		this.equals(OBJECT) ||
		this.equals(BOOLEAN) ||
		this.equals(BYTE) ||
		this.equals(CODE) ||	
		this.equals(SHORT) ||
		this.equals(INT) ||
		this.equals(LONG) ||	  
		this.equals(FLOAT) ||
		this.equals(DOUBLE) ||
		this.equals(STRING) ||
		this.equals(UBYTE) ||
		this.equals(UINT) ||
		this.equals(ULONG) ||
		this.equals(USHORT);
	}
	
	public boolean isPrimitive()
	{
		return
		this.equals(BYTE) ||
		this.equals(CODE) ||	
		this.equals(SHORT) ||
		this.equals(INT) ||
		this.equals(LONG) ||	  
		this.equals(FLOAT) ||
		this.equals(DOUBLE) ||
		this.equals(UBYTE) ||
		this.equals(UINT) ||
		this.equals(ULONG) ||
		this.equals(USHORT);
	}
}