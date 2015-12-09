/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableCompositeRandomVariable<Data, ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>>
{
	void add(@Nullable Data value, int probability, ProbabilityStrategiesEnum probabilityStrategy);
	void add(IEditableRandomVariable<Data> randomVariable, int probability, ProbabilityStrategiesEnum probabilityStrategy);
	boolean remove(final IEditableRandomVariable<Data> randomVariable);
}