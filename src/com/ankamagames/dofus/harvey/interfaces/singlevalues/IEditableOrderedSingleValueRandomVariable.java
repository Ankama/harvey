/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.singlevalues;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableOrderedSingleValueRandomVariable<Data>
	extends IEditableSingleValueRandomVariable<Data>,
	IOrderedSingleValueRandomVariable<Data>
{}