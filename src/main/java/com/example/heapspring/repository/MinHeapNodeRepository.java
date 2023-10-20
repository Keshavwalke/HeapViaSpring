package com.example.heapspring.repository;

import com.example.heapspring.models.MinHeapNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinHeapNodeRepository extends JpaRepository<MinHeapNode,Long> {

}
