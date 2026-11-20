package eus.birt.dam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import eus.birt.dam.entity.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}