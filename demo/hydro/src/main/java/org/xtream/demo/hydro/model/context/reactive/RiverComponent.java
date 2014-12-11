package org.xtream.demo.hydro.model.context.reactive;

import org.xtream.core.model.Component;
import org.xtream.core.model.Expression;
import org.xtream.core.model.Port;
import org.xtream.core.model.State;
import org.xtream.core.model.expressions.ChannelExpression;
import org.xtream.core.model.markers.Constraint;
import org.xtream.demo.hydro.model.Constants;
import org.xtream.demo.hydro.model.context.reactive.volume.VolumeDirectComponent;

public class RiverComponent extends Component
{
	
	// Ports
	
	public Port<Double> scenarioInflowInput = new Port<>();
	
	public Port<Double> hauptkraftwerkTurbineDischargeInput = new Port<>();
	public Port<Double> wehr1TurbineDischargeInput = new Port<>();
	public Port<Double> wehr2TurbineDischargeInput = new Port<>();
	public Port<Double> wehr3TurbineDischargeInput = new Port<>();
	public Port<Double> wehr4TurbineDischargeInput = new Port<>();
	
	public Port<Double> hauptkraftwerkWeirDischargeInput = new Port<>();
	public Port<Double> wehr1WeirDischargeInput = new Port<>();
	public Port<Double> wehr2WeirDischargeInput = new Port<>();
	public Port<Double> wehr3WeirDischargeInput = new Port<>();
	public Port<Double> wehr4WeirDischargeInput = new Port<>();
	
	public Port<Double> speicherseeLevelOutput = new Port<>();
	public Port<Double> volumen1LevelOutput = new Port<>();
	public Port<Double> volumen2LevelOutput = new Port<>();
	public Port<Double> volumen3LevelOutput = new Port<>();
	public Port<Double> volumen4LevelOutput = new Port<>();
	public Port<Double> volumen5LevelOutput = new Port<>();
	
	public Port<Double> hauptkraftwerkProductionOutput = new Port<>();
	public Port<Double> wehr1ProductionOutput = new Port<>();
	public Port<Double> wehr2ProductionOutput = new Port<>();
	public Port<Double> wehr3ProductionOutput = new Port<>();
	public Port<Double> wehr4ProductionOutput = new Port<>();
	
	public Port<Boolean> levelConstraintOutput = new Port<>();
	public Port<Boolean> dischargeConstraintOutput = new Port<>();
	
	// Components

	public VolumeComponent speichersee = new VolumeDirectComponent(0, 1, 1, 1, 1, 1, 1, 315, 315 + Constants.SPEICHERSEE_LEVEL_MAX);
	public VolumeComponent volumen1 = new VolumeDirectComponent(1, 1, 1, 1, 1, 1, 1, 275.4, 275.4 + Constants.VOLUMEN1_LEVEL_MAX);
	public VolumeComponent volumen2 = new VolumeDirectComponent(2, 1, 1, 1, 1, 1, 1, 271.7, 271.7 + Constants.VOLUMEN2_LEVEL_MAX);
	public VolumeComponent volumen3 = new VolumeDirectComponent(3, 1, 1, 5, 1, 1, 1, 269.2, 269.2 + Constants.VOLUMEN3_LEVEL_MAX);
	public VolumeComponent volumen4 = new VolumeDirectComponent(4, 1, 1, 15, 1, 1, 1, 267.6, 267.6 + Constants.VOLUMEN4_LEVEL_MAX);
	/*
	public VolumeComponent speichersee = new VolumeDeltaComponent(0, 1, 1, 1, 1, 315, 315 + Constants.SPEICHERSEE_LEVEL_MAX);
	public VolumeComponent volumen1 = new VolumeDeltaComponent(1, 1, 1, 1, 1, 275.4, 275.4 + Constants.VOLUMEN1_LEVEL_MAX);
	public VolumeComponent volumen2 = new VolumeDeltaComponent(2, 1, 1, 1, 1, 271.7, 271.7 + Constants.VOLUMEN2_LEVEL_MAX);
	public VolumeComponent volumen3 = new VolumeDeltaComponent(3, 5, 1, 1, 1, 269.2, 269.2 + Constants.VOLUMEN3_LEVEL_MAX);
	public VolumeComponent volumen4 = new VolumeDeltaComponent(4, 15, 1, 1, 1, 267.6, 267.6 + Constants.VOLUMEN4_LEVEL_MAX);
	*/
	public VolumeLastComponent volumen5 = new VolumeLastComponent(5, 1, 1, 1, 1);
	
