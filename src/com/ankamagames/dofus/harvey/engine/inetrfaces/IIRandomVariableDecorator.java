/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIRandomVariableDecorator<Data, Decorated extends IRandomVariable<Data>>
{
	Decorated getDecoratedRandomVariable();
}