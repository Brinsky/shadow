package shadow.parse;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;

import shadow.doctool.Documentation;
import shadow.tac.nodes.TACNode;
import shadow.tac.nodes.TACOperand;
import shadow.typecheck.type.MethodSignature;
import shadow.typecheck.type.ModifiedType;
import shadow.typecheck.type.Modifiers;
import shadow.typecheck.type.Type;

public class Context extends ParserRuleContext implements ModifiedType {
	
	public enum AssignmentKind {
		  EQUAL("=", ""),
		  CAT("#=", "concatenate"),
		  PLUS("+=", "add"),
		  MINUS("-=", "subtract"),
		  STAR("*=", "multiply"),
		  SLASH("/=", "divide"),
		  MOD("%=", "modulus"),
		  AND("&=", "bitAnd"),
		  OR("|=", "bitOr"),
		  XOR("^=", "bitXor"),
		  LEFT_SHIFT("<<=", "bitShiftLeft"),
		  RIGHT_SHIFT(">>=", "bitShiftRight"),
		  LEFT_ROTATE("<<<=", "bitRotateLeft"),
		  RIGHT_ROTATE(">>>=", "bitRotateLeft");	  
		  
		  private String operator;
		  private String method;
		  
		  public static AssignmentKind getKind(String operator)
		  {
			  for( AssignmentKind kind : AssignmentKind.values() )
				  if( kind.getOperator().equals(operator) )
					  return kind;
			  
			  return null;
		  }
		  
		  AssignmentKind( String operator, String method )
		  {
			  this.operator = operator;
			  this.method = method;		  
		  }
		  
		  public String getOperator()
		  {
			  return operator;
		  }
		  
		  public String getMethod()
		  {
			  return method;
		  }
	  }
		
	private Type type;	
	private Modifiers modifiers;
	private MethodSignature signature;
	private Type enclosingType;
	private Documentation documentation;
	private List<MethodSignature> operations;
	private TACNode list;
	private TACOperand operand;	
	
	public Context()
	{				
	}
	
	public Context(ParserRuleContext parent, int invokingStateNumber)
	{
		super(parent, invokingStateNumber);	
	}
	
	public Path getPath()
	{
		if( getStart() != null ) {
			CharStream stream = getStart().getInputStream();
			if( stream instanceof PathStream )
				return ((PathStream)stream).getPath();					
		}
		
		return null;
	}
	
	public String getSource()
	{
		if( getStart() != null ) {
			CharStream stream = getStart().getInputStream();
			if( stream instanceof PathStream )
				return ((PathStream)stream).getPath().toString();		
		
			return stream.getSourceName();
		}
		
		return null;
	}
	
	public void setType(Type type) 
	{
		this.type = type;
	}
	
	public Type getType()
	{
		return type;
	}
	
	public Modifiers getModifiers()
	{
		if( modifiers == null )
			modifiers = new Modifiers();
		return modifiers;
	}
	
	public boolean addModifiers(Modifiers modifiers)
	{		
		return addModifiers(modifiers.getModifiers());
	}
	
	public boolean addModifiers(int modifiers)
	{
		if( this.modifiers == null )
			this.modifiers = new Modifiers();
		return this.modifiers.addModifier(modifiers);
	}
	
	public int lineStart()
	{
		if( getStart() != null )
			return getStart().getLine();
		return -1;		
	}
	
	public int lineEnd()
	{
		if( getStop() != null )
			return getStop().getLine();
		return -1;		
	}
	
	public int columnStart()
	{
		if( getStart() != null )
			return getStart().getCharPositionInLine();
		return -1;		
	}
	
	public int columnEnd()
	{
		if( getStop() != null )
			return getStop().getCharPositionInLine();
		return -1;		
	}
	
	public MethodSignature getSignature()
	{
		return signature;
	}
	
	public void setSignature(MethodSignature signature)
	{
		this.signature = signature;
	}
	
	public void setEnclosingType(Type type)
	{
		if( type != null )
			enclosingType = type.getTypeWithoutTypeArguments();
		else
			enclosingType = null;
	}
	
	public Type getEnclosingType()
	{
		return enclosingType;
	}
	
	public Documentation getDocumentation()
	{
		return documentation;
	}
	
	public boolean hasDocumentation()
	{
		return documentation != null;
	}
	
	public void setDocumentation(Documentation documentation)
	{
		this.documentation = documentation;
	}
	
	public List<MethodSignature> getOperations()
	{
		if( operations == null )
			operations = new ArrayList<MethodSignature>();
		return operations;
	}
	
	public void addOperation(MethodSignature signature)
	{
		if( operations == null )
			operations = new ArrayList<MethodSignature>();
		operations.add(signature);
	}
	
	
	public static ShadowParser.VariableDeclaratorContext copy( ShadowParser.VariableDeclaratorContext declarator )
	{
		ShadowParser.VariableDeclaratorContext  object = new ShadowParser.VariableDeclaratorContext(declarator.getParent(), declarator.invokingState);
		object.setType(declarator.getType());
		object.addModifiers(declarator.getModifiers());
		object.setSignature(object.getSignature());
		object.setEnclosingType(object.getEnclosingType());
		object.setDocumentation(declarator.getDocumentation());
		object.start = declarator.start;
		object.stop = declarator.stop;
		for( MethodSignature signature : declarator.getOperations() )
			object.addOperation(signature);
		
		return object;	
	}
	
	
	public void setList(TACNode node)
	{
		list = node;
	}
	
	public TACNode getList()
	{
		return list;
	}
	
	public TACOperand appendBefore(TACNode node)
	{
		if( list != null )
			list.appendBefore(node);
		return operand;
	}
	
	public TACOperand setOperand(TACOperand op)
	{
		operand = op;
		return operand;
	}
	
	public TACOperand getOperand()
	{
		return operand;
	}
}