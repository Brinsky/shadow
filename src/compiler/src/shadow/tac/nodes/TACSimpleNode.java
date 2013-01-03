package shadow.tac.nodes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import shadow.typecheck.type.PropertyType;
import shadow.typecheck.type.Type;
import shadow.typecheck.type.TypeParameter;

public abstract class TACSimpleNode extends TACNode
		implements Iterable<TACOperand>
{
	protected TACSimpleNode()
	{
		this(null);
	}
	protected TACSimpleNode(TACNode node)
	{
		super(node);
	}

	@Override
	public final Iterator<TACOperand> iterator()
	{
		return new OperandIterator();
	}
	private final class OperandIterator implements Iterator<TACOperand>
	{
		private int index = 0;

		@Override
		public boolean hasNext()
		{
			return index != getNumOperands();
		}

		@Override
		public TACOperand next()
		{
			if (index == getNumOperands())
				throw new NoSuchElementException();
			return getOperand(index++);
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

	public abstract int getNumOperands();
	public abstract TACOperand getOperand(int num);

	protected final TACOperand check(TACOperand operand)
	{
		return operand.checkVirtual(operand.getType(), this);
	}
	protected final TACOperand check(TACOperand operand, Type type)
	{
		if (type instanceof TypeParameter &&
				!(operand.getType() instanceof TypeParameter))
			type = Type.OBJECT;
		operand = operand.checkVirtual(type, this);
		if (type instanceof PropertyType)
			type = ((PropertyType)type).getGetType().getType();
		if (operand.getType().equals(type))
			return operand;
		if (operand.getType().getPackage().equals(type.getPackage()) &&
				operand.getType().getTypeName().equals(type.getTypeName()))
			return operand;
		throw new IllegalArgumentException();
	}
}
