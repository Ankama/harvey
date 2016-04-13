/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.randomvariables.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IGenericRandomVariable<Data, ElementType extends IGenericSet<Data>>
extends IRandomVariable<IGenericSet<Data>, IGenericElementaryEvent<Data, ?>, ElementType>
{
	int getProbabilityOf(Data value);
}