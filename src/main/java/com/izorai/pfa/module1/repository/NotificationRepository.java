package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.camion.Notification;
import com.izorai.pfa.module1.entities.enumerations.NotificationFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByNotificationFrom(NotificationFrom notificationFrom) ;
}

