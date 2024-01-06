package LibraryManagement.data.repository;

import LibraryManagement.data.entity.MemberEntity;
import LibraryManagement.data.enums.UserRole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Qualifier("Member")
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
   Optional<MemberEntity> findByMemberId(String memberId); // MemberId기준 MemberEntity조회(MemberId는 PK)

   @Query("SELECT m FROM MemberEntity m WHERE m.Name = :name")
   List<Object> findByName(@Param("name") String name);
   @Query("SELECT m FROM MemberEntity m WHERE m.Level = :level")
   List<Object> findByLevel(@Param("level") UserRole level);
}