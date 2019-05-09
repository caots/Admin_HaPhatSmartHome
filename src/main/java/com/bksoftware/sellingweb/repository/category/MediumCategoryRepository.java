package com.bksoftware.sellingweb.repository.category;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediumCategoryRepository extends JpaRepository<MediumCategory,Integer> {

    List<MediumCategory> findAllByBigCategory(BigCategory bigCategory);
    MediumCategory findById(int id);

    @Query("select m from MediumCategory m where m.status = true and  m.bigCategory.id= :id")
    List<MediumCategory> showMediumCategory(@Param("id") int id);

    Page<MediumCategory> findByStatus(boolean status, Pageable pageable);


    @Query("select m from MediumCategory m where m.status=true")
    List<MediumCategory> findAllMediumCategory();
}
