package java.util;

import java.util.*;
import java.util.Map.Entry;

/**
 * An object that maps keys to values.  A map cannot contain duplicate keys;
 * each key can map to at most one value.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public abstract class AbstractMultiMap<K, V> implements MultiMap<K, V> {

    /**
     * Used to hold the key-value pairs in the multimap
     */
    private transient Map<K, Collection<V>> multiMap;

    /**
     * Total number of key-value pairs in the multimap
     */
    private transient int size;

    /**
     * Default constructor for AbstractMultiMap
     */
    protected AbstractMultiMap() {}

    /**
     * Parameterized constructor for AbstractMultimap
     * @param multiMap takes in a Map object
     */
    protected AbstractMultiMap(Map<K, Collection<V>> multiMap) {
        this.multiMap = multiMap;
    }

    // Query Operations

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns {@code true} if this map contains a mapping for the specified
     * key.  More formally, returns {@code true} if and only if
     * this map contains a mapping for a key {@code k} such that
     * {@code Objects.equals(key, k)}.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     * key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map ({@linkplain Collection##optional-restrictions optional})
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys ({@linkplain Collection##optional-restrictions optional})
     */
    public boolean containsKey(Object key) {
        return multiMap.containsKey(key);
    }

    /**
     * Returns {@code true} if this map maps one or more keys to the
     * specified value.  More formally, returns {@code true} if and only if
     * this map contains at least one mapping to a value {@code v} such that
     * {@code Objects.equals(value, v)}.  This operation
     * will probably require time linear in the map size for most
     * implementations of the {@code Map} interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return {@code true} if this map maps one or more keys to the
     * specified value
     * @throws ClassCastException   if the value is of an inappropriate type for
     *                              this map ({@linkplain Collection##optional-restrictions optional})
     * @throws NullPointerException if the specified value is null and this
     *                              map does not permit null values ({@linkplain Collection##optional-restrictions optional})
     */
    public boolean containsValue(Object value) {
        for (Collection<V> collection : multiMap.values()) {
            if (collection.contains(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the values associated with the {@code key}
     *
     * @param key key for which associated values are to be retrived
     * @return collection of all values associated with the key
     */
    public Collection<V> get(Object key) {
        Collection<V> collection = multiMap.get(key);
        if (collection == null) {
            return null;
        } else {
            return collection;
        }
    }

    // Modifications Operations

    // TODO

    /**
     * Associates the specified value with the specified key in this multimap.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return {@code true} is the multimap is changed
     */
    public boolean put(K key, V value) {
        Collection<V> collection = multiMap.get(key);
        if (collection == null) {
            collection = createCollection(key);
            if (collection.add(value)) {
                size++;
                multiMap.put(key, collection);
                return true;
            } else {
                System.out.println("Cannot add value to the collection");
            }
        } else if (collection.add(value)) {
            size++;
            return true;
        }

        return false;
    }

    /**
     * Removes a single key-value pair with the key {@code key} and the value {@code value}
     * from this multimap.
     *
     * @param key   key from which the value is to be removed
     * @param value value which is to be removed
     * @return {@code true} is the multimap is changed
     */
    public boolean remove(Object key, Object value) {
        Collection<V> collection = multiMap.get(key);
        size--;
        return collection != null && collection.remove(value);
    }
	
     /**
     * Removes a single key {@code key} and all its value {@code value}
     * from this multimap.
     *
     * @param key   key from which the value is to be removed
     * @return {@code Collection} collection of values
     */
    public Collection<V> remove(Object key) {
        Collection<V> collection = multiMap.get(key);
        size -= collection.size();
        return multiMap.remove(key);
    }

    private Collection<V> createCollection(K key) {
        return createCollection();
    }

    abstract Collection<V> createCollection();

    // Bulk Operations

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} operation
     *                                       is not supported by this map
     */
    public void clear() {
        for (Collection<V> collection: multiMap.values()) {
            collection.clear();
        }
        multiMap.clear();
        size = 0;
    }

    // Views

    transient Set<K> keySet;
    transient Collection<V> values;
    transient Collection<Entry<K, V>> entries;

    /**
     * Returns a Set view of the keys contained in this map.
     *
     * @return a set view of the keys contained in this map
     */
    public Set<K> keySet() {
        Set<K> ks = keySet;
        if (ks == null) {
            ks = new AbstractSet<K>() {
                public Iterator<K> iterator() {
                    return new Iterator<K>() {
                        private Iterator<K> i = multiMap.keySet().iterator();

                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        public K next() {
                            return i.next();
                        }

                        public void remove() {
                            i.remove();
                        }
                    };
                }

                public int size() {
                    return multiMap.size();
                }

                public boolean isEmpty() {
                    return AbstractMultiMap.this.isEmpty();
                }

                public void clear() {
                    AbstractMultiMap.this.clear();
                }

                public boolean contains(Object k) {
                    return AbstractMultiMap.this.containsKey(k);
                }
            };
            keySet = ks;
        }
        return ks;
    }

    /**
     * Returns a collection view of the values contained in this multimap.
     *
     * @return a collection view of the values contained in this multimap
     */
    public Collection<V> values() {
        Collection<V> vals = values;
        if (vals == null) {
            vals = new AbstractCollection<V>() {

                public Iterator<V> iterator() {
                    return new Iterator<V>() {
                        private Iterator<Entry<K, V>> i = entries().iterator();

                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        public V next() {
                            return i.next().getValue();
                        }

                        public void remove() {
                            i.remove();
                        }
                    };
                }

                public int size() {
                    return AbstractMultiMap.this.size();
                }

                public boolean isEmpty() {
                    return AbstractMultiMap.this.isEmpty();
                }

                public void clear() {
                    AbstractMultiMap.this.clear();
                }

                public boolean contains(Object v) {
                    return AbstractMultiMap.this.containsValue(v);
                }
            };

            values = vals;
        }
        return vals;
    }

    /**
     * Returns a collection view of all key-value pairs contained in this multimap.
     *
     * @return a collection view of all key-value pairs contained in this multimap
     */
    public Collection<Entry<K, V>> entries() {
        Collection<Entry<K, V>> collection = entries;
        return (collection == null) ? (entries = new Entries()) : collection;
    }

    final class Entries extends AbstractCollection<Entry<K, V>> {
        public int size() {
            return size;
        }
        public void clear() {
            this.clear();
        }
        public Iterator<Entry<K, V>> iterator() {
            return new EntriesIterator();
        }
    }

    final class EntriesIterator implements Iterator<Entry<K, V>> {
        Iterator<Entry<K, Collection<V>>> keyIterator = multiMap.entrySet().iterator();
        Iterator<V> valueIterator = new Iterator<V>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public V next() {
                return null;
            }
        };
        K key;
        public boolean hasNext() {
            return keyIterator.hasNext() || valueIterator.hasNext();
        }

        public Entry<K, V> next() {
            if (!valueIterator.hasNext()) {
                Entry<K, Collection<V>> entry = keyIterator.next();
                key = entry.getKey();
                valueIterator = entry.getValue().iterator();
            }
            return new AbstractMap.SimpleEntry<>(key, valueIterator.next());
        }
    }

    // Comparison and hashing

    public String toString() {
        return multiMap.toString();
    }

    public boolean equals(Object o) {
        return this == o || multiMap.equals(o);
    }

    public int hashCode() {
        return multiMap.hashCode();
    }
}
