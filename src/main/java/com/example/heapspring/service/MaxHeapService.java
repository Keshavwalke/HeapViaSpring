package com.example.heapspring.service;
import com.example.heapspring.exceptions.InternalServerError;
import com.example.heapspring.models.MaxHeapNode;
import com.example.heapspring.repository.MaxHeapNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaxHeapService {
    @Autowired
    private MaxHeapNodeRepository repository;

    private List<Integer> maxHeap = new ArrayList<>();

    public int getMax() throws InternalServerError {
        if (maxHeap.isEmpty()) {

         //   throw new IllegalStateException("Heap is empty");
            throw new InternalServerError("heap is empty");
        }

        return maxHeap.get(0);
    }

    public void insert(int value) {
        MaxHeapNode newNode = new MaxHeapNode(value);
        repository.save(newNode);

        int N=maxHeap.size();
        maxHeap.add(value);
        int index=N;
        int parent=(index-1)/2;
        while(index>0 && maxHeap.get(parent)<maxHeap.get(index)){
            int temp=maxHeap.get(parent);
            maxHeap.set(parent,maxHeap.get(index));
            maxHeap.set(index,temp);
            index=parent;
            parent=(index-1)/2;
        }
    }

    public void deleteMax() {
        if (maxHeap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int lastIndex = maxHeap.size() - 1;
        int lastValue = maxHeap.remove(lastIndex);

        if (!maxHeap.isEmpty()) {
            maxHeap.set(0, lastValue);
            heapify(0);
        }

        Optional<MaxHeapNode> nodeToDelete = repository.findById(1L); // Assuming the first node is the maximum
        nodeToDelete.ifPresent(repository::delete);
    }

    public int size() {
        return maxHeap.size();
    }

    private void heapify(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largest = index;

        if (leftChildIndex < maxHeap.size() && maxHeap.get(leftChildIndex) > maxHeap.get(largest)) {
            largest = leftChildIndex;
        }

        if (rightChildIndex < maxHeap.size() && maxHeap.get(rightChildIndex) > maxHeap.get(largest)) {
            largest = rightChildIndex;
        }

        if (largest != index) {
            // Swap with the largest child
            int temp = maxHeap.get(index);
            maxHeap.set(index, maxHeap.get(largest));
            maxHeap.set(largest, temp);

            // Recursively heapify the affected subtree
            heapify(largest);
        }
    }
}

