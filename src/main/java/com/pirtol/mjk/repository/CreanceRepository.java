package com.pirtol.mjk.repository;

import com.pirtol.mjk.domain.Creance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Creance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreanceRepository extends JpaRepository<Creance, Long> {}
