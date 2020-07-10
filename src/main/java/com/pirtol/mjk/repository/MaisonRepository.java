package com.pirtol.mjk.repository;

import com.pirtol.mjk.domain.Maison;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Maison entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaisonRepository extends JpaRepository<Maison, Long> {}
