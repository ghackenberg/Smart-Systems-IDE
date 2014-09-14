package org.xtream.demo.projecthouse.model.simple;

import org.xtream.core.model.Chart;
import org.xtream.core.model.Expression;
import org.xtream.core.model.Port;
import org.xtream.core.model.State;
import org.xtream.core.model.charts.Timeline;
import org.xtream.core.model.containers.Component;
import org.xtream.demo.projecthouse.model.Irradiation;
import org.xtream.demo.projecthouse.model.Producer;

public class PVComponent extends Component implements Producer {
	
	public Port<Irradiation> irradiationInput = new Port<>();
	public Port<Double> productionOutput = new Port<>();
	
	public Chart production = new Timeline(productionOutput);
	
	public Expression<Double> productionExpression = new Expression<Double>(productionOutput, true) {

		@Override
		protected Double evaluate(State state, int timepoint) {
			return getPower(irradiationInput.get(state, timepoint).irradiance);
		}
	};

	protected double getPower(double irradiance) {
		// TODO [Andreas] Get value from file with PV data
		return irradiance*1000;
	}

	@Override
	public Port<Double> production() {
		return productionOutput;
	}
	

}
