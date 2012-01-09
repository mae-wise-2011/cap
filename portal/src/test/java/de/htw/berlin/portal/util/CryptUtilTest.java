package de.htw.berlin.portal.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pat.dahms@googlemail.com">Patrick Dahms</a>
 * @since 08.01.12, 18:27
 */
public class CryptUtilTest {

  @Test
  public void testGetCryptedPassword() throws Exception {
    final String cryptedPassword = CrypUtil.getCryptedPassword( "1234" );
    assertNotNull( cryptedPassword );
  }
}
