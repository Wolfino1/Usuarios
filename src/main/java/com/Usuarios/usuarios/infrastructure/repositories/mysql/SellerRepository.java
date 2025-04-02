package com.Usuarios.usuarios.infrastructure.repositories.mysql;

import com.Usuarios.usuarios.infrastructure.entities.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerEntity, Long> {


}
