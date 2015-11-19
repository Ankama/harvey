package com.ankamagames.dofus.harvey.interfaces;

import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 *
 * capable de donner la probabilit� de pr�sence de la ou des valeurs contenues par le type impl�mentant cette interface
 *
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>>
extends IRandomVariable<Data, ParentType>
{
	void setParent(@Nullable ParentType parent) throws OverOneProbabilityException;
}
