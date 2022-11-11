package ru.mephi.gpus_api.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mephi.gpus_api.entity.products.Store;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    @Query(nativeQuery = true,
            value = "select *" +
                    "from Store s " +
                    "where s.product_id = :productId")
    List<Store> findAllByProductId(String productId);
}
