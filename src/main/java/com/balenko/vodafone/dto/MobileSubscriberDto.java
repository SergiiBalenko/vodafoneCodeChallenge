package com.balenko.vodafone.dto;

import com.balenko.vodafone.model.enums.ServiceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class MobileSubscriberDto {

    private Integer id;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$")
    private String mobileNumber;

    private Integer ownerId;
    private Integer userId;
    private ServiceType serviceType;

    public MobileSubscriberDto(Integer id, String mobileNumber) {
        this.id = id;
        this.mobileNumber = mobileNumber;
    }
}
