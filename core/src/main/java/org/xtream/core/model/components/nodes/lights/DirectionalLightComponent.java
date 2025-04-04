package org.xtream.core.model.components.nodes.lights;

import org.apache.commons.math3.linear.RealVector;
import org.xtream.core.model.Port;
import org.xtream.core.model.components.nodes.LightComponent;

public abstract class DirectionalLightComponent extends LightComponent
{
	
	// Ports
	
	public Port<RealVector> directionOutput = new Port<>();
	
}
