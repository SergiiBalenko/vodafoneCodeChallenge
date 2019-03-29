package com.balenko.vodafone.service;

import com.balenko.vodafone.VodafoneApplication;
import com.balenko.vodafone.dto.MobileSubscriberDto;
import com.balenko.vodafone.model.MobileSubscriber;
import com.balenko.vodafone.model.enums.ServiceType;
import com.balenko.vodafone.repository.MobileSubscriberRepository;
import com.balenko.vodafone.service.impl.MobileSubscriberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VodafoneApplication.class)
@WebAppConfiguration
public class MobileSubscriberServiceTest {

    private MobileSubscriberServiceImpl mobileSubscriberService;
    @Mock
    private MobileSubscriberRepository mobileSubscriberRepositoryMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mobileSubscriberService = new MobileSubscriberServiceImpl(mobileSubscriberRepositoryMock);
    }

    @Test
    public void testGetAllMobileNumbers() {
        List<MobileSubscriberDto> expected = Arrays.asList(new MobileSubscriberDto(1, "+380991234567"));
        when(mobileSubscriberRepositoryMock.findAll()).thenReturn(Arrays.asList(new MobileSubscriber(1, "+380991234567")));

        List<MobileSubscriberDto> actual = mobileSubscriberService.getAllMobileNumbers();
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getMobileNumber(), actual.get(0).getMobileNumber());
    }

    @Test
    public void testGetSearchMobileNumbers() {
        List<MobileSubscriberDto> expected = Arrays.asList(new MobileSubscriberDto(1, "+380991234567"));
        when(mobileSubscriberRepositoryMock.getSearchMobileNumbers("+380991234567")).thenReturn(Arrays.asList(new MobileSubscriber(1, "+380991234567")));

        List<MobileSubscriberDto> actual = mobileSubscriberService.getSearchMobileNumbers("+380991234567");
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getMobileNumber(), actual.get(0).getMobileNumber());
    }

    @Test
    public void testAddMobileNumber() {
        MobileSubscriberDto expected = new MobileSubscriberDto(1, "+380991234567");
        MobileSubscriber inputValue = new MobileSubscriber();
        inputValue.setMobileNumber("+380991234567");
        when(mobileSubscriberRepositoryMock.save(inputValue)).thenReturn(new MobileSubscriber(1, "+380991234567"));

        MobileSubscriberDto actualInput = new MobileSubscriberDto();
        actualInput.setMobileNumber("+380991234567");
        MobileSubscriberDto actual = mobileSubscriberService.addMobileNumber(actualInput);
        assertEquals(expected.getMobileNumber(), actual.getMobileNumber());
        assertEquals(expected.getId(), Integer.valueOf(1));
    }

    @Test
    public void testChangeServiceType() {
        MobileSubscriberDto expected = new MobileSubscriberDto(1, "");
        expected.setServiceType(ServiceType.MOBILE_POSTPAID);
        MobileSubscriber inputValue = new MobileSubscriber(1, "");
        when(mobileSubscriberRepositoryMock.findById(expected.getId())).thenReturn(java.util.Optional.ofNullable(inputValue));
        when(mobileSubscriberRepositoryMock.save(inputValue)).thenReturn(inputValue);

        MobileSubscriberDto actualInput = new MobileSubscriberDto();
        actualInput.setServiceType(ServiceType.MOBILE_POSTPAID);
        MobileSubscriberDto actual = mobileSubscriberService.changeMobileSubscriberData(1, actualInput);
        assertEquals(expected.getId(), Integer.valueOf(1));
        assertEquals(expected.getServiceType(), actual.getServiceType());
    }

    @Test
    public void testChangeOwnerId() {
        MobileSubscriberDto expected = new MobileSubscriberDto(1, "");
        expected.setOwnerId(123);
        MobileSubscriber inputValue = new MobileSubscriber(1, "");
        when(mobileSubscriberRepositoryMock.findById(expected.getId())).thenReturn(java.util.Optional.ofNullable(inputValue));
        when(mobileSubscriberRepositoryMock.save(inputValue)).thenReturn(inputValue);

        MobileSubscriberDto actualInput = new MobileSubscriberDto();
        actualInput.setOwnerId(123);
        MobileSubscriberDto actual = mobileSubscriberService.changeMobileSubscriberData(1, actualInput);
        assertEquals(expected.getId(), Integer.valueOf(1));
        assertEquals(expected.getOwnerId(), actual.getOwnerId());
    }

    @Test
    public void testChangeUserId() {
        MobileSubscriberDto expected = new MobileSubscriberDto(1, "");
        expected.setUserId(123);
        MobileSubscriber inputValue = new MobileSubscriber(1, "");
        when(mobileSubscriberRepositoryMock.findById(expected.getId())).thenReturn(java.util.Optional.ofNullable(inputValue));
        when(mobileSubscriberRepositoryMock.save(inputValue)).thenReturn(inputValue);

        MobileSubscriberDto actualInput = new MobileSubscriberDto();
        actualInput.setUserId(123);
        MobileSubscriberDto actual = mobileSubscriberService.changeMobileSubscriberData(1, actualInput);
        assertEquals(expected.getId(), Integer.valueOf(1));
        assertEquals(expected.getUserId(), actual.getUserId());
    }
}
