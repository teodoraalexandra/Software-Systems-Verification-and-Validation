package validation;

import domain.Student;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        // Id validation
        if (entity.getID().equals("") || entity.getID() == null){
            throw new ValidationException("Incorrect id!");
        }

        // Name validation
        if (entity.getNume().equals("") || entity.getNume() == null){
            throw new ValidationException("Incorrect name!");
        }

        // Group validation
        if (entity.getGrupa() < 0) {
            throw new ValidationException("Incorrect group!");
        }

        // Email validation
        if (entity.getEmail().equals("") || entity.getEmail() == null){
            throw new ValidationException("Incorrect email!");
        }
        if (!entity.getEmail().contains("@")) {
            throw new ValidationException("Email should contain @!");
        }
    }
}
