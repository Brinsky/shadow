/* Generated By:JJTree: Do not edit this line. ASTConditionalOrExpression.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package shadow.parser.javacc;

public
@SuppressWarnings("all")
class ASTConditionalOrExpression extends SimpleNode {
  public ASTConditionalOrExpression(int id) {
    super(id);
  }

  public ASTConditionalOrExpression(ShadowParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ShadowParserVisitor visitor, Object data) throws ShadowException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=3dc41ddb92e6c65ba862157d1eb64d52 (do not edit this line) */
