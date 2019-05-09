package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.AppAdmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppAdminRepository extends CrudRepository<AppAdmin,Integer> {
    AppAdmin findByUsernameAndPassword(String username, String password);
    AppAdmin findByUsername(String username);
    List<AppAdmin> findAll();
}
