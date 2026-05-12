package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
