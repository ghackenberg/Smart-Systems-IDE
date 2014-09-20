package org.xtream.demo.hydro.model;

import org.xtream.core.model.Chart;
import org.xtream.core.model.Expression;
import org.xtream.core.model.charts.Series;
import org.xtream.core.model.charts.Timeline;
import org.xtream.core.model.containers.Component;
import org.xtream.core.model.expressions.ChannelExpression;
import org.xtream.core.model.markers.Objective;
import org.xtream.core.model.markers.objectives.MaxObjective;
import org.xtream.core.workbench.Workbench;

public class RootComponent extends Component
{
	
	// Main
	
	public static void main(String[] args)
	{
		new Workbench<>(new RootComponent(), Constants.DURATION, Constants.SAMPLES, Constants.CLUSTERS, Constants.RANDOM, Constants.CACHING, Constants.ROUNDS, Constants.PRUNE);
	}
	
	// Components
	
	// Reactive Components

	public ScenarioComponent scenario = new ScenarioComponent();
	public ControlComponent control_reactive = new org.xtream.demo.hydro.model.control.actual.ControlComponent();
	//public ControlComponent control_reactive = new org.xtream.demo.hydro.model.control.reactive.single.continuous.ControlComponent();
	//public ControlComponent control_reactive = new org.xtream.demo.hydro.model.control.reactive.single.discrete.ControlComponent();
	//public ControlComponent control_reactive = new org.xtream.demo.hydro.model.control.reactive.split.continuous.ControlComponent();
	//public ControlComponent control_reactive = new org.xtream.demo.hydro.model.control.reactive.split.discrete.ControlComponent();
	public org.xtream.demo.hydro.model.context.reactive.ContextComponent context_reactive = new org.xtream.demo.hydro.model.context.reactive.ContextComponent();
	public ObjectiveComponent objective_reactive = new ObjectiveComponent();
	public EquivalenceComponent equivalence_reactive = new EquivalenceComponent();
	
	// Actual Components

	public org.xtream.demo.hydro.model.context.actual.ContextComponent context_actual = new org.xtream.demo.hydro.model.context.actual.ContextComponent();
	public ObjectiveComponent objective_actual = new ObjectiveComponent();
	
	// Objectives
	
	public Objective objectiveMarker = new MaxObjective(objective_reactive.objectiveOutput);
	
	// Charts

	public Chart inflowChart = new Timeline("Zufluss Bigonville", "Viertelstunde", "Kubikmeter pro Sekunde", new Series<>("Zufluss Bigonville", scenario.inflowOutput));
	public Chart priceChart = new Timeline("Energiepreis", "Viertelstunde", "Euro", new Series<>("Energiepreis", scenario.priceOutput));
	public Chart objectiveChart = new Timeline("Zielfunktion", "Viertelstunde", "Euro", new Series<>("Umsatz gesch�tzt", objective_reactive.rewardOutput), new Series<>("Kosten gesch�tzt", objective_reactive.costOutput), new Series<>("Gewinn gesch�tzt", objective_reactive.objectiveOutput), new Series<>("Umsatz gemessen", objective_actual.rewardOutput), new Series<>("Kosten gemessen", objective_actual.costOutput), new Series<>("Gewinn gemessen", objective_actual.objectiveOutput));

	public Chart productionChart = new Timeline("Vergleich Energieproduktion", "Viertelstunde", "Kilowatt", new Series<>("Produktion gesch�tzt", context_reactive.netProductionOutput), new Series<>("Produktion gemessen", context_actual.netProductionOutput));
	public Chart speicherseeLevelChart = new Timeline("Vergleich Speicherseepegel", "Viertelstunde", "Meter", new Series<>("Pegel gesch�tzt", context_reactive.speicherseeLevelOutput), new Series<>("Pegel gemessen", context_actual.speicherseeLevelOutput));
	public Chart volumen1LevelChart = new Timeline("Vergleich Segmentpegel 1", "Viertelstunde", "Meter", new Series<>("Pegel gesch�tzt", context_reactive.volumen1LevelOutput), new Series<>("Pegel gemessen", context_actual.volumen1LevelOutput));
	public Chart volumen2LevelChart = new Timeline("Vergleich Segmentpegel 2", "Viertelstunde", "Meter", new Series<>("Pegel gesch�tzt", context_reactive.volumen2LevelOutput), new Series<>("Pegel gemessen", context_actual.volumen2LevelOutput));
	public Chart volumen3LevelChart = new Timeline("Vergleich Segmentpegel 3", "Viertelstunde", "Meter", new Series<>("Pegel gesch�tzt", context_reactive.volumen3LevelOutput), new Series<>("Pegel gemessen", context_actual.volumen3LevelOutput));
	public Chart volumen4LevelChart = new Timeline("Vergleich Segmentpegel 4", "Viertelstunde", "Meter", new Series<>("Pegel gesch�tzt", context_reactive.volumen4LevelOutput), new Series<>("Pegel gemessen", context_actual.volumen4LevelOutput));
	public Chart volumen5LevelChart = new Timeline("Vergleich Heidescheider Grundpegel", "Viertelstunde", "Meter", new Series<>("Pegel gesch�tzt", context_reactive.volumen5LevelOutput), new Series<>("Pegel gemessen", context_actual.volumen5LevelOutput));
	
