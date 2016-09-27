/**
 *
 */
package com.ankamagames.dofus.harvey.generic.randomvariables.interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedGenericRandomVariable<Data, ElementType extends ISortedGenericSet<Data>>
extends IIGenericRandomVariable<Data>,
ISortedRandomVariable<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IDegenerateSortedGenericSet<Data>, ISortedGenericRandomVariable<Data, ?>, ISortedGenericElementaryEvent<Data, ?>, ElementType>
{}