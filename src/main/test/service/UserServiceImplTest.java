package service;

import com.beard.entity.Role;
import com.beard.entity.User;
import com.beard.repository.UserRepository;
import com.beard.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class UserServiceImplTest {

    private User user = User.builder()
            .withUserId(1L)
            .withFirstName("Alex")
            .withLastName("Petrov")
            .withEmail("petrov@gmail.com")
            .withPassword("123456789")
            .withPhoneNumber("0632773351")
            .withRole(new Role(1L, "user"))
            .build();

    private User master = User.builder()
            .withUserId(2L)
            .withFirstName("Alex")
            .withLastName("Petrov")
            .withEmail("petrov2@gmail.com")
            .withPassword("123456789")
            .withPhoneNumber("0632773351")
            .withRole(new Role(3L, "master"))
            .build();

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void defineByIdInvokeEmpty() {
        Long findById = 1L;

        Mockito.doReturn(Optional.empty()).when(userRepository).findById(findById);
        userService.findById(findById);
        verify(userRepository, times(1)).findById(findById);
    }

    @Test
    public void defineByIdNotNull() {

        userService.findById(null);
        verify(userRepository, times(1)).findById(null);
    }

    @Test
    public void shouldFindAll() {

        List<User> expected = new ArrayList<>();
        expected.add(user);
        expected.add(master);
        when(userService.findAll()).thenReturn(expected);

        List<User> actual = userService.findAll();
        verify(userRepository, times(1)).findAll();

        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void shouldReturnUserByEmail() {

        Mockito.doReturn(Optional.of(user)).when(userRepository).findByEmail("petrov@gmail.com");

        Optional<User> actual = userService.findByEmail("petrov@gmail.com");

        verify(userRepository, times(1)).findByEmail("petrov@gmail.com");

        assertTrue(actual.isPresent());

        assertEquals(actual.get(), user);
    }
}
