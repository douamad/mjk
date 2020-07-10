package com.pirtol.mjk.repository;

import com.pirtol.mjk.domain.NatureSaisine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NatureSaisine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NatureSaisineRepository extends JpaRepository<NatureSaisine, Long> {}
