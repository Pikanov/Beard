package util;

import com.beard.util.Validator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void shouldValidateEmail(){
        String email = "java@gmail.com";
        String wrongEmail = "gmail.com";

        assertTrue(Validator.isEmailValid(email));
        assertFalse(Validator.isEmailValid(wrongEmail));
    }

    @Test
    public void shouldValidatePassword(){
        String password = "qwerty11";
        String wrongPassword = "qwerty";

        assertTrue(Validator.isPasswordValid(password));
        assertFalse(Validator.isPasswordValid(wrongPassword));
    }

    @Test
    public void shouldValidatePhoneNumber(){
        String phoneNumber = "0632663399";
        String wrongPhoneNumber = "+38(063)266-33-99 ";

        assertTrue(Validator.isPhoneNumberValid(phoneNumber));
        assertFalse(Validator.isPhoneNumberValid(wrongPhoneNumber));
    }
}
