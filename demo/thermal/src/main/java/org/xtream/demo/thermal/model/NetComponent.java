package org.xtream.demo.thermal.model;

import org.xtream.core.model.Chart;
import org.xtream.core.model.Expression;
import org.xtream.core.model.Port;
import org.xtream.core.model.annotations.Constraint;

public class NetComponent extends EnergyComponent
{
	
	@SuppressWarnings("unchecked")
	public NetComponent(double capacity, int size)
	{
		super(NetComponent.class.getClassLoader().getResource("net.png"));
		
		this.capacity = capacity;
		
		// Balance channels
		
		balanceInputs = new Port[size];
		
		for (int i = 0; i < size; i++)
		{
			balanceInputs[i] = new Port<>();
		}
	}
	
	// Parameters
	
	private double capacity;
	
	// Inputs
	
	public Port<Double>[] balanceInputs;
	
	// Outputs
	
	public Port<Boolean> validOutput = new Port<>();
	
	// Constraints
	
	public Constraint validConstraint = new Constraint(validOutput);
	
	// Charts
	
	public Chart energyChart = new Chart(productionOutput, consumptionOutput, balanceOutput);
	
	// Expressions

	public Expression<Double> productionExpression = new Expression<Double>(productionOutput)
	{
		@Override public Double evaluate(int timepoint)
		{
			double production = 0;
			
			for (Port<Double> terminal : balanceInputs)
			{
				double current = terminal.get(timepoint);
				
				production += current > 0. ? current : 0.;
			}
			
			return production;
		}
	};
	public Expression<Double> consumptionExpression = new Expression<Double>(consumptionOutput)
	{
		@Override public Double evaluate(int timepoint)
		{
			double consumption = 0;
			
			for (Port<Double> terminal : balanceInputs)
			{
				double current = terminal.get(timepoint);
				
				consumption += current < 0. ? current : 0.;
			}
			
			return consumption;
		}
	};
	public Expression<Boolean> validExpression = new Expression<Boolean>(validOutput)
	{	
		@Override public Boolean evaluate(int timepoint)
		{
			return productionOutput.get(timepoint) >= -capacity && consumptionOutput.get(timepoint) <= capacity; 
		}
	};
	
}
