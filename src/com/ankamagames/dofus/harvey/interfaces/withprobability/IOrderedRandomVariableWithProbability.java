/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.withprobability;

import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedRandomVariableWithProbability<Data>
	extends IRandomVariableWithProbability<Data>,
	IOrderedRandomVariable<Data>
{}