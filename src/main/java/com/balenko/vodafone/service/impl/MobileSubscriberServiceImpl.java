package com.balenko.vodafone.service.impl;

import com.balenko.vodafone.dto.MobileSubscriberDto;
import com.balenko.vodafone.exceptions.ResourceNotFoundException;
import com.balenko.vodafone.exceptions.ResourceNotUniqueException;
import com.balenko.vodafone.model.MobileSubscriber;
import com.balenko.vodafone.repository.MobileSubscriberRepository;
import com.balenko.vodafone.service.MobileSubscriberService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MobileSubscriberServiceImpl implements MobileSubscriberService {

    private final MobileSubscriberRepository mobileSubscriberRepository;
    private final MapperFacade mapper;

    @Autowired
    public MobileSubscriberServiceImpl(MobileSubscriberRepository mobileSubscriberRepository) {
        this.mobileSubscriberRepository = mobileSubscriberRepository;

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(MobileSubscriber.class, MobileSubscriberDto.class);
        mapperFactory.classMap(MobileSubscriberDto.class, MobileSubscriber.class);
        mapper = mapperFactory.getMapperFacade();
    }

    @Override
    public List<MobileSubscriberDto> getAllMobileNumbers() {
        List<MobileSubscriber> mobileSubscribers = mobileSubscriberRepository.findAll();
        return mapper.mapAsList(mobileSubscribers, MobileSubscriberDto.class);
    }

    @Override
    public List<MobileSubscriberDto> getSearchMobileNumbers(String number) {
        List<MobileSubscriber> mobileSubscribers = mobileSubscriberRepository.getSearchMobileNumbers(number);
        return mapper.mapAsList(mobileSubscribers, MobileSubscriberDto.class);
    }

    @Override
    public MobileSubscriberDto addMobileNumber(MobileSubscriberDto mobileSubscriberDto) {
        MobileSubscriber mobileSubscriber = mapper.map(mobileSubscriberDto, MobileSubscriber.class);
        mobileSubscriber.setCreatedDate(new Date().getTime());
        try {
            mobileSubscriberRepository.save(mobileSubscriber);
            return mapper.map(mobileSubscriber, MobileSubscriberDto.class);
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotUniqueException("Mobile number is not unique");
        }
    }

    @Override
    public MobileSubscriberDto changeMobileSubscriberData(Integer id, MobileSubscriberDto mobileSubscriberDto) {
        Optional<MobileSubscriber> mobileSubscriberOptional = mobileSubscriberRepository.findById(id);
        if (mobileSubscriberOptional.isEmpty()) {
            throw new ResourceNotFoundException("Mobile number is not found");
        }
        MobileSubscriber mobileSubscriber = mobileSubscriberOptional.get();
        if (mobileSubscriberDto.getServiceType() != null) {
            mobileSubscriber.setServiceType(mobileSubscriberDto.getServiceType());
        }
        if (mobileSubscriberDto.getOwnerId() != null) {
            mobileSubscriber.setOwnerId(mobileSubscriberDto.getOwnerId());
        }
        if (mobileSubscriberDto.getUserId() != null) {
            mobileSubscriber.setUserId(mobileSubscriberDto.getUserId());
        }
        mobileSubscriberRepository.save(mobileSubscriber);
        return mapper.map(mobileSubscriber, MobileSubscriberDto.class);
    }

    @Override
    public void deleteMobileNumber(Integer id) {
        try {
            mobileSubscriberRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Not found");
        }

    }
}
