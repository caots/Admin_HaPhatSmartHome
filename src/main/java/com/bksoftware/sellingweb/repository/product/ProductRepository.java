package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name_product,'%')")
    Page<Product> findByName(@Param("name_product") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name_product,'%')")
    List<Product> findByNamePage(@Param("name_product") String name);

    @Query(value = "SELECT p  From Product p  order by p.initDate desc ")
    Page<Product> findNewProducts(Pageable pageable);

    @Query(value = " SELECT p From Product p where p.status=true order by p.initDate desc ")
    List<Product> findNewProductsPage();

    @Query(value = " SELECT p From Product p order by (p.originCost - p.saleCost) desc ")
    Page<Product> findHotProducts(Pageable pageable);

    @Query(value = " SELECT p from Product p  where p.status=true order by (p.saleCost) asc ")
    List<Product> findHotProductsPage();

    List<Product> findAllBySmallCategory(SmallCategory smallCategory);

    Product findById(int id);

    Page<Product> findByStatus(boolean status, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.id= :id")
    Page<Product> showProduct(@Param("id") int id, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.id= :id")
    List<Product> showProductPage(@Param("id") int id);

    @Query("select p from Product p where p.status=true and p.smallCategory.id= :id")
    List<Product> findProductBySmall(@Param("id") int id);

    @Query("select p from Product p where p.status=true and p.smallCategory.id= :id and p.partner.id= :branch")
    List<Product> showProductSmallBranch(@Param("id") int id, @Param("branch") int branch);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.id= :id")
    Page<Product> showProductByMedium(@Param("id") int id, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.id= :id")
    List<Product> findProductByMedium(@Param("id") int id);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.id= :id and p.partner.id= :branch")
    List<Product> showProductByMediumBranch(@Param("id") int id, @Param("branch") int branch);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id")
    Page<Product> showProductByBig(@Param("id") int id, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id")
    List<Product> showProductByBigList(@Param("id") int id);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id and p.partner.id= :branch")
    List<Product> showProductByBigBranch(@Param("id") int id, @Param("branch") int branch);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id" +
            " and (p.saleCost> :low and p.saleCost< :high)")
    Page<Product> findProductByPrice(@Param("id") int id, @Param("low") int low, @Param("high") int high, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id  and p.partner.id= :branch" +
            " and (p.saleCost> :low and p.saleCost< :high)")
    Page<Product> findProductByPriceBranch(@Param("id") int id, @Param("low") int low, @Param("high") int high, @Param("branch") int branch, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.saleCost>0")
    Page<Product> showProductSale(Pageable pageable);

    @Query("select p from Product p where p.status=true")
    List<Product> findAllProduct();

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id and p.saleCost between :price1 and :price2 ")
    List<Product> productsByBigPrice(@Param("id") int id, @Param("price1") double price1, @Param("price2") double price2);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.id= :id and p.saleCost between :price1 and :price2 ")
    List<Product> productsByMediumPrice(@Param("id") int id, @Param("price1") double price1, @Param("price2") double price2);

    @Query("select p from Product p where p.status=true and p.smallCategory.id= :id and p.saleCost between :price1 and :price2 ")
    List<Product> productsBySmallPrice(@Param("id") int id, @Param("price1") double price1, @Param("price2") double price2);

    @Query(value = " from Product p where p.saleCost between :price1 and :price2  order by (p.saleCost) asc ")
    List<Product> productsByHotPrice(@Param("price1") double price1, @Param("price2") double price2b);

    @Query(value = " from Product p where p.saleCost between :price1 and :price2  order by p.initDate desc  ")
    List<Product> productsByNewPrice(@Param("price1") double price1, @Param("price2") double price2b);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id and p.saleCost between :price1 and :price2 and p.partner.id= :branch ")
    List<Product> productsByBigPriceAndBranch(@Param("id") int id, @Param("price1") double price1, @Param("price2") double price2, @Param("branch") int branch);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.id= :id and p.saleCost between :price1 and :price2 and p.partner.id= :branch")
    List<Product> productsByMediumPriceAndBranch(@Param("id") int id, @Param("price1") double price1, @Param("price2") double price2, @Param("branch") int branch);

    @Query("select p from Product p where p.status=true and p.smallCategory.id= :id and p.saleCost between :price1 and :price2 and p.partner.id= :branch")
    List<Product> productsBySmallPriceAndBranch(@Param("id") int id, @Param("price1") double price1, @Param("price2") double price2, @Param("branch") int branch);

    @Query(value = " from Product p where p.saleCost between :price1 and :price2  and p.partner.id= :branch order by (p.saleCost) asc ")
    List<Product> productsByHotPriceAndBranch(@Param("price1") double price1, @Param("price2") double price2b, @Param("branch") int branch);

    @Query(value = " from Product p where p.saleCost between :price1 and :price2   and p.partner.id= :branch order by p.initDate desc  ")
    List<Product> productsByNewPriceAndBranch(@Param("price1") double price1, @Param("price2") double price2b, @Param("branch") int branch);
}
