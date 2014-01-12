package org.xtream.core.model.annotations;

import org.xtream.core.model.Annotation;
import org.xtream.core.model.Port;
import org.xtream.core.model.enumerations.Direction;

public class Objective extends Annotation<Double>
{
	
	public Direction direction;

	public Objective(Port<Double> port, Direction direction)
	{
		super(port);
		
		this.direction = direction;
	}

}
