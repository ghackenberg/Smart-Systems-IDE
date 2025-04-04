package org.xtream.demo.thermal.model.smart;

import java.util.HashSet;
import java.util.Set;

import org.xtream.core.model.Expression;
import org.xtream.core.model.State;
import org.xtream.core.model.expressions.NonDeterministicExpression;
import org.xtream.demo.thermal.model.StorageComponent;

public class SmartStorageComponent extends StorageComponent
{

	public SmartStorageComponent(double speed, double capacity)
	{
		super(speed, capacity);
	}

	// Expressions
	
	public Expression<Double> commandExpression = new NonDeterministicExpression<Double>(commandInput)
	{
		@Override protected Set<Double> evaluateSet(State state, int timepoint)
		{
			Set<Double> result = new HashSet<>();
			
			if (timepoint == 0 || levelOutput.get(state, timepoint - 1) * loss - speed >= minimumOutput.get(state, timepoint))
			{
				result.add(1.);
			}
			if (timepoint == 0 || levelOutput.get(state, timepoint - 1) * loss + speed * efficiency <= maximumOutput.get(state, timepoint))
			{
				result.add(-1.);
			}
			if (timepoint == 0 || levelOutput.get(state, timepoint - 1) * loss >= minimumOutput.get(state, timepoint))
			{
				result.add(0.);
			}
			
			return result;
		}
		
	};
	
}
