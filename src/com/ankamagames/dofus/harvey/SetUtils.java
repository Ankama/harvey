/**
 *
 */
package com.ankamagames.dofus.harvey;

import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.generic.sets.classes.BaseDegenerateGenericSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.BaseGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class SetUtils
{
	public static <Data> IGenericSet<Data> getIntersection(final IGenericSet<Data> s1, final IGenericSet<Data> s2)
	{
		if(s1.isDegenerate())
			if(s2.isDegenerate())
				if(s1.equals(s2))
					return BaseDegenerateGenericSet.makeSet(s1.iterator().next());
				else
					 return EmptyGenericSet.getInstance();
			else
				if(s2.contains(s1))
					return BaseDegenerateGenericSet.makeSet(s1.iterator().next());
				else
					 return EmptyGenericSet.getInstance();
		else
			if(s2.isDegenerate())
				if(s1.contains(s2))
					return BaseDegenerateGenericSet.makeSet(s2.iterator().next());
				else
					 return EmptyGenericSet.getInstance();
			else
			{
				final Iterator<Data> it = s1.iterator();
				final HashSet<BaseDegenerateGenericSet<Data>> items = new HashSet<BaseDegenerateGenericSet<Data>>((int)Math.max(s1.size(), s2.size()));
				while(it.hasNext())
				{
					final Data item = it.next();
					if(s2.contains(item))
					{
						items.add(BaseDegenerateGenericSet.makeSet(item));
					}
				}
				return BaseGenericSet.makeSet(items);
			}
	}
}