	// Expressions
 
	public Expression<Double> inflowToContext = new ChannelExpression<>(context_reactive.inflowInput, scenario.inflowOutput);
	
	public Expression<Double> inflowToControl = new ChannelExpression<>(control_reactive.inflowInput, scenario.inflowOutput);
	public Expression<Double> priceToControl = new ChannelExpression<>(control_reactive.priceInput, scenario.priceOutput);
	
	public Expression<Double> speicherseeLevelToControl = new ChannelExpression<>(control_reactive.speicherseeLevelInput, context_reactive.speicherseeLevelOutput);
	public Expression<Double> volumen1LevelToControl = new ChannelExpression<>(control_reactive.volumen1LevelInput, context_reactive.volumen1LevelOutput);
	public Expression<Double> volumen2LevelToControl = new ChannelExpression<>(control_reactive.volumen2LevelInput, context_reactive.volumen2LevelOutput);
	public Expression<Double> volumen3LevelToControl = new ChannelExpression<>(control_reactive.volumen3LevelInput, context_reactive.volumen3LevelOutput);
	public Expression<Double> volumen4LevelToControl = new ChannelExpression<>(control_reactive.volumen4LevelInput, context_reactive.volumen4LevelOutput);
	
	public Expression<Double> speicherseeLevelToEquivalence = new ChannelExpression<>(equivalence_reactive.speicherseeLevelInput, context_reactive.speicherseeLevelOutput);
	public Expression<Double> volumen1LevelToEquivalence = new ChannelExpression<>(equivalence_reactive.volumen1LevelInput, context_reactive.volumen1LevelOutput);
	public Expression<Double> volumen2LevelToEquivalence = new ChannelExpression<>(equivalence_reactive.volumen2LevelInput, context_reactive.volumen2LevelOutput);
	public Expression<Double> volumen3LevelToEquivalence = new ChannelExpression<>(equivalence_reactive.volumen3LevelInput, context_reactive.volumen3LevelOutput);
	public Expression<Double> volumen4LevelToEquivalence = new ChannelExpression<>(equivalence_reactive.volumen4LevelInput, context_reactive.volumen4LevelOutput);
	
	public Expression<Double> hauptkraftwerkTurbineDischargeToContext = new ChannelExpression<>(context_reactive.hauptkraftwerkTurbineDischargeInput, control_reactive.hauptkraftwerkTurbineDischargeOutput);
	public Expression<Double> wehr1TurbineDischargeToContext = new ChannelExpression<>(context_reactive.wehr1TurbineDischargeInput, control_reactive.wehr1TurbineDischargeOutput);
	public Expression<Double> wehr2TurbineDischargeToContext = new ChannelExpression<>(context_reactive.wehr2TurbineDischargeInput, control_reactive.wehr2TurbineDischargeOutput);
	public Expression<Double> wehr3TurbineDischargeToContext = new ChannelExpression<>(context_reactive.wehr3TurbineDischargeInput, control_reactive.wehr3TurbineDischargeOutput);
	public Expression<Double> wehr4TurbineDischargeToContext = new ChannelExpression<>(context_reactive.wehr4TurbineDischargeInput, control_reactive.wehr4TurbineDischargeOutput);
	
	public Expression<Double> hauptkraftwerkWeirDischargeToContext = new ChannelExpression<>(context_reactive.hauptkraftwerkWeirDischargeInput, control_reactive.hauptkraftwerkWeirDischargeOutput);
	public Expression<Double> wehr1WeirDischargeToContext = new ChannelExpression<>(context_reactive.wehr1WeirDischargeInput, control_reactive.wehr1WeirDischargeOutput);
	public Expression<Double> wehr2WeirDischargeToContext = new ChannelExpression<>(context_reactive.wehr2WeirDischargeInput, control_reactive.wehr2WeirDischargeOutput);
	public Expression<Double> wehr3WeirDischargeToContext = new ChannelExpression<>(context_reactive.wehr3WeirDischargeInput, control_reactive.wehr3WeirDischargeOutput);
	public Expression<Double> wehr4WeirDischargeToContext = new ChannelExpression<>(context_reactive.wehr4WeirDischargeInput, control_reactive.wehr4WeirDischargeOutput);
	
