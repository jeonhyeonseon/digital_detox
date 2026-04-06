package com.digitaldetox.digital_detox.faq.repository;

import com.digitaldetox.digital_detox.faq.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
}