	public BarrageComponent hauptkraftwerk = new BarrageComponent(0, 1, 1, 1, 1, 1, 1);
	public BarrageComponent wehr1 = new BarrageComponent(1, 1, 1, 1, 1, 1, 1);
	public BarrageComponent wehr2 = new BarrageComponent(2, 1, 1, 1, 1, 1, 1);
	public BarrageComponent wehr3 = new BarrageComponent(3, 1, 1, 1, 1, 1, 1);
	public BarrageComponent wehr4 = new BarrageComponent(4, 1, 1, 1, 1, 1, 1);
	
	// Constraints
	
	public Constraint levelConstraint = new Constraint(levelConstraintOutput);
	public Constraint dischargeConstraint = new Constraint(dischargeConstraintOutput);
	
	// Expressions
	
	public Expression<Double> hauptkraftwerkTurbineDischarge = new ChannelExpression<>(hauptkraftwerk.turbineDischargeInput, hauptkraftwerkTurbineDischargeInput);
	public Expression<Double> wehr1TurbineDischarge = new ChannelExpression<>(wehr1.turbineDischargeInput, wehr1TurbineDischargeInput);
	public Expression<Double> wehr2TurbineDischarge = new ChannelExpression<>(wehr2.turbineDischargeInput, wehr2TurbineDischargeInput);
	public Expression<Double> wehr3TurbineDischarge = new ChannelExpression<>(wehr3.turbineDischargeInput, wehr3TurbineDischargeInput);
	public Expression<Double> wehr4TurbineDischarge = new ChannelExpression<>(wehr4.turbineDischargeInput, wehr4TurbineDischargeInput);
	
	public Expression<Double> hauptkraftwerkWeirDischarge = new ChannelExpression<>(hauptkraftwerk.weirDischargeInput, hauptkraftwerkWeirDischargeInput);
	public Expression<Double> wehr1WeirDischarge = new ChannelExpression<>(wehr1.weirDischargeInput, wehr1WeirDischargeInput);
	public Expression<Double> wehr2WeirDischarge = new ChannelExpression<>(wehr2.weirDischargeInput, wehr2WeirDischargeInput);
	public Expression<Double> wehr3WeirDischarge = new ChannelExpression<>(wehr3.weirDischargeInput, wehr3WeirDischargeInput);
	public Expression<Double> wehr4WeirDischarge = new ChannelExpression<>(wehr4.weirDischargeInput, wehr4WeirDischargeInput);
	
	public Expression<Double> speicherseeInflow = new ChannelExpression<>(speichersee.inflowInput, scenarioInflowInput);
	public Expression<Double> volumen1Inflow = new ChannelExpression<>(volumen1.inflowInput, hauptkraftwerk.dischargeOutput);
	public Expression<Double> volumen2Inflow = new ChannelExpression<>(volumen2.inflowInput, wehr1.dischargeOutput);
	public Expression<Double> volumen3Inflow = new ChannelExpression<>(volumen3.inflowInput, wehr2.dischargeOutput);
	public Expression<Double> volumen4Inflow = new ChannelExpression<>(volumen4.inflowInput, wehr3.dischargeOutput);
	public Expression<Double> volumen5Inflow = new ChannelExpression<>(volumen5.inflowInput, wehr4.dischargeOutput);
	
	public Expression<Double> speicherseeOutflow = new ChannelExpression<>(speichersee.outflowInput, hauptkraftwerk.dischargeOutput);
	public Expression<Double> volumen1Outflow = new ChannelExpression<>(volumen1.outflowInput, wehr1.dischargeOutput);
	public Expression<Double> volumen2Outflow = new ChannelExpression<>(volumen2.outflowInput, wehr2.dischargeOutput);
	public Expression<Double> volumen3Outflow = new ChannelExpression<>(volumen3.outflowInput, wehr3.dischargeOutput);
	public Expression<Double> volumen4Outflow = new ChannelExpression<>(volumen4.outflowInput, wehr4.dischargeOutput);
	
