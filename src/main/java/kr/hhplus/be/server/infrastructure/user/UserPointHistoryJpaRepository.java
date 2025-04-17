package kr.hhplus.be.server.infrastructure.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.domain.user.entity.UserPointHistory;

@Repository
public interface UserPointHistoryJpaRepository extends JpaRepository<UserPointHistory, Long>{
	List<UserPointHistory> findAllByUserId(Long userId);
}
