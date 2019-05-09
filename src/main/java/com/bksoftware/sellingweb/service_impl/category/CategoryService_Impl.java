package com.bksoftware.sellingweb.service_impl.category;

import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.bksoftware.sellingweb.repository.category.BigCategoryRepository;
import com.bksoftware.sellingweb.repository.category.MediumCategoryRepository;
import com.bksoftware.sellingweb.repository.product.ProductRepository;
import com.bksoftware.sellingweb.repository.category.SmallCategoryRepository;
import com.bksoftware.sellingweb.service.category.CategoryService;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
  public class CategoryService_Impl implements CategoryService {

    private final static Logger LOGGER = Logger.getLogger(PartnerService_Impl.class.getName());

    private final BigCategoryRepository bigCategoryRepository;
    private final MediumCategoryRepository mediumCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final ProductRepository productRepository;

    public CategoryService_Impl(BigCategoryRepository bigCategoryRepository, MediumCategoryRepository mediumCategoryRepository,
                                SmallCategoryRepository smallCategoryRepository, ProductRepository productRepository) {
        this.bigCategoryRepository = bigCategoryRepository;
        this.mediumCategoryRepository = mediumCategoryRepository;
        this.smallCategoryRepository = smallCategoryRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Page<BigCategory> showBigCategory(Pageable pageable) {
        try {
            return bigCategoryRepository.showBigCategory(pageable);
        } catch (Exception ex){
            LOGGER.log(Level.SEVERE, "show-all-big-category-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<MediumCategory> findAllMediumCategoryPage() {
        return mediumCategoryRepository.findAllMediumCategory();
    }

    @Override
    public List<SmallCategory> findAllSmallCategoryPage() {
        return smallCategoryRepository.findAllSmallCategory();
    }

    @Override
    public Page<MediumCategory> findAllMediumCategory(Pageable pageable) {

        try {
            return mediumCategoryRepository.findByStatus(true,pageable);
        } catch (Exception ex){
            LOGGER.log(Level.SEVERE, "show-all-medium-category-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Page<SmallCategory> findAllSmallCategory(Pageable pageable) {

        try {
            return smallCategoryRepository.findByStatus(true,pageable);
        } catch (Exception ex){
            LOGGER.log(Level.SEVERE, "show-all-small-category-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<MediumCategory> showMediumCategory(int id) {

        try {
            return mediumCategoryRepository.showMediumCategory(id);
        } catch (Exception ex){
            LOGGER.log(Level.SEVERE, "show-all-medium-category-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<SmallCategory> showSmallCategory(int id) {

        try {
            return smallCategoryRepository.showSmallCategory(id);
        } catch (Exception ex){
            LOGGER.log(Level.SEVERE, "show-all-small-category-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<BigCategory> findAllBigCategoryPage() {
        try {
            List<BigCategory> bigCategories = bigCategoryRepository.findAll();
            return bigCategories
                    .stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-big-category-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<MediumCategory> findAllMediumCategoryByBigCategory(BigCategory bigCategory) {
        try {
            List<MediumCategory> mediumCategories = mediumCategoryRepository.findAllByBigCategory(bigCategory);
            return mediumCategories
                    .stream()
                    .filter(p -> p.isStatus() == true)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-medium-category-by-big-category-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<SmallCategory> findAllSmallCategoryByMediumCategory(MediumCategory mediumCategory) {
        try {
            List<SmallCategory> smallCategories = smallCategoryRepository.findAllByMediumCategory(mediumCategory);
            return smallCategories
                    .stream()
                    .filter(p -> p.isStatus() == true)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-small-category-by-medium-category-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findAllProductBySmallCategory(SmallCategory smallCategory) {
        try {
            List<Product> products = productRepository.findAllBySmallCategory(smallCategory);
            return products
                    .stream()
                    .filter(p -> p.isStatus() == true)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-product-by-small-category-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public BigCategory findBigCategoryById(int id) {
        try {
            BigCategory bigCategory = bigCategoryRepository.findById(id);
            if (bigCategory.isStatus() == true) return bigCategory;
            return null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-big-category-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public MediumCategory findMediumCategoryById(int id) {
        try {
            MediumCategory mediumCategory = mediumCategoryRepository.findById(id);
            if (mediumCategory.isStatus() == true) return mediumCategory;
            return null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-medium-category-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public SmallCategory findSmallCategoryById(int id) {
        try {
            SmallCategory smallCategory = smallCategoryRepository.findById(id);
            if (smallCategory.isStatus() == true) return smallCategory;
            return null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-small-category-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveBigCategory(BigCategory bigCategory) {
        try {
            bigCategoryRepository.save(bigCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-big-category-error", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean saveMediumCategory(MediumCategory mediumCategory) {
        try {
            mediumCategoryRepository.save(mediumCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-medium-category-error", ex.getMessage());
        }
        return false;

    }

    @Override
    public boolean saveSmallCategory(SmallCategory smallCategory) {
        try {
            smallCategoryRepository.save(smallCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-small-category-error", ex.getMessage());
        }
        return false;

    }


    @Override
    public boolean deleteBigCategory(BigCategory bigCategory) {
        try {
            bigCategory.setStatus(false);
            bigCategoryRepository.save(bigCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-error", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteMediumCategory(MediumCategory mediumCategory) {
        try {
            mediumCategory.setStatus(false);
            mediumCategoryRepository.save(mediumCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteSmallCategory(SmallCategory smallCategory) {
        try {
            smallCategory.setStatus(false);
            smallCategoryRepository.save(smallCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-error", ex.getMessage());
            return false;
        }
    }
}
