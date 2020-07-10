package com.pirtol.mjk.repository;

import com.pirtol.mjk.domain.Saisine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Saisine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SaisineRepository extends JpaRepository<Saisine, Long> {}
