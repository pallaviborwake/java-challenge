package jp.co.axa.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.axa.apidemo.entities.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData,String> {

	UserData findByUsername(String username);
}
