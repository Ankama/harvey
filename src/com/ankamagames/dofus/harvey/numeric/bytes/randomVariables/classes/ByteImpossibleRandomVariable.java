/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.classes;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractImpossibleRandomVariable;
import com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.Interfaces.IByteElementaryEvent;
import com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.Interfaces.IByteRandomVariable;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.EmptyByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ByteImpossibleRandomVariable
extends AbstractImpossibleRandomVariable<IByteSet, ISimpleByteSet, IElementaryByteSet, IEmptyByteSet, IByteRandomVariable, IByteElementaryEvent> {

	@SuppressWarnings("rawtypes")
	private static ByteImpossibleRandomVariable _instance = new ByteImpossibleRandomVariable();

	@SuppressWarnings("unchecked")
	public static ByteImpossibleRandomVariable getInstance()
	{
		return _instance;
	}

	@Override
	public IEmptyByteSet getElements()
	{
		return EmptyByteSet.getInstance();
	}

}
