package com.example.heapspring.repository;

import com.example.heapspring.models.MaxHeapNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxHeapNodeRepository extends JpaRepository<MaxHeapNode,Long> {
}
