package service;

import com.beard.entity.PriceOffers;
import com.beard.entity.Schedule;
import com.beard.entity.ScheduleItem;
import com.beard.entity.User;
import com.beard.repository.ScheduleItemRepository;
import com.beard.service.impl.ScheduleItemServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleItemServiceImplTest {

    private ScheduleItem  scheduleItem = ScheduleItem.builder()
            .withScheduleItemId(1L)
            .withDate(LocalDate.parse("2019-06-10"))
            .withTime(LocalTime.parse("10:00:00"))
            .withMaster(User.builder()
                        .withUserId(1L)
                        .withFirstName("John")
                        .withLastName("Dillinger")
                        .build())
            .withCustomer(User.builder()
                        .withUserId(2L)
                        .withFirstName("Arthur")
                        .withLastName("Morgan")
                        .withEmail("morgan@gmail.com")
                        .build())
            .withFreeBusy(true)
            .withSchedule(new Schedule(1L))
            .withPriceOffers(PriceOffers.builder()
                        .withPriceOffersId(1L)
                        .withName("Haircut")
                        .withPrice(500)
                        .build())
            .build();

   private ScheduleItem basicScheduleItem = ScheduleItem.builder()
            .withScheduleItemId(1L)
           .withDate(LocalDate.parse("2019-06-11"))
           .withTime(LocalTime.parse("11:00:00"))
            .withMaster(User.builder()
                        .withUserId(1L)
                        .withFirstName("Guile")
                        .withLastName("Adnderson")
                        .build())
            .withFreeBusy(true)
            .withSchedule(new Schedule(2L))
            .build();


    @Mock
    private ScheduleItemRepository scheduleItemRepository;

    @InjectMocks
    private ScheduleItemServiceImpl scheduleItemService;

    @Test
    public void shouldFindAll() {

        List<ScheduleItem> expected = new ArrayList<>();
        expected.add(scheduleItem);
        when(scheduleItemRepository.findAll()).thenReturn(expected);

        List<ScheduleItem> actual = scheduleItemService.findAll();
        verify(scheduleItemRepository, times(1)).findAll();

        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void shouldFindByIdBasic() {

        List<ScheduleItem> expected = new ArrayList<>();
        expected.add(basicScheduleItem);
        when(scheduleItemRepository.findByIdScheduleItemBasic(2L)).thenReturn(expected);

        List<ScheduleItem> actual = scheduleItemService.findByIdScheduleItemBasic(2L);
        verify(scheduleItemRepository, times(1)).findByIdScheduleItemBasic(2L);


        assertEquals(expected.size(), actual.size());
    }


    @Test
    public void shouldFindById() {

        Mockito.doReturn(scheduleItem).when(scheduleItemRepository).findById(1L);

        ScheduleItem actual = scheduleItemService.findById(1L);

        verify(scheduleItemRepository, times(1)).findById(1L);

        assertEquals(actual, scheduleItem);
    }

    @Test
    public void shouldAddScheduleItem() {

        when(scheduleItemRepository.add(scheduleItem)).thenReturn(true);

        boolean actual = scheduleItemService.add(scheduleItem);
        boolean expected = true;

        assertEquals( expected, actual);
    }

    @Test
    public void shouldDeleteById() {

        when(scheduleItemRepository.deleteById(1L)).thenReturn(true);

        boolean actual = scheduleItemService.deleteById(1L);
        boolean expected = true;

        assertEquals( expected, actual);
    }

    @Test
    public void shouldUpdateUsers() {

        when(scheduleItemRepository.update(scheduleItem)).thenReturn(true);

        boolean actual = scheduleItemService.update(scheduleItem);
        boolean expected = true;

        assertEquals( expected, actual);
    }

    @Test
    public void shouldFindUsersForPagination() {

        List<ScheduleItem> expected = new ArrayList<>();
        expected.add(scheduleItem);

        when(scheduleItemRepository.findScheduleItemForPagination( 1, 5)).thenReturn(expected);

        List<ScheduleItem> actual = scheduleItemService.findScheduleItemForPagination(1,5);

        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void shouldGetNumberOfRows() {

        List<ScheduleItem> list = new ArrayList<>();
        list.add(scheduleItem);

        when(scheduleItemRepository.getNumberOfRows()).thenReturn(1);

        int actual = scheduleItemService.getNumberOfRows();
        int expected = list.size();

        assertEquals(1, actual);
    }
}
