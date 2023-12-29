package java.util;

import java.util.*;

/**
 * An object that maps keys to values.  A map cannot contain duplicate keys;
 * each key can map to at most one value.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class ArrayListMultiMap<K, V> extends AbstractMultiMap<K, V>
        implements MultiMap<K, V> {

    private static final int DEFAULT_VALUES_PER_KEY = 3;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    transient int valuesPerKey;

    /**
     * Default constroctor for ArrayListMultiMap
     */
    public ArrayListMultiMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_VALUES_PER_KEY);
    }

    /**
     * Parameterized constructor for ArrayListMultiMap
     * @param size the size of multimap
     * @param valuesPerKey values per key
     */
    public ArrayListMultiMap(int size, int valuesPerKey) {
        super(new HashMap<>(size));
        this.valuesPerKey = valuesPerKey;
    }

    Collection<V> createCollection() {
        return new ArrayList<>(this.valuesPerKey);
    }
}
