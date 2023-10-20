package com.example.heapspring.service;

import com.example.heapspring.models.MinHeapNode;
import com.example.heapspring.repository.MinHeapNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MinHeapService {
    @Autowired
    private MinHeapNodeRepository repository;

    private List<Integer> minHeap = new ArrayList<>();

    public int getMin() {
        if (minHeap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return minHeap.get(0);
    }

    public void insert(int value) {
        MinHeapNode newNode = new MinHeapNode(value);
        repository.save(newNode);

        int N=minHeap.size();
        minHeap.add(value);
        int index=N;
        int parent=(index-1)/2;
        while(index>0 && minHeap.get(parent)>minHeap.get(index)){
            int temp=minHeap.get(parent);
            minHeap.set(parent,minHeap.get(index));
            minHeap.set(index,temp);
            index=parent;
            parent=(index-1)/2;
        }
    }


    public void deleteMin(){
        if (minHeap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int N=minHeap.size();
        int temp=minHeap.get(0);
        minHeap.set(0,minHeap.get(N-1));
        minHeap.set(N-1, temp);
        minHeap.remove(N-1);
        minHeapify();

        Optional<MinHeapNode> nodeToDelete = repository.findById(1L); // Assuming the first node is the minimum
        nodeToDelete.ifPresent(repository::delete);
    }

    public int size() {
        return minHeap.size();
    }



    public void minHeapify(){
        int N=minHeap.size();
        for(int k=N-1;k>=0;k--) {
            int i = k;
            while (i < N) {
                int minInd = 0;
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                if (left >= N) break;      //means no left child exists
                if (left < N) {
                    minInd = left;
                }
                if (right < N && minHeap.get(right) < minHeap.get(left)) {
                    minInd = right;
                }
                if (minHeap.get(i) <= minHeap.get(minInd)) {
                    break;
                } else {
                    int temp1 = minHeap.get(i);
                    minHeap.set(i, minHeap.get(minInd));
                    minHeap.set(minInd, temp1);
                    i = minInd;
                }
            }
        }
    }

    //    public void deleteMin() {
//        if (minHeap.isEmpty()) {
//            throw new IllegalStateException("Heap is empty");
//        }
//
//        int lastIndex = minHeap.size() - 1;
//        int lastValue = minHeap.remove(lastIndex);
//
//        if (!minHeap.isEmpty()) {
//            minHeap.set(0, lastValue);
//            heapify(0);
//        }
//
//        Optional<MinHeapNode> nodeToDelete = repository.findById(1L); // Assuming the first node is the minimum
//        nodeToDelete.ifPresent(repository::delete);
//    }


//    private void heapify(int index) {
//        int leftChildIndex = 2 * index + 1;
//        int rightChildIndex = 2 * index + 2;
//        int smallest = index;
//
//        if (leftChildIndex < minHeap.size() && minHeap.get(leftChildIndex) < minHeap.get(smallest)) {
//            smallest = leftChildIndex;
//        }
//
//        if (rightChildIndex < minHeap.size() && minHeap.get(rightChildIndex) < minHeap.get(smallest)) {
//            smallest = rightChildIndex;
//        }
//
//        if (smallest != index) {
//            // Swap with the smallest child
//            int temp = minHeap.get(index);
//            minHeap.set(index, minHeap.get(smallest));
//            minHeap.set(smallest, temp);
//
//            // Recursively heapify the affected subtree
//            heapify(smallest);
//        }
//    }


}

