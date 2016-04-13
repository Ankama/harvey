/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.randomvariables.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IElementaryEvent;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IGenericElementaryEvent<Data, ElementType extends IDegenerateGenericSet<Data>>
	extends IGenericRandomVariable<Data, ElementType>, IElementaryEvent<IGenericSet<Data>, IGenericElementaryEvent<Data, ?>, ElementType>
{}