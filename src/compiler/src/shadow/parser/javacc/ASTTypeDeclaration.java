/* Generated By:JJTree: Do not edit this line. ASTTypeDeclaration.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package shadow.parser.javacc;

public
@SuppressWarnings("all")
class ASTTypeDeclaration extends SimpleNode {
  public ASTTypeDeclaration(int id) {
    super(id);
  }

  public ASTTypeDeclaration(ShadowParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ShadowParserVisitor visitor, Object data) throws ShadowException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=ab6d86b00eea1ca390fa60965a461611 (do not edit this line) */
