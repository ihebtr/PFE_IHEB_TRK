package tn.com.guru.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.com.guru.gateway.model.LogData;

public interface ILogDataRepository extends JpaRepository<LogData, Long> {

}
