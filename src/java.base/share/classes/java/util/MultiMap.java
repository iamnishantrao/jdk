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
public interface MultiMap<K, V> {
    // Query Operations

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */
    int size();

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    boolean isEmpty();

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
    boolean containsKey(Object key);

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
    boolean containsValue(Object value);

    /**
     * Returns the values associated with the {@code key}
     *
     * @param key key for which associated values are to be retrived
     * @return collection of all values associated with the key
     */
    Collection<V> get(Object key);

    // Modification Operations

    /**
     * Associates the specified value with the specified key in this multimap.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return {@code true} is the multimap is changed
     */
    boolean put(K key, V value);

    /**
     * Removes a single key-value pair with the key {@code key} and the value {@code value}
     * from this multimap.
     *
     * @param key   key from which the value is to be removed
     * @param value value which is to be removed
     * @return {@code true} is the multimap is changed
     */
    boolean remove(Object key, Object value);

    /**
     * Removes a single key {@code key} and all its value {@code value}
     * from this multimap.
     *
     * @param key   key from which the value is to be removed
     * @return {@code Collection} collection of values
     */
    Collection<V> remove(Object key);

    // Bulk Operations
    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} operation
     *         is not supported by this map
     */
    void clear();

    // Views

    /**
     * Returns a Set view of the keys contained in this map.
     *
     * @return a set view of the keys contained in this map
     */
    Set<K> keySet();

    /**
     * Returns a collection view of the values contained in this multimap.
     *
     * @return a collection view of the values contained in this multimap
     */
    Collection<V> values();

    /**
     * Returns a collection view of all key-value pairs contained in this multimap.
     *
     * @return a collection view of all key-value pairs contained in this multimap
     */
    Collection<Entry<K, V>> entries();

    // Comparison and Hashing

    /**
     * Compares the specified object with this map for equality.
     * @param o object to be compared for equality
     * @return {@code true} if the specified object is equal to this map
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this map.
     * @return the hash code value for this map
     */
    int hashCode();
}
