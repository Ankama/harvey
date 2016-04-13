/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.randomvariables.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IOrderedGenericSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedGenericRandomVariable<Data, ElementType extends IOrderedGenericSet<Data>>
	extends IRandomVariable<IOrderedGenericSet<Data>, IOrderedGenericRandomVariable<Data, ?>, ElementType>
{}