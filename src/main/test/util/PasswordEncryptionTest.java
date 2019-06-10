package util;

import com.beard.util.PasswordEncryption;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordEncryptionTest {

    @Test
    public void shouldEncryptPassword(){
        String actual = PasswordEncryption.encryption("qwerty11");
        String expected = PasswordEncryption.encryption("qwerty11");
        String beforeEncryption = "qwerty11";
        assertEquals(expected,actual);
        assertNotEquals(beforeEncryption,actual);
    }
}
