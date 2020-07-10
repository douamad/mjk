package com.pirtol.mjk.repository;

import com.pirtol.mjk.domain.ObjetSaisine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ObjetSaisine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObjetSaisineRepository extends JpaRepository<ObjetSaisine, Long> {}
