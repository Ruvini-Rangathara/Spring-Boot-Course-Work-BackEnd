package com.next.travel.booking_service.repository;

import com.next.travel.booking_service.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity,String> {

}
