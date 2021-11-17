package com.upgrad.bookingservice.dao;

import com.upgrad.bookingservice.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingInfoDao extends JpaRepository<BookingInfoEntity, Integer> {
}
