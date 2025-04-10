package kr.hhplus.be.server.domain.user;

public interface UserPointRepository {
	UserPoint findById(Long id);
	void update(UserPoint user);
}
