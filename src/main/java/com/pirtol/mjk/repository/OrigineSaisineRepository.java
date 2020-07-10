package com.pirtol.mjk.repository;

import com.pirtol.mjk.domain.OrigineSaisine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OrigineSaisine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrigineSaisineRepository extends JpaRepository<OrigineSaisine, Long> {}
