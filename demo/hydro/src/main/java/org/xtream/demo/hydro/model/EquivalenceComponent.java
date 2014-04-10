package org.xtream.demo.hydro.model;

import org.xtream.core.model.Component;
import org.xtream.core.model.Expression;
import org.xtream.core.model.Port;
import org.xtream.core.model.annotations.Equivalence;

public class EquivalenceComponent extends Component
{
	
	// Ports
	
	public Port<Double> speicherseeLevelInput = new Port<>();
	public Port<Double> volumen1LevelInput = new Port<>();
	public Port<Double> volumen2LevelInput = new Port<>();
	public Port<Double> volumen3LevelInput = new Port<>();
	public Port<Double> volumen4LevelInput = new Port<>();
	
	public Port<Double> volumenAverageOutput = new Port<>();
	
	// Equivalences
	
	public Equivalence volumenAverageEquivalence = new Equivalence(volumenAverageOutput);
	
	// Expressions
	
	public Expression<Double> volumenAverageExpression = new Expression<Double>(volumenAverageOutput)
	{		@Override public Double evaluate(int timepoint)
		{
			return speicherseeLevelInput.get(timepoint) / Constants.SPEICHERSEE_LEVEL_MAX / 5 + volumen1LevelInput.get(timepoint) / Constants.VOLUMEN1_LEVEL_MAX / 5 + volumen2LevelInput.get(timepoint) / Constants.VOLUMEN2_LEVEL_MAX / 5 + volumen3LevelInput.get(timepoint) / Constants.VOLUMEN3_LEVEL_MAX / 5 + volumen4LevelInput.get(timepoint) / Constants.VOLUMEN4_LEVEL_MAX / 5;
		}
	};

}
