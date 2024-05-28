package tn.com.guru.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.com.guru.gateway.model.LogAccess;

@Repository
public interface ILogAccessRepository extends JpaRepository<LogAccess, Long> {

}
