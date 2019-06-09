package service;

import com.beard.entity.Comment;
import com.beard.entity.User;
import com.beard.repository.CommentRepository;
import com.beard.service.impl.CommentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

   private Comment comment1 = Comment.builder()
            .withCommentId(1L)
            .withComment("Hello world")
            .withUser(User.builder()
                        .withUserId(1L)
                        .withEmail("petrov@gmail.com")
                        .withFirstName("Alex")
                        .withLastName("Petrov")
                        .build())
           .build();

    private Comment comment2 = Comment.builder()
            .withCommentId(2L)
            .withComment("Hello galaxy")
            .withUser(User.builder()
                    .withUserId(1L)
                    .withEmail("petrov@gmail.com")
                    .withFirstName("Alex")
                    .withLastName("Petrov")
                    .build())
            .build();


    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;


    @Test
    public void shouldFindAll() {
        List<Comment> expected = new ArrayList<>();
        expected.add(comment1);
        expected.add(comment2);
        when(commentRepository.findAll()).thenReturn(expected);

        List<Comment> actual = commentService.findAll();
        verify(commentRepository, times(1)).findAll();

        assertEquals(expected.size(), actual.size());
    }


    @Test
    public void shouldFindById() {

        Mockito.doReturn(comment2).when(commentRepository).findById(2L);

        Comment actual = commentService.findById(2L);

        verify(commentRepository, times(1)).findById(2L);

        assertEquals(actual, comment2);
    }

    @Test
    public void shouldAddComments() {

        when(commentRepository.add(comment1)).thenReturn(true);

        boolean actual = commentService.add(comment1);
        boolean expected = true;

        assertEquals( expected, actual);
    }

    @Test
    public void shouldFindUsersForPagination() {

        List<Comment> expected = new ArrayList<>();
        expected.add(comment1);
        expected.add(comment2);

        when(commentRepository.findCommentsForPagination( 1, 5))
                              .thenReturn(expected);

        List<Comment> actual = commentService.findCommentsForPagination(1,5);

        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void shouldGetNumberOfRows() {

        List<Comment> list = new ArrayList<>();
        list.add(comment1);
        list.add(comment2);

        when(commentRepository.getNumberOfRows()).thenReturn(2);

        int actual = commentService.getNumberOfRows();
        int expected = list.size();

        assertEquals(expected, actual);
    }
}
