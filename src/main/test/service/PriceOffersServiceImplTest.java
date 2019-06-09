package service;


import com.beard.entity.PriceOffers;
import com.beard.repository.PriceOffersRepository;
import com.beard.service.impl.PriceOffersServiceImpl;
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
public class PriceOffersServiceImplTest {

    private PriceOffers priceOffers1 = PriceOffers.builder()
            .withPriceOffersId(1L)
            .withName("Royal shave")
            .withPrice(250)
            .build();

    private PriceOffers priceOffers2 = PriceOffers.builder()
            .withPriceOffersId(2L)
            .withName("Beard and mustache trim")
            .withPrice(350)
            .build();

    @Mock
    private PriceOffersRepository priceOffersRepository;

    @InjectMocks
    private PriceOffersServiceImpl priceOffersService;


    @Test
    public void shouldFindById() {

        Mockito.doReturn(priceOffers1).when(priceOffersRepository).findById(2L);

        PriceOffers actual = priceOffersService.findById(2L);

        verify(priceOffersRepository, times(1)).findById(2L);

        assertEquals(actual, priceOffers1);
    }


    @Test
    public void shouldFindAll() {
        List<PriceOffers> expected = new ArrayList<>();
        expected.add(priceOffers1);
        expected.add(priceOffers2);

        when(priceOffersRepository.findAll()).thenReturn(expected);

        List<PriceOffers> actual = priceOffersService.findAll();
        verify(priceOffersRepository, times(1)).findAll();

        assertEquals(2, actual.size());
    }
}
