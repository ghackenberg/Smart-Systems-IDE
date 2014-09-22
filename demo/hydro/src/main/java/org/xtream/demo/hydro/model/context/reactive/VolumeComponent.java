package org.xtream.demo.hydro.model.context.reactive;

import org.xtream.core.model.Chart;
import org.xtream.core.model.Component;
import org.xtream.core.model.Expression;
import org.xtream.core.model.Port;
import org.xtream.core.model.State;
import org.xtream.core.model.charts.Timeline;
import org.xtream.core.model.markers.Constraint;
import org.xtream.demo.hydro.data.PolynomLevel;
import org.xtream.demo.hydro.model.Constants;

public class VolumeComponent extends Component
{
	
	// Parameters
	
	private double levelMin;
	private double levelMax;
	
	private PolynomLevel model;
	
	// Constructors
	
	public VolumeComponent(int staustufe, int level_past, int level_order, int inflow_past, int inflow_order, int outflow_past, int outflow_order, double levelMin, double levelMax)
	{
		this.levelMin = levelMin;
		this.levelMax = levelMax;
		
		model = new PolynomLevel(staustufe, level_past, level_order, inflow_past, inflow_order, outflow_past, outflow_order);
		model.fit(Constants.DATASET);
	}
	
	// Ports
	
	public Port<Double> inflowInput = new Port<Double>();
	public Port<Double> outflowInput = new Port<Double>();
	
	public Port<Double> levelMinOutput = new Port<Double>();
	public Port<Double> levelMaxOutput = new Port<Double>();
	
	public Port<Double> levelOutput = new Port<Double>();
	public Port<Boolean> bandOutput = new Port<Boolean>();
	
	// Constraints
	
	public Constraint bandConstraint = new Constraint(bandOutput);
	
	// Charts
	
	public Chart levelChart = new Timeline(levelMinOutput, levelOutput, levelMaxOutput);
	public Chart flowChart = new Timeline(inflowInput, outflowInput);
	
	// Expressions
	
	public Expression<Double> levelExpression = new Expression<Double>(levelOutput, true)
	{
		@Override protected Double evaluate(State state, int timepoint)
		{
			if (timepoint == 0)
			{
				return Constants.DATASET.getLevel(model.getStaustufe(), Constants.START + timepoint);
			}
			else
			{
				double[] levels = new double[model.getLevelPast()];
				double[] inflows = new double[model.getInflowPast()];
				double[] outflows = new double[model.getOutflowPast()];
				
				for (int i = 0; i < model.getLevelPast(); i++)
				{
					if (i < timepoint)
					{
						levels[levels.length - 1 - i] = levelOutput.get(state, timepoint - 1 - i);
					}
					else
					{
						levels[levels.length - 1 - i] = Constants.DATASET.getLevel(model.getStaustufe(), Constants.START + timepoint - 1 - i);
					}
				}
				for (int i = 0; i < model.getInflowPast(); i++)
				{
					if (i <= timepoint)
					{
						inflows[inflows.length - 1 - i] = inflowInput.get(state, timepoint - i);
					}
					else
					{
						inflows[inflows.length - 1 - i] = Constants.DATASET.getInflow(model.getStaustufe(), Constants.START + timepoint - i);
					}
				}
				for (int i = 0; i < model.getOutflowPast(); i++)
				{
					if (i <= timepoint)
					{
						outflows[outflows.length - 1 - i] = outflowInput.get(state, timepoint - i);
					}
					else
					{
						outflows[outflows.length - 1 - i] = Constants.DATASET.getOutflowTotal(model.getStaustufe(), Constants.START + timepoint - i);
					}
				}
				
				return model.estimate(levels, inflows, outflows);
			}
		}
	};
	public Expression<Double> levelMinExpression = new Expression<Double>(levelMinOutput)
	{
		@Override protected Double evaluate(State state, int timepoint)
		{
			return levelMin;
		}
	};
	public Expression<Double> levelMaxExpression = new Expression<Double>(levelMaxOutput)
	{
		@Override protected Double evaluate(State state, int timepoint)
		{
			return levelMax;
		}
	};
	public Expression<Boolean> bandExpression = new Expression<Boolean>(bandOutput)
	{
		@Override protected Boolean evaluate(State state, int timepoint)
		{
			return levelOutput.get(state, timepoint) >= levelMin - 0.01 && levelOutput.get(state, timepoint) <= levelMax + 0.01;
		}
	};

}
