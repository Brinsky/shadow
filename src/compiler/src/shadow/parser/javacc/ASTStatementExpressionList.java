/* Generated By:JJTree: Do not edit this line. ASTStatementExpressionList.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package shadow.parser.javacc;

public
@SuppressWarnings("all")
class ASTStatementExpressionList extends SimpleNode {
  public ASTStatementExpressionList(int id) {
    super(id);
  }

  public ASTStatementExpressionList(ShadowParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ShadowParserVisitor visitor, Object data) throws ShadowException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=b9906cf969c1d85c128ec16c3b00a7ba (do not edit this line) */