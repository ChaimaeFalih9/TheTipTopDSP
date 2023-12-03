package com.jeux.tiptop_app.repository;

import com.jeux.tiptop_app.entity.SendMail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendMailRepository extends JpaRepository<SendMail,Integer> {
}
