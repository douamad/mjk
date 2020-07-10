package com.pirtol.mjk.repository;

import com.pirtol.mjk.domain.Ethnie;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Ethnie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EthnieRepository extends JpaRepository<Ethnie, Long> {}
