package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserRepo repo;

    // Lấy danh sách tất cả users
    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // Tạo user mới
    @PostMapping
    public User createUser(@RequestBody User user) {
        return repo.save(user);
    }

    // Lấy user theo id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Cập nhật user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
        return repo.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    return repo.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Xóa user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
