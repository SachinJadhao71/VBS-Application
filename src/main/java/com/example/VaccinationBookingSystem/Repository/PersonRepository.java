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

    @Query("select p from Person p where p.gender = :gender")
    public List<Person> findAllFemale(Gender gender);

    @Query("SELECT p FROM Person p WHERE p.dose1Taken = false and p.dose2Taken = false")
    public List<Person> findAllByVaccination();


    @Query("select p from Person p where p.age > :age and p.gender = :gender")
    public List<Person> findByAgeAndByGender(int age, Gender gender);

}
