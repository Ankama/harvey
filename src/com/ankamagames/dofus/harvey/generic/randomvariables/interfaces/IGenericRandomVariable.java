/**
 *
 */
package com.ankamagames.dofus.harvey.generic.randomvariables.interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IGenericRandomVariable<Data, ElementType extends IGenericSet<Data>>
extends IIGenericRandomVariable<Data>,
IRandomVariable<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IGenericRandomVariable<Data, ?>, IGenericElementaryEvent<Data, ?>, ElementType>
{}