	public Expression<Double> hauptkraftwerkTurbineDischargeToObjective = new ChannelExpression<>(objective_reactive.hauptkraftwerkTurbineDischargeInput, context_reactive.hauptkraftwerkTurbineDischargeOutput);
	public Expression<Double> wehr1TurbineDischargeToObjective = new ChannelExpression<>(objective_reactive.wehr1TurbineDischargeInput, context_reactive.wehr1TurbineDischargeOutput);
	public Expression<Double> wehr2TurbineDischargeToObjective = new ChannelExpression<>(objective_reactive.wehr2TurbineDischargeInput, context_reactive.wehr2TurbineDischargeOutput);
	public Expression<Double> wehr3TurbineDischargeToObjective = new ChannelExpression<>(objective_reactive.wehr3TurbineDischargeInput, context_reactive.wehr3TurbineDischargeOutput);
	public Expression<Double> wehr4TurbineDischargeToObjective = new ChannelExpression<>(objective_reactive.wehr4TurbineDischargeInput, context_reactive.wehr4TurbineDischargeOutput);
	
	public Expression<Double> hauptkraftwerkWeirDischargeToObjective = new ChannelExpression<>(objective_reactive.hauptkraftwerkWeirDischargeInput, context_reactive.hauptkraftwerkWeirDischargeOutput);
	public Expression<Double> wehr1WeirDischargeToObjective = new ChannelExpression<>(objective_reactive.wehr1WeirDischargeInput, context_reactive.wehr1WeirDischargeOutput);
	public Expression<Double> wehr2WeirDischargeToObjective = new ChannelExpression<>(objective_reactive.wehr2WeirDischargeInput, context_reactive.wehr2WeirDischargeOutput);
	public Expression<Double> wehr3WeirDischargeToObjective = new ChannelExpression<>(objective_reactive.wehr3WeirDischargeInput, context_reactive.wehr3WeirDischargeOutput);
	public Expression<Double> wehr4WeirDischargeToObjective = new ChannelExpression<>(objective_reactive.wehr4WeirDischargeInput, context_reactive.wehr4WeirDischargeOutput);
	
	public Expression<Double> productionToObjective = new ChannelExpression<>(objective_reactive.productionInput, context_reactive.netProductionOutput);
	public Expression<Double> priceToObjective = new ChannelExpression<>(objective_reactive.priceInput, scenario.priceOutput);
	
	public Expression<Double> actualHauptkraftwerkTurbineDischargeToObjective = new ChannelExpression<>(objective_actual.hauptkraftwerkTurbineDischargeInput, context_actual.hauptkraftwerkTurbineDischargeOutput);
	public Expression<Double> actualWehr1TurbineDischargeToObjective = new ChannelExpression<>(objective_actual.wehr1TurbineDischargeInput, context_actual.wehr1TurbineDischargeOutput);
	public Expression<Double> actualWehr2TurbineDischargeToObjective = new ChannelExpression<>(objective_actual.wehr2TurbineDischargeInput, context_actual.wehr2TurbineDischargeOutput);
	public Expression<Double> actualWehr3TurbineDischargeToObjective = new ChannelExpression<>(objective_actual.wehr3TurbineDischargeInput, context_actual.wehr3TurbineDischargeOutput);
	public Expression<Double> actualWehr4TurbineDischargeToObjective = new ChannelExpression<>(objective_actual.wehr4TurbineDischargeInput, context_actual.wehr4TurbineDischargeOutput);
	
	public Expression<Double> actualHauptkraftwerkWeirDischargeToObjective = new ChannelExpression<>(objective_actual.hauptkraftwerkWeirDischargeInput, context_actual.hauptkraftwerkWeirDischargeOutput);
	public Expression<Double> actualWehr1WeirDischargeToObjective = new ChannelExpression<>(objective_actual.wehr1WeirDischargeInput, context_actual.wehr1WeirDischargeOutput);
	public Expression<Double> actualWehr2WeirDischargeToObjective = new ChannelExpression<>(objective_actual.wehr2WeirDischargeInput, context_actual.wehr2WeirDischargeOutput);
	public Expression<Double> actualWehr3WeirDischargeToObjective = new ChannelExpression<>(objective_actual.wehr3WeirDischargeInput, context_actual.wehr3WeirDischargeOutput);
	public Expression<Double> actualWehr4WeirDischargeToObjective = new ChannelExpression<>(objective_actual.wehr4WeirDischargeInput, context_actual.wehr4WeirDischargeOutput);
	
	public Expression<Double> actualProductionToObjective = new ChannelExpression<>(objective_actual.productionInput, context_actual.netProductionOutput);
	public Expression<Double> actualPriceToObjective = new ChannelExpression<>(objective_actual.priceInput, scenario.priceOutput);

}
