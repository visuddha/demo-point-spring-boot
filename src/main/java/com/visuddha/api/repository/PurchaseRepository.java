package com.visuddha.api.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.visuddha.api.models.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

        @Query(value = "SELECT CONCAT(YEAR(purchase_date), '-', MONTH(purchase_date)) AS \"month\", " +
                        "SUM(points) AS order_count " +
                        "FROM Purchase " +
                        "WHERE user_id = :userId " +
                        "  AND purchase_date >= :startDate " +
                        "  AND purchase_date <= :endDate " +
                        "GROUP BY CONCAT(YEAR(purchase_date), '-', MONTH(purchase_date)) " +
                        "ORDER BY CONCAT(YEAR(purchase_date), '-', MONTH(purchase_date))", nativeQuery = true)

        List<Object[]> calculatePointsByMonth(
                        @Param("userId") int userId,
                        @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate);

        @Query(value = "SELECT sum(points) FROM Purchase WHERE user_id = :userId ", nativeQuery = true)
        int getPointsByUserId(@Param("userId") int userId);

        @Query(value = "SELECT count(*) FROM Purchase")
        String calculatePointsByMonth3(
                        @Param("userId") int userId,
                        @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate);

}
