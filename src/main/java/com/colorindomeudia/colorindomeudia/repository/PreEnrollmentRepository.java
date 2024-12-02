package com.colorindomeudia.colorindomeudia.repository;

import com.colorindomeudia.colorindomeudia.model.PreEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreEnrollmentRepository extends JpaRepository<PreEnrollment, Long> {
    @Query(
            value = "SELECT * FROM PreEnrollment e WHERE e.desired_class_id = :desired_class_id",
            nativeQuery = true)
    List<PreEnrollment> findByDesiredClassId(@Param("desired_class_id") Long desiredClassId);
}
