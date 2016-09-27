/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ElementaryGenericSetCreationToolbox
<
	Data, 
	BridgedType extends IElementaryGenericSet<Data>
>
	extends AbstractGenericSetCreationToolbox<Data, BridgedType>
{
	public ElementaryGenericSetCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}
}