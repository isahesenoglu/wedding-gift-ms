package com.example.weddinggift.repository;

import com.example.weddinggift.model.Credential;
import com.example.weddinggift.model.Guest;
import com.example.weddinggift.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest,Long> {
    @Query("SELECT g FROM Guest g WHERE g.name = :name AND g.surname = :surname AND g.nickName = :nickName AND " +
            "g.amount = :amount AND g.currency = :currency AND g.paymentType = :paymentType AND " +
            "g.additionalText = :additionalText AND g.user.id = :userId")
    Optional<Guest> findExistingGuest(@Param("name") String name,
                                      @Param("surname") String surname,
                                      @Param("nickName") String nickName,
                                      @Param("amount") Double amount,
                                      @Param("currency") String currency,
                                      @Param("paymentType") String paymentType,
                                      @Param("additionalText") String additionalText,
                                      @Param("userId") Long userId);

    List<Guest> findByUser(User user);


}
