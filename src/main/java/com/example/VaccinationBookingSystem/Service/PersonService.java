package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Enum.Gender;
import com.example.VaccinationBookingSystem.Exceptions.EmailNotFoundException;
import com.example.VaccinationBookingSystem.Exceptions.PersonNotFoundException;
import com.example.VaccinationBookingSystem.Exceptions.personAlreadyExistsException;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.AddPersonRequestdto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.AddPersonResponsedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public AddPersonResponsedto addPerson(AddPersonRequestdto addPersonRequestdto) {

//        we cannot save dto directly because databse dont know dto he will only understand entity
//        so we have to convert dto to the entity

        Person person = new Person();

        person.setName(addPersonRequestdto.getName());
        person.setAge(addPersonRequestdto.getAge());
        person.setEmailId(addPersonRequestdto.getEmailId());
        person.setGender(addPersonRequestdto.getGender());

        person.setDose1Taken(false);
        person.setDose2Taken(false);

        Person savedPerson = personRepository.save(person);

//        we have save person successfully but we dont want to show entire entity so we will create
//        response dto  and then we can return only necessory detail

        AddPersonResponsedto addPersonResponsedto = new AddPersonResponsedto();

        addPersonResponsedto.setName(savedPerson.getName());
        addPersonResponsedto.setMessage("Congrats..! your Registration has been successfully done");

        return addPersonResponsedto;


    }

    public String updateEmail(String oldEmail, String newEmail) {

        Person person = personRepository.findByEmailId(oldEmail);

        if(person == null){
            throw new EmailNotFoundException("oldEmail does not exists");
        }

        person.setEmailId(newEmail);

        personRepository.save(person);

        return "Your Email successfully updated";
    }

    public List<String> getAllMalesOfGreaterThanCertainAge(int age) {
        List<Person> all = personRepository.findByAge(age);

        List<String> names = new ArrayList<>();
        for(int i=0; i<all.size(); i++){
            if(all.get(i).getGender() == Gender.MALE)
                names.add(all.get(i).getName());
        }
        return names;
    }

    public List<String> getAllFemalesWhoTakenDose1() {
        List<Person> personList = personRepository.findAll();

        List<String> list = new ArrayList<>();

        for(int i=0; i<personList.size(); i++){
            Person person = personList.get(i);

            if(person.isDose1Taken()==true && person.isDose2Taken()==false && person.getGender()==Gender.FEMALE){
                list.add(person.getName());
            }
        }

        return list;
    }

    public List<String> getAllWhoTakeBothDoses() {

        List<Person> persons = personRepository.findAll();

        List<String> ans = new ArrayList<>();

        for(int i=0; i<persons.size(); i++){
            Person person = persons.get(i);

            if(person.isDose1Taken()==true && person.isDose2Taken()==true){
                ans.add(person.getName());
            }
        }

        return ans;
    }

    public List<String> getAllWhoHaveNotTakenAnyDose() {
        List<Person> all = personRepository.findAll();

        List<String> ans = new ArrayList<>();

        for(int i=0; i< all.size(); i++){
            Person person = all.get(i);

            if(!person.isDose1Taken() && !person.isDose2Taken()){
                ans.add(person.getName());
            }
        }

        return ans;
    }

    public List<String> getAllTheFamlesAboveTheCertainAgeWithDose1(int age) {

        List<Person> all = personRepository.findByAge(age);

        List<String> ans= new ArrayList<>();

        for(int i=0; i<all.size(); i++){
            Person person = all.get(i);

            if(person.getGender()==Gender.FEMALE && person.isDose1Taken() && !person.isDose2Taken()){
                ans.add(person.getName());
            }
        }

        return ans;
    }

    public List<String> getAllTheMalesAboveCertainAgeAndTakenBothDose(int age) {
        List<Person> all = personRepository.findByAge(age);

        List<String> ans = new ArrayList<>();

        for(int i=0; i<all.size(); i++){
            Person person = all.get(i);

            if(person.isDose1Taken() && person.isDose2Taken() && person.getGender()==Gender.MALE){
                ans.add(person.getName());
            }
        }

        return ans;
    }
}
