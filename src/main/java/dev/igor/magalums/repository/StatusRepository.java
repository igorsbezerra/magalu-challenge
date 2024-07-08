package dev.igor.magalums.repository;

import dev.igor.magalums.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
