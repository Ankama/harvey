/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.singlevalues;

import com.ankamagames.dofus.harvey.engine.inetrfaces.singlevalues.IIEditableSingleValueRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableSingleValueRandomVariable<Data>
	extends ISingleValueRandomVariable<Data>,
	IIEditableSingleValueRandomVariable<Data>
{

}
