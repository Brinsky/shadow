/* Generated By:JJTree: Do not edit this line. ASTEnumDeclaration.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package shadow.parser.javacc;

import shadow.typecheck.type.Modifiers;

public
@SuppressWarnings("all")
class ASTEnumDeclaration extends SimpleNode {
  public ASTEnumDeclaration(int id) {
    super(id);
  }

  public ASTEnumDeclaration(ShadowParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ShadowParserVisitor visitor, Boolean secondVisit) throws ShadowException {
    return visitor.visit(this, secondVisit);
  }
  
  private Modifiers modifiers;
  
  public void setModifiers(Modifiers modifiers) {
	  this.modifiers = modifiers;
  }
  
  public Modifiers getModifiers() {
	  return this.modifiers;
  }
}
/* JavaCC - OriginalChecksum=4c9b7b9fb2fd7448f52bd14b4e141511 (do not edit this line) */