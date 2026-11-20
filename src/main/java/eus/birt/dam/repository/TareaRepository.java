package eus.birt.dam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import eus.birt.dam.entity.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
}