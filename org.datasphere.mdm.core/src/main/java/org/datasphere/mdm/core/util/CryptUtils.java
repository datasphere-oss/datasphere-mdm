

package org.datasphere.mdm.core.util;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * @author Mikhail Mikhailov
 * Collect crypto stuff in a single place.
 */
public class CryptUtils {

    private static final HashFunction MURMUR3_128 = Hashing.murmur3_128();

    private CryptUtils() {
        super();
    }

    public static String toMurmurString(String val) {
        return Objects.isNull(val) ? null : MURMUR3_128.hashString(val, StandardCharsets.UTF_8).toString();
    }

    public static String toMurmurString(Long val) {
        return Objects.isNull(val) ? null : MURMUR3_128.hashLong(val).toString();
    }

    public static String toMurmurString(byte[] val) {
        return Objects.isNull(val) ? null : MURMUR3_128.hashBytes(val).toString();
    }
}
