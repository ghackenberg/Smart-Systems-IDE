package org.xtream.core.model;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import org.xtream.core.model.annotations.Constraint;
import org.xtream.core.model.annotations.Dominance;
import org.xtream.core.model.annotations.Equivalence;
import org.xtream.core.model.annotations.Objective;

public abstract class Component
{
	
	public String name;
	
	public Collection<Port<?>> ports = new ArrayList<>();
	public Collection<Component> components = new ArrayList<>();
	public Collection<Channel<?>> channels = new ArrayList<>();
	public Collection<Port<Boolean>> constraints= new ArrayList<>();
	public Collection<Port<Double>> minDominances= new ArrayList<>();
	public Collection<Port<Double>> maxDominances= new ArrayList<>();
	public Collection<Port<?>> equivalences= new ArrayList<>();
	public Collection<Port<Double>> minObjectives= new ArrayList<>();
	public Collection<Port<Double>> maxObjectives= new ArrayList<>();
	
	public Collection<Port<?>> portsRecursive = new ArrayList<>();
	public Collection<Component> componentsRecursive = new ArrayList<>();
	public Collection<Channel<?>> channelsRecursive = new ArrayList<>();
	public Collection<Port<Boolean>> constraintsRecursive= new ArrayList<>();
	public Collection<Port<Double>> minDominancesRecursive = new ArrayList<>();
	public Collection<Port<Double>> maxDominancesRecursive = new ArrayList<>();
	public Collection<Port<?>> equivalencesRecursive= new ArrayList<>();
	public Collection<Port<Double>> minObjectivesRecursive= new ArrayList<>();
	public Collection<Port<Double>> maxObjectivesRecursive= new ArrayList<>();
	
	public void init()
	{
		init("root");
	}
	
	@SuppressWarnings("unchecked")
	public void init(String name)
	{
		this.name = name;
		
		for (Field field : this.getClass().getFields())
		{
			try
			{
				if (Port.class.isAssignableFrom(field.getType()))
				{
					Port<?> port = (Port<?>) field.get(this);
					
					ports.add(port);
					
					port.name = name + "." + field.getName();
					
					if (field.getAnnotation(Constraint.class) != null && (Port<Boolean>) port != null)
					{
						constraints.add((Port<Boolean>) port);
					}
					else if (field.getAnnotation(Dominance.class) != null && (Port<Double>) port != null)
					{
						if (field.getAnnotation(Dominance.class).value() == Dominance.Value.MAX)
						{
							maxDominances.add((Port<Double>) port);
						}
						else
						{
							minDominances.add((Port<Double>) port);	
						}
					}
					else if (field.getAnnotation(Equivalence.class) != null)
					{
						equivalences.add((Port<?>) port);
					}
					else if (field.getAnnotation(Objective.class) != null && (Port<Double>) port != null)
					{
						if (field.getAnnotation(Objective.class).value() == Objective.Value.MAX)
						{
							maxObjectives.add((Port<Double>) port);
						}
						else
						{
							minObjectives.add((Port<Double>) port);
						}
					}
				}
				else if (Component.class.isAssignableFrom(field.getType()))
				{
					Component component = (Component) field.get(this);
					
					components.add(component);
					
					component.init(name + "." + field.getName());
					
					portsRecursive.addAll(component.portsRecursive);
					componentsRecursive.addAll(component.componentsRecursive);
					channelsRecursive.addAll(component.channelsRecursive);
					constraintsRecursive.addAll(component.constraintsRecursive);
					minDominancesRecursive.addAll(component.minDominancesRecursive);
					maxDominancesRecursive.addAll(component.maxDominancesRecursive);
					equivalencesRecursive.addAll(component.equivalencesRecursive);
					minObjectivesRecursive.addAll(component.minObjectivesRecursive);
					maxObjectivesRecursive.addAll(component.maxObjectivesRecursive);
				}
				else if (Channel.class.isAssignableFrom(field.getType()))
				{
					Channel<?> channel = (Channel<?>) field.get(this);
					
					channels.add(channel);
					
					channel.name = name + "." + field.getName();
				}
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		
		portsRecursive.addAll(ports);
		componentsRecursive.addAll(components);
		channelsRecursive.addAll(channels);
		constraintsRecursive.addAll(constraints);
		minDominancesRecursive.addAll(minDominances);
		maxDominancesRecursive.addAll(maxDominances);
		equivalencesRecursive.addAll(equivalences);
		minObjectivesRecursive.addAll(minObjectives);
		maxObjectivesRecursive.addAll(maxObjectives);
	}
	
	public void dump(PrintStream out)
	{
		dump(out, 0);
	}
	
	private void dump(PrintStream out, int indent)
	{
		tabs(out, indent);
		
		out.println(name);
		
		for (Component component : components)
		{
			component.dump(out, indent + 1);
		}
	}
	
	private void tabs(PrintStream out, int indent)
	{
		for (int i = 0; i < indent; i++)
		{
			out.print("\t");
		}
	}

}
