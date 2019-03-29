package com.balenko.vodafone.model;

import com.balenko.vodafone.model.enums.ServiceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "mobile_subsribers")
@Getter @Setter @NoArgsConstructor
public class MobileSubscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(unique = true, name = "msisdn")
    private String mobileNumber;

    @Column(name = "customer_id_owner")
    private Integer ownerId;

    @Column(name = "customer_id_user")
    private Integer userId;

    @Column(name = "service_type")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column(name = "service_start_date")
    private Long createdDate;

    public MobileSubscriber(Integer id, String mobileNumber) {
        this.id = id;
        this.mobileNumber = mobileNumber;
    }
}
