package com.bksoftware.sellingweb.repository.category;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BigCategoryRepository extends JpaRepository<BigCategory, Integer> {

    BigCategory findByName(String name);

    BigCategory findById(int id);

    @Query("select b from BigCategory b where b.status=true ")
    Page<BigCategory> showBigCategory(Pageable pageable);



}