	public Expression<Double> hauptkraftwerkHeadLevel = new ChannelExpression<>(hauptkraftwerk.headLevelInput, speichersee.levelOutput);
	public Expression<Double> wehr1HeadLevel = new ChannelExpression<>(wehr1.headLevelInput, volumen1.levelOutput);
	public Expression<Double> wehr2HeadLevel = new ChannelExpression<>(wehr2.headLevelInput, volumen2.levelOutput);
	public Expression<Double> wehr3HeadLevel = new ChannelExpression<>(wehr3.headLevelInput, volumen3.levelOutput);
	public Expression<Double> wehr4HeadLevel = new ChannelExpression<>(wehr4.headLevelInput, volumen4.levelOutput);
	
	public Expression<Double> hauptkraftwerkTailLevel = new ChannelExpression<>(hauptkraftwerk.tailLevelInput, volumen1.levelOutput);
	public Expression<Double> wehr1TailLevel = new ChannelExpression<>(wehr1.tailLevelInput, volumen2.levelOutput);
	public Expression<Double> wehr2TailLevel = new ChannelExpression<>(wehr2.tailLevelInput, volumen3.levelOutput);
	public Expression<Double> wehr3TailLevel = new ChannelExpression<>(wehr3.tailLevelInput, volumen4.levelOutput);
	public Expression<Double> wehr4TailLevel = new ChannelExpression<>(wehr4.tailLevelInput, volumen5.levelOutput);

	public Expression<Double> speicherseeLevel = new ChannelExpression<>(speicherseeLevelOutput, speichersee.levelOutput);
	public Expression<Double> volumen1Level = new ChannelExpression<>(volumen1LevelOutput, volumen1.levelOutput);
	public Expression<Double> volumen2Level = new ChannelExpression<>(volumen2LevelOutput, volumen2.levelOutput);
	public Expression<Double> volumen3Level = new ChannelExpression<>(volumen3LevelOutput, volumen3.levelOutput);
	public Expression<Double> volumen4Level = new ChannelExpression<>(volumen4LevelOutput, volumen4.levelOutput);
	public Expression<Double> volumen5Level = new ChannelExpression<>(volumen5LevelOutput, volumen5.levelOutput);
	
	public Expression<Double> hauptkraftwerkProduction = new ChannelExpression<>(hauptkraftwerkProductionOutput, hauptkraftwerk.productionOutput);
	public Expression<Double> wehr1Production = new ChannelExpression<>(wehr1ProductionOutput, wehr1.productionOutput);
	public Expression<Double> wehr2Production = new ChannelExpression<>(wehr2ProductionOutput, wehr2.productionOutput);
	public Expression<Double> wehr3Production = new ChannelExpression<>(wehr3ProductionOutput, wehr3.productionOutput);
	public Expression<Double> wehr4Production = new ChannelExpression<>(wehr4ProductionOutput, wehr4.productionOutput);

	public Expression<Boolean> levelConstraintExpression = new Expression<Boolean>(levelConstraintOutput)
	{
		@Override protected Boolean evaluate(State state, int timepoint)
		{
			boolean valid = true;
			
			valid = valid && speichersee.levelOutput.get(state, timepoint) > volumen1.levelOutput.get(state, timepoint);
			valid = valid && volumen1.levelOutput.get(state, timepoint) > volumen2.levelOutput.get(state, timepoint);
			valid = valid && volumen2.levelOutput.get(state, timepoint) > volumen3.levelOutput.get(state, timepoint);
			valid = valid && volumen3.levelOutput.get(state, timepoint) > volumen4.levelOutput.get(state, timepoint);
			
			return valid;
		}
	};
	public Expression<Boolean> dischargeConstraintExpression = new Expression<Boolean>(dischargeConstraintOutput)
	{
		@Override protected Boolean evaluate(State state, int timepoint)
		{
			double discharge_previous;
			
			if (timepoint == 0)
			{
				discharge_previous = Constants.DATASET_TEST.getOutflowTotal(5, Constants.START + timepoint - 1);
			}
			else
			{
				discharge_previous = wehr4TurbineDischargeInput.get(state, timepoint - 1) + wehr4WeirDischargeInput.get(state, timepoint - 1);
			}
			
			double discharge_current = wehr4TurbineDischargeInput.get(state, timepoint) + wehr4WeirDischargeInput.get(state, timepoint);
			
			double delta = discharge_current / discharge_previous;
			
			return delta >= 0.94 && delta <= 1.13;
		}
	};
	
}
