package com.pirtol.mjk.repository;

import com.pirtol.mjk.domain.ObjetAssistance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ObjetAssistance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObjetAssistanceRepository extends JpaRepository<ObjetAssistance, Long> {}
