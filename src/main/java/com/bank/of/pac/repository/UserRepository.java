package com.bank.of.pac.repository;

import com.bank.of.pac.model.MstUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<MstUser, Long> {

    @Query(value = "select a.* from mst_user a where a.user_id = :user_id",nativeQuery = true)
    Optional<MstUser> findByUserId(@Param("user_id") Long userId);

    @Query(value = "select a.* from mst_user a where lower(a.user_email)= lower(:userEmail)",nativeQuery = true)
    Optional<MstUser> findByEmail(@Param("userEmail") String userEmail);


    @Query(value = "select a.* from mst_user a order by a.user_id asc ",nativeQuery = true)
    List<MstUser> findAll();

}
