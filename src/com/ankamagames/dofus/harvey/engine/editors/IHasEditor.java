/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IHasEditor<Data, Editor extends IEditor<Data, ? extends IRandomVariable<Data>>>
{
	Editor getEditor();
}
