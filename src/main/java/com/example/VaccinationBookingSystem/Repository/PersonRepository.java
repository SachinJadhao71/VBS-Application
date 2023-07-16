package com.example.VaccinationBookingSystem.Repository;

import com.example.VaccinationBookingSystem.Enum.Gender;
import com.example.VaccinationBookingSystem.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

    Person findByEmailId(String email);

    @Query(value = "select * from person where age > :age", nativeQuery = true)
    List<Person> findByAge(int age);



}
