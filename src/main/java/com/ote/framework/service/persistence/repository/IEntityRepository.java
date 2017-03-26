package com.ote.framework.service.persistence.repository;

import com.ote.framework.model.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IEntityRepository<TE extends IEntity<TK>, TK extends Serializable> extends JpaRepository<TE, TK>, JpaSpecificationExecutor<TE> {
}
