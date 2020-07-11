package shadow.tac.nodes;

import shadow.ShadowException;
import shadow.tac.TACVariable;
import shadow.tac.TACVisitor;
import shadow.typecheck.type.ExceptionType;

public class TACCatchPad extends TACPad
{
	private ExceptionType type;
	private TACVariable variable;
	private TACCatch catchSwitch;

	public TACCatchPad(TACNode node, TACLabel label, ExceptionType catchType, TACVariable variable) {
		super(node, label);
		type = catchType;
		this.variable = variable;
	}
	
	public void setCatchSwitch(TACCatch catchSwitch) {
		this.catchSwitch = catchSwitch;
	}
	
	public TACCatch getCatchSwitch() {
		return catchSwitch;
	}

	@Override
	public ExceptionType getType() {
		return type;
	}

	@Override
	public void accept(TACVisitor visitor) throws ShadowException {
		visitor.visit(this);
	}
	
	public TACVariable getVariable() {
		return variable;
	}
}
