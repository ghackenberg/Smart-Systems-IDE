package org.xtream.demo.thermal.model;

import org.xtream.core.model.Chart;
import org.xtream.core.model.Expression;
import org.xtream.core.model.Port;
import org.xtream.core.model.annotations.Constraint;
import org.xtream.core.model.expressions.ConstantExpression;

public abstract class ThermalComponent extends EnergyComponent
{

	public ThermalComponent()
	{
		super(ThermalComponent.class.getClassLoader().getResource("consumer.png"));
	}
	
	// Parameters
	
	protected double increase = 0.5;
	protected double decrease = 1.5;
	
	// Inputs

	public Port<Double> probabilityInput = new Port<>();
	public Port<Boolean> commandInput = new Port<>();
	
	// Outputs
	
	public Port<Double> temperatureOutput = new Port<>();
	public Port<Double> minimumOutput = new Port<>();
	public Port<Double> maximumOutput = new Port<>();
	public Port<Boolean> validOutput = new Port<>();
	
	// Constraints
	
	public Constraint validConstraint = new Constraint(validOutput);
	
	// Charts
	
	public Chart temperatureChart = new Chart(minimumOutput, temperatureOutput, maximumOutput);
	
	// Expressions
	
	public Expression<Double> temperatureExpression = new Expression<Double>(temperatureOutput)
	{
		@Override public Double evaluate(int timepoint)
		{
			if (timepoint == 0)
			{
				return 5.;
			}
			else
			{
				if (commandInput.get(timepoint))
				{
					return temperatureOutput.get(timepoint - 1) - decrease;
				}
				else
				{
					return temperatureOutput.get(timepoint - 1) + increase;
				}
			}
		}
	};
	public Expression<Double> consumptionExpression = new Expression<Double>(consumptionOutput)
	{
		@Override public Double evaluate(int timepoint)
		{
			if (commandInput.get(timepoint))
			{
				return -200.;
			}
			else
			{
				return 0.;
			}
		}
	};
	public Expression<Boolean> validExpression = new Expression<Boolean>(validOutput)
	{
		@Override public Boolean evaluate(int timepoint)
		{
			return temperatureOutput.get(timepoint) >= minimumOutput.get(timepoint) && temperatureOutput.get(timepoint) < maximumOutput.get(timepoint);
		}
	};
	public Expression<Double> productionExpression = new ConstantExpression<Double>(productionOutput, 0.);
	public Expression<Double> minimumExpression = new ConstantExpression<Double>(minimumOutput, 2.0 - 0.1);
	public Expression<Double> maximumExpression = new ConstantExpression<Double>(maximumOutput, 8.0 + 0.1);
	
}
