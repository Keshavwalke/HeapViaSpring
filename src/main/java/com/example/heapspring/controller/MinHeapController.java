package com.example.heapspring.controller;

import com.example.heapspring.service.MinHeapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/minheap")
public class MinHeapController {
    @Autowired
    private MinHeapService minHeapService;

    @GetMapping("/getMin")
    public int getMin() {
        return minHeapService.getMin();
    }

    @PostMapping("/insert")
    public void insert(@RequestBody int value) {
        minHeapService.insert(value);
    }

    @DeleteMapping("/deleteMin")
    public void deleteMin() {
        minHeapService.deleteMin();
    }

    @GetMapping("/size")
    public int size() {
        return minHeapService.size();
    }
}
