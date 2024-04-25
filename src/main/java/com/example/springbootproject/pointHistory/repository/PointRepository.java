package com.example.springbootproject.pointHistory.repository;


import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.pointHistory.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<PointHistory,Long> {

    List<PointHistory>findAllByUser(User user);
    List<PointHistory> findAllByUserIdOrderByIdDesc(Long userId);


}
