package org.xtream.core.model.expressions;

import org.xtream.core.model.Expression;
import org.xtream.core.model.Port;
import org.xtream.core.model.State;

public class ConstantExpression<T> extends Expression<T>
{
	
	private T value;
	
	public ConstantExpression(Port<T> port, T value)
	{
		super(port);
		
		this.value = value;
	}
	
	@Override
	protected T evaluate(State state, int timepoint)
	{
		return value;
	}

}
