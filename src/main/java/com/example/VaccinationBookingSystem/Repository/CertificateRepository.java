package com.example.VaccinationBookingSystem.Repository;

import com.example.VaccinationBookingSystem.Model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate,Integer> {
}
