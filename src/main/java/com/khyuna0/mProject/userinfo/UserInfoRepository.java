package com.khyuna0.mProject.userinfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	public Optional<UserInfo> findByUsername(String username);
}
