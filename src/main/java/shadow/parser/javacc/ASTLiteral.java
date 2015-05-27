/* Generated By:JJTree: Do not edit this line. ASTLiteral.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package shadow.parser.javacc;

import shadow.interpreter.ShadowValue;

public
@SuppressWarnings("all")
class ASTLiteral extends SimpleNode {
	
	private Literal literal;
	private ShadowValue value;
	
  public ASTLiteral(int id) {
    super(id);
  }

  public ASTLiteral(ShadowParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ShadowParserVisitor visitor, Boolean secondVisit) throws ShadowException {
    return visitor.visit(this, secondVisit);
  }
  
  
  public void setLiteral(Literal literal)
  {
	  this.literal = literal;
  }
  
  public Literal getLiteral()
  {
	  return literal;
  }
  
  public ShadowValue getValue()
  {
	  return value;  
  }
  
  public void setValue(ShadowValue value)
  {
	  this.value = value;
  }
}
/* JavaCC - OriginalChecksum=e6f81fb816260e4c3f2f8b9ad1bc1340 (do not edit this line) */
