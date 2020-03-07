package com.noto.spring.shopping.repository;

import com.noto.spring.shopping.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
