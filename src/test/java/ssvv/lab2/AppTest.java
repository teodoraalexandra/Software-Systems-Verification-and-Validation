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
    private String filenameStudent = "./fisiere/Studenti.xml";
    private String filenameTema = "./fisiere/Teme.xml";
    private String filenameNota = "./fisiere/Note.xml";

    // Repositories
    private StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    private TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    private NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

    @Test
    public void validStudent() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("dmie2521", "Mara", 933, "bla@yahoo.com");

        Student added = service.addStudent(student);
        Assert.assertEquals(student, added);
    }

    @Test(expected = ValidationException.class)
    public void negativeGroup() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("dmie2521", "Mara", -2, "bla@yahoo.com");

        Student added = service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void nullId() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("", "Mara", 933, "bla@yahoo.com");

        Student added = service.addStudent(student);
        Assert.assertEquals(student, added);
    }

    @Test(expected = ValidationException.class)
    public void nullName() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("dmie2521", "", 933, "bla@yahoo.com");

        Student added = service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void invalidEmail() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("dmie2521", "Mara", 933, "blabla");

        Student added = service.addStudent(student);
    }
}
