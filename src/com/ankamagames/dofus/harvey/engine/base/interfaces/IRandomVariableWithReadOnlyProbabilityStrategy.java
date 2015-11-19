/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IRandomVariableWithReadOnlyProbabilityStrategy<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>>
extends	IRandomVariableWithProbabilityStrategy<Data, ParentType, ReadOnlyProbabilityStrategy>
{}
