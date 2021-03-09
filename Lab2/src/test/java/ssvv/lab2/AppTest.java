package ssvv.lab2;


import domain.Student;
import org.junit.Assert;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    // Validators
    private StudentValidator studentValidator = new StudentValidator();
    private TemaValidator temaValidator = new TemaValidator();

    // Filenames
    private String filenameStudent = "/Users/teodoradan/Desktop/Software-Systems-Verification-and-Validation/Lab2/fisiere/Studenti.xml";
    private String filenameTema = "/Users/teodoradan/Desktop/Software-Systems-Verification-and-Validation/Lab2/fisiere/Teme.xml";
    private String filenameNota = "/Users/teodoradan/Desktop/Software-Systems-Verification-and-Validation/Lab2/fisiere/Note.xml";

    // Repositories
    private StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    private TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    private NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

    @Test
    public void positiveGroup() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("123", "Name", 933, "email");

        Student added = service.addStudent(student);
        Assert.assertEquals(student, added);
    }

    @Test(expected = ValidationException.class)
    public void negativeGroup() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("1", "Name", -2, "email");

        Student added = service.addStudent(student);
    }
}
