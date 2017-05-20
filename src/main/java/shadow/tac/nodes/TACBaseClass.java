package shadow.tac.nodes;

import shadow.ShadowException;
import shadow.tac.TACVisitor;
import shadow.typecheck.type.Type;

public class TACBaseClass extends TACOperand {
	
	private TACOperand array;
	
	public TACBaseClass(TACNode node, TACOperand arrayRef) {
		super(node);
		array = check(arrayRef, arrayRef);		
	}

	public TACOperand getArray()
	{
		return array;
	}	
	
	@Override
	public Type getType() {		
		return Type.CLASS;
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
			return array;
		throw new IndexOutOfBoundsException("" + num);
	}

	@Override
	public void accept(TACVisitor visitor) throws ShadowException
	{
		visitor.visit(this);
	}

	@Override
	public String toString()
	{		
		return array + ":baseclass";		
	}
}