package com.balenko.vodafone.repository;

import com.balenko.vodafone.model.MobileSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MobileSubscriberRepository extends JpaRepository<MobileSubscriber, Integer> {

    @Query("FROM MobileSubscriber where mobileNumber LIKE %:number%")
    List<MobileSubscriber> getSearchMobileNumbers(@Param("number") String number);
}
