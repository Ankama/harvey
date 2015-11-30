/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.withprobability;

import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IIRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IRandomVariableWithProbability<Data>
	extends IRandomVariable<Data>, IIRandomVariableWithProbability
{}