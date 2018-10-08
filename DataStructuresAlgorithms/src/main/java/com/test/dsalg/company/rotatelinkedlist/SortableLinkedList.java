package com.test.dsalg.company.rotatelinkedlist;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class SortableLinkedList<E> extends LinkedList<E> {

	private static final long serialVersionUID = -5143721468573124264L;

	public void sortIndexBased(int indexSorting) {
		IntStream.range(0, indexSorting).forEach(i -> {
			E e = poll();
			addLast(e);
		});
	}

}
