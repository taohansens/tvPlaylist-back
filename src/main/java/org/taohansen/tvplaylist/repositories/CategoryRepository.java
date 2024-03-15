package org.taohansen.tvplaylist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taohansen.tvplaylist.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}