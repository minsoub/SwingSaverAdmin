package kr.co.swingsaver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.swingsaver.entity.AdminEntity;

@Repository
public interface AdminRepository  extends JpaRepository<AdminEntity, String> {
	AdminEntity findByEmail(String email);
}
