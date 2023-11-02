package net.rakip.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.rakip.ems.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
