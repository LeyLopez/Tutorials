package unimagdalena.edu.tutorials.security.service;

import unimagdalena.edu.tutorials.dto.DisponibilityDTO;

import java.util.List;
import java.util.Optional;

public interface DisponibilityService {

    Optional<DisponibilityDTO> findDisponibilityById(Long id);
    List<DisponibilityDTO> findAllDisponibilities();
    DisponibilityDTO saveDisponibility(DisponibilityDTO disponibilityDTO);
    void deleteDisponibilityById(Long id);
    Optional<DisponibilityDTO> updateDisponibilityById(Long id, DisponibilityDTO disponibilityDTO);

}
