
package app.prog.service;

import app.prog.model.AuthorEntity;
import app.prog.model.AuthorEntity;
import app.prog.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<AuthorEntity>getAllAuthors(){
        return authorRepository.findAll();
    }

    public List<AuthorEntity> createAuthors(List<AuthorEntity> toCreate) {
        toCreate.get(0).setId(authorRepository.findAll().size()+1);

        return authorRepository.saveAll(toCreate);
    }

    public List<AuthorEntity> updateAuthor(List<AuthorEntity> toUpdate) {
        return authorRepository.saveAll(toUpdate);
    }

    //TODO-3: should I use Integer here or int ? Why ?
    public AuthorEntity deleteAuthor(int AuthorEntityId) {
        /*
        TIPS: From the API, the Class Optional<T> is :
        A container object which may or may not contain a non-null value.
        If a value is present, isPresent() returns true.
        If no value is present, the object is considered empty and isPresent() returns false.

        T is the type of the value, for example : here the class type is AuthorEntity
         */
        Optional<AuthorEntity> optional = authorRepository.findById((long)AuthorEntityId);
        if (optional.isPresent()) {
            authorRepository.delete(optional.get());
            return optional.get();
        } else {
        /*
        TODO-5 : The exception appears as an internal server error, status 500.
        We all know that the appropriate error status is the 404 Not Found.
        Any solution to do this ?
        These links may help you :
        Link 1 : https://www.baeldung.com/spring-response-entity
        Link 2 : https://www.baeldung.com/exception-handling-for-rest-with-spring
         */
            throw new RuntimeException("AuthorEntity." + AuthorEntityId + " not found");
        }
    }
}
