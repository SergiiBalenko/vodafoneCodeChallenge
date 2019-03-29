package com.balenko.vodafone.controller;

import com.balenko.vodafone.dto.MobileSubscriberDto;
import com.balenko.vodafone.dto.ResponseDto;
import com.balenko.vodafone.service.MobileSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mobiles")
public class MobileSubscriberController {

    private final MobileSubscriberService mobileSubscriberService;

    @Autowired
    public MobileSubscriberController(MobileSubscriberService mobileSubscriberService) {
        this.mobileSubscriberService = mobileSubscriberService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseDto<List<MobileSubscriberDto>> getAllMobileNumbers() {
        return new ResponseDto<>(mobileSubscriberService.getAllMobileNumbers());
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseDto<List<MobileSubscriberDto>> getSearchMobileNumbers(@RequestParam("number") String number) {
        return new ResponseDto<>(mobileSubscriberService.getSearchMobileNumbers(number));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseDto addMobileNumber(@Valid @RequestBody MobileSubscriberDto mobileSubscriberDto) {
        return new ResponseDto<>(mobileSubscriberService.addMobileNumber(mobileSubscriberDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseDto changeMobileSubscriberData(@PathVariable("id") Integer id, @RequestBody MobileSubscriberDto mobileSubscriberDto) {
        return new ResponseDto<>(mobileSubscriberService.changeMobileSubscriberData(id, mobileSubscriberDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseDto deleteMobileNumber(@PathVariable("id") Integer id) {
        mobileSubscriberService.deleteMobileNumber(id);
        return new ResponseDto();
    }

}
