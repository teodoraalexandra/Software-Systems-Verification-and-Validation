package ssvv.lab2;


import domain.Student;
import domain.Tema;
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

    // LAB2 - addStudent
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

    // BVA
    @Test(expected = ValidationException.class)
    public void bva_1() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("dmie2521", "Mara", 0, "bla@yahoo.com");

        Student added = service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void bva_2() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("dmie2521", "Mara", -1, "bla@yahoo.com");

        Student added = service.addStudent(student);
    }

    @Test
    public void bva_3() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("dmie2521", "Mara", Integer.MAX_VALUE - 1, "bla@yahoo.com");

        Student added = service.addStudent(student);
        Assert.assertEquals(student, added);
    }

    @Test(expected = ValidationException.class)
    public void bva_4() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("dmie2521", "Mara", Integer.MAX_VALUE + 1, "bla@yahoo.com");

        Student added = service.addStudent(student);
    }

    @Test
    public void bva_5() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("dmie2521", "Mara", Integer.MAX_VALUE, "bla@yahoo.com");

        Student added = service.addStudent(student);
        Assert.assertEquals(student, added);
    }

    // LAB3 - addAssignment
    @Test
    public void validDeadline() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("assignment1", "description1", 5, 5);

        Tema added = service.addTema(tema);
        Assert.assertEquals(tema, added);
    }

    @Test(expected = ValidationException.class)
    public void invalidDeadline() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("assignment1", "description1", 15, 5);

        Tema added = service.addTema(tema);
    }
}
