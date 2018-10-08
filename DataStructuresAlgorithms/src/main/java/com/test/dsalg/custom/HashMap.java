package com.test.dsalg.custom;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V> {

	private int capacity;
	private Entry<K, V>[] table;

	@SuppressWarnings("all")
	public HashMap() {
		capacity = 16;
		table = new Entry[capacity];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		int bucketIndex = key.hashCode() % capacity;
		if (table[bucketIndex] == null) {
			return null;
		}

		Entry<K, V> current = table[bucketIndex];
		do {
			if (current.key.equals(key)) {
				return current.value;
			}
		} while (current.next != null);

		return null;
	}

	@Override
	public V put(K key, V value) {
		int bucketIndex = key.hashCode() % capacity;
		if (table[bucketIndex] == null) {
			table[bucketIndex] = new Entry<>(key, value);
			return value;
		}

		Entry<K, V> current = table[bucketIndex];
		Entry<K, V> entry = new Entry<>(key, value);
		do {
			if (current.key.equals(entry.key)) {
				current.value = value;
				return value;
			}
		} while (current.next != null);

		current.next = entry;
		return entry.value;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	static class Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this(key, value, null);
		}

		public Entry(K key, V value, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
}
