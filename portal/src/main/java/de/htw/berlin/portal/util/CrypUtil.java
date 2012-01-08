package de.htw.berlin.portal.util;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * @author <a href="mailto:pat.dahms@googlemail.com">Patrick Dahms</a>
 * @since 08.01.12, 18:23
 */
public class CrypUtil {
  
  public static String getCryptedPassword( final String password ) {
    final HashFunction hashFunction = Hashing.sha256();
    return hashFunction.hashString( password ).toString();
  }
  
}
