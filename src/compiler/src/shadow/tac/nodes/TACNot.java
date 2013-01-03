package shadow.tac.nodes;

import shadow.parser.javacc.ShadowException;
import shadow.tac.TACVisitor;
import shadow.typecheck.type.Type;

public class TACNot extends TACOperand
{
	private TACOperand op;
	public TACNot(TACOperand operand)
	{
		this(null, operand);
	}
	public TACNot(TACNode node, TACOperand operand)
	{
		super(node);
		op = check(operand, Type.BOOLEAN);
	}

	public TACOperand getOperand()
	{
		return op;
	}

	@Override
	public Type getType()
	{
		return Type.BOOLEAN;
	}
	@Override
	public int getNumOperands()
	{
		return 1;
	}
	@Override
	public TACOperand getOperand(int num)
	{
		if (num == 0)
			return op;
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void accept(TACVisitor visitor) throws ShadowException
	{
		visitor.visit(this);
	}
}
