package com.bksoftware.sellingweb.service.category;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<MediumCategory> findAllMediumCategoryPage();

    List<SmallCategory> findAllSmallCategoryPage();

    Page<MediumCategory> findAllMediumCategory(Pageable pageable);

    Page<SmallCategory> findAllSmallCategory(Pageable pageable);

    Page<BigCategory> showBigCategory(Pageable pageable);

    List<MediumCategory> showMediumCategory(int id);

    List<SmallCategory> showSmallCategory(int id);

    List<BigCategory> findAllBigCategoryPage();

    List<MediumCategory> findAllMediumCategoryByBigCategory(BigCategory bigCategory);

    List<SmallCategory> findAllSmallCategoryByMediumCategory(MediumCategory mediumCategory);

    List<Product> findAllProductBySmallCategory(SmallCategory smallCategory);

    BigCategory findBigCategoryById(int id);

    MediumCategory findMediumCategoryById(int id);

    SmallCategory findSmallCategoryById(int id);

    boolean saveBigCategory(BigCategory bigCategory);

    boolean saveMediumCategory(MediumCategory mediumCategory);

    boolean saveSmallCategory(SmallCategory smallCategory);

    boolean deleteBigCategory(BigCategory bigCategory);

    boolean deleteMediumCategory(MediumCategory mediumCategory);

    boolean deleteSmallCategory(SmallCategory smallCategory);

}
