package com.balenko.vodafone.service;

import com.balenko.vodafone.dto.MobileSubscriberDto;

import java.util.List;

public interface MobileSubscriberService {

    List<MobileSubscriberDto> getAllMobileNumbers();

    List<MobileSubscriberDto> getSearchMobileNumbers(String number);

    MobileSubscriberDto addMobileNumber(MobileSubscriberDto mobileSubscriberDto);

    MobileSubscriberDto changeMobileSubscriberData(Integer id, MobileSubscriberDto mobileSubscriberDto);

    void deleteMobileNumber(Integer id);
}
