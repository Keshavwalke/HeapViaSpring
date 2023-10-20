package com.example.heapspring.controller;

import com.example.heapspring.exceptions.InternalServerError;
import com.example.heapspring.service.MaxHeapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maxheap")
public class MaxHeapController {
    @Autowired
    private MaxHeapService maxHeapService;

    @GetMapping("/getMax")
    public int getMax() throws InternalServerError {
        return maxHeapService.getMax();
    }

    @PostMapping("/insert")
    public void insert(@RequestBody int value) {
        maxHeapService.insert(value);
    }

    @DeleteMapping("/delete")
    public void deleteMin() {
        maxHeapService.deleteMax();
    }

    @GetMapping("/size")
    public int size() {
        return maxHeapService.size();
    }
}
