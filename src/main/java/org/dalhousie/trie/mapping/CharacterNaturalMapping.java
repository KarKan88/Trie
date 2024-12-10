/*
 * Copyright 2010 Christos Gioran
 *
 * This file is part of DoubleArrayTrie.
 *
 * DoubleArrayTrie is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DoubleArrayTrie is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DoubleArrayTrie.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dalhousie.trie.mapping;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @author Chris Gioran
 *
 */
public class CharacterNaturalMapping implements NaturalMapping<Character> {

	private static final int N = Character.MAX_VALUE;
	private static final CharacterNaturalMapping instance = 
		new CharacterNaturalMapping();

	/**
	 * Private constructor, this is a Singleton.
	 */
	private CharacterNaturalMapping() {
	}

	public static CharacterNaturalMapping getInstance() {
		return instance;
	}

	/**
	 * @see main.org.digitalstain.datrie.mapping.NaturalMapping#getN()
	 */
	@Override
	public int getN() {
		return N;
	}

	/**
	 * @see main.org.digitalstain.datrie.mapping.NaturalMapping#fromNatural(int)
	 */
	@Override
	public Character fromNatural(int i) {
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.putInt(i);
		bb.flip();
		return Charset.defaultCharset().decode(bb).get(3);
	}

	/**
	 * @see main.org.digitalstain.datrie.mapping.NaturalMapping#toNatural(java.lang.Object)
	 */
	@Override
	public int toNatural(Character object) {
		CharBuffer cb = CharBuffer.allocate(1);
		cb.put(object.charValue());
		cb.flip();
		ByteBuffer bb = Charset.defaultCharset().encode(cb);
		return bb.get();
	}

	/**
	 * @see main.org.digitalstain.datrie.mapping.NaturalMapping#getUnmapped()
	 */
	@Override
	public Character getUnmapped() {
		return null;
	}
}
