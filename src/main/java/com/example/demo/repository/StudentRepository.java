package com.example.demo.repository;




import com.example.demo.model.entities.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {

    @Query("SELECT s FROM StudentEntity s WHERE s.classEntity.id =:idClass")
    Page<StudentEntity> findByClass(@Param("idClass") int idClass, Pageable pageable);

    @Query("SELECT s FROM StudentEntity s WHERE s.classEntity.id =:idClass AND s.status = :status")
    Page<StudentEntity> findByClassAndStatus(@Param("idClass") int idClass,@Param("status") boolean status, Pageable pageable);


    @Query("SELECT s FROM StudentEntity s WHERE s.classEntity IS NULL ")
    Page<StudentEntity> findByClassIsNull(Pageable pageable);


    @Query("SELECT s FROM StudentEntity s WHERE s.status = false AND s.id =:id")
    StudentEntity checkStudentStatus(@Param("id") int id);


}
