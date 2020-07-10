package com.pirtol.mjk.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.pirtol.mjk.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.pirtol.mjk.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.pirtol.mjk.domain.User.class.getName());
            createCache(cm, com.pirtol.mjk.domain.Authority.class.getName());
            createCache(cm, com.pirtol.mjk.domain.User.class.getName() + ".authorities");
            createCache(cm, com.pirtol.mjk.domain.ObjetSaisine.class.getName());
            createCache(cm, com.pirtol.mjk.domain.OrigineSaisine.class.getName());
            createCache(cm, com.pirtol.mjk.domain.NatureSaisine.class.getName());
            createCache(cm, com.pirtol.mjk.domain.Conclusion.class.getName());
            createCache(cm, com.pirtol.mjk.domain.Maison.class.getName());
            createCache(cm, com.pirtol.mjk.domain.Profession.class.getName());
            createCache(cm, com.pirtol.mjk.domain.ObjetAssistance.class.getName());
            createCache(cm, com.pirtol.mjk.domain.Ethnie.class.getName());
            createCache(cm, com.pirtol.mjk.domain.Saisine.class.getName());
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName());
            createCache(cm, com.pirtol.mjk.domain.Creance.class.getName());
            createCache(cm, com.pirtol.mjk.domain.Assistance.class.getName());
            createCache(cm, com.pirtol.mjk.domain.Saisine.class.getName() + ".objets");
            createCache(cm, com.pirtol.mjk.domain.Saisine.class.getName() + ".natures");
            createCache(cm, com.pirtol.mjk.domain.Saisine.class.getName() + ".origines");
            createCache(cm, com.pirtol.mjk.domain.Saisine.class.getName() + ".conclusions");
            createCache(cm, com.pirtol.mjk.domain.Saisine.class.getName() + ".maisons");
            createCache(cm, com.pirtol.mjk.domain.Saisine.class.getName() + ".demandeurs");
            createCache(cm, com.pirtol.mjk.domain.Saisine.class.getName() + ".defendeurs");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".professions");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".ethnis");
            createCache(cm, com.pirtol.mjk.domain.Creance.class.getName() + ".origines");
            createCache(cm, com.pirtol.mjk.domain.Creance.class.getName() + ".conclusions");
            createCache(cm, com.pirtol.mjk.domain.Creance.class.getName() + ".maisons");
            createCache(cm, com.pirtol.mjk.domain.Creance.class.getName() + ".demandeurs");
            createCache(cm, com.pirtol.mjk.domain.Creance.class.getName() + ".defendeurs");
            createCache(cm, com.pirtol.mjk.domain.Assistance.class.getName() + ".objets");
            createCache(cm, com.pirtol.mjk.domain.Assistance.class.getName() + ".maisons");
            createCache(cm, com.pirtol.mjk.domain.Assistance.class.getName() + ".demandeurs");
            createCache(cm, com.pirtol.mjk.domain.Assistance.class.getName() + ".defendeurs");
            createCache(cm, com.pirtol.mjk.domain.ObjetSaisine.class.getName() + ".saisines");
            createCache(cm, com.pirtol.mjk.domain.OrigineSaisine.class.getName() + ".creances");
            createCache(cm, com.pirtol.mjk.domain.OrigineSaisine.class.getName() + ".saisines");
            createCache(cm, com.pirtol.mjk.domain.NatureSaisine.class.getName() + ".saisines");
            createCache(cm, com.pirtol.mjk.domain.Conclusion.class.getName() + ".creances");
            createCache(cm, com.pirtol.mjk.domain.Conclusion.class.getName() + ".saisines");
            createCache(cm, com.pirtol.mjk.domain.Maison.class.getName() + ".assistances");
            createCache(cm, com.pirtol.mjk.domain.Maison.class.getName() + ".creances");
            createCache(cm, com.pirtol.mjk.domain.Maison.class.getName() + ".saisines");
            createCache(cm, com.pirtol.mjk.domain.Profession.class.getName() + ".requerants");
            createCache(cm, com.pirtol.mjk.domain.ObjetAssistance.class.getName() + ".assistances");
            createCache(cm, com.pirtol.mjk.domain.Ethnie.class.getName() + ".requerants");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".assistances");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".creances");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".saisines");
            createCache(cm, com.pirtol.mjk.domain.ObjetSaisine.class.getName() + ".objets");
            createCache(cm, com.pirtol.mjk.domain.OrigineSaisine.class.getName() + ".origines");
            createCache(cm, com.pirtol.mjk.domain.NatureSaisine.class.getName() + ".natures");
            createCache(cm, com.pirtol.mjk.domain.Conclusion.class.getName() + ".conclusions");
            createCache(cm, com.pirtol.mjk.domain.Maison.class.getName() + ".maisons");
            createCache(cm, com.pirtol.mjk.domain.Profession.class.getName() + ".professions");
            createCache(cm, com.pirtol.mjk.domain.ObjetAssistance.class.getName() + ".objets");
            createCache(cm, com.pirtol.mjk.domain.Ethnie.class.getName() + ".ethnis");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".demandeurs");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".defendeurs");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".demandeAssistances");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".defenseAssistances");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".demandeCreances");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".defenseCreances");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".demandeSaisines");
            createCache(cm, com.pirtol.mjk.domain.Requerant.class.getName() + ".defenseSaisines");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
