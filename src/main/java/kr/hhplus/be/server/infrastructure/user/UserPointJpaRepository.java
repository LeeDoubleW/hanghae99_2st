package kr.hhplus.be.server.infrastructure.user;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.domain.user.entity.UserPoint;

public interface UserPointJpaRepository extends JpaRepository<UserPoint, Long> {

}