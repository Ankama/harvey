/**
 *
 */
package com.ankamagames.dofus.harvey.generic.randomvariables.interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IElementaryEvent;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IGenericElementaryEvent<Data, ElementType extends IDegenerateGenericSet<Data>>
extends IGenericRandomVariable<Data, ElementType>,
IElementaryEvent<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IDegenerateGenericSet<Data>, IGenericRandomVariable<Data, ?>, IGenericElementaryEvent<Data, ?>, ElementType>
{}