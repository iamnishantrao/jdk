package java.util;

import java.util.*;

/**
 * An object that maps keys to values.  A map cannot contain duplicate keys;
 * each key can map to at most one value.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
@SuppressWarnings("unchecked")
public class TreeMultiMap<K, V> extends AbstractMultiMap<K, V>
        implements MultiMap<K, V> {

    private transient Comparator<K> keyComparator;
    private transient Comparator<V> valueComparator;

    /**
     * Default constructor for TreeMultiMap
     */
    public TreeMultiMap() {
        this((Comparator<K>) Comparator.naturalOrder(), (Comparator<V>) Comparator.naturalOrder());
    }

    /**
     * Parameterized constructor for TreeMultiMap
     * @param keyComparator key comparator to be used
     * @param valueComparator value comparator to be used
     */
    public TreeMultiMap(Comparator<K> keyComparator, Comparator<V> valueComparator) {
        super(new TreeMap<>(keyComparator));
        this.keyComparator = keyComparator;
        this.valueComparator = valueComparator;
    }

    SortedSet<V> createCollection() {
        return new TreeSet<>(valueComparator);
    }
}
