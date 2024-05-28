package tn.com.guru.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.com.guru.gateway.model.VAdmUser;

@Repository
public interface IVAdmUserRepository extends JpaRepository<VAdmUser, Long> {
	
	@Query("select v from VAdmUser v where v.id=:id ")
	public VAdmUser findVAdmUserById(@Param("id") Long id);
	
	@Query("select v from VAdmUser v where v.username=:login ")
	public VAdmUser findVAdmUserByLogin(@Param("login") String login);
	
	@Query("select v from VAdmUser v where v.id=:id or v.login=:id ")
	public VAdmUser findVAdmUserFroAccess(@Param("id") Long id);

}
