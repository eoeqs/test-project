package org.eoeqs.testproject.repositories;


import org.eoeqs.testproject.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {}