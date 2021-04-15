package ssvv.lab2;

import domain.Nota;
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

import java.time.LocalDate;

public class IntegrationTest {
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

    public IntegrationTest() {
    }

    @Test()
    public void addStudent() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("dmie2521", "Mara", 933, "bla@yahoo.com");

        Student added = service.addStudent(student);
        Assert.assertEquals(student, added);
    }

    @Test()
    public void addAssignment() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("t1", "desc1", 3, 3);

        Tema added = service.addTema(tema);
    }

    @Test()
    public void addGrade() {
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        LocalDate date = LocalDate.of(2018, 10, 15);
        Nota nota = new Nota("1", "1", "1", 10, date);

        double added = service.addNota(nota, "Looks good.");
        Assert.assertEquals(10.00, added, 2);
    }

    // Big-bang integration - in class
    @Test()
    public void testAll() {
        addStudent();
        addAssignment();
        addGrade();
    }

    // Top-down - at home
    @Test()
    public void integrationTD1() {
        addStudent();
    }

    @Test()
    public void integrationTD2() {
        integrationTD1();
        addAssignment();
    }

    @Test()
    public void integrationTD3() {
        integrationTD2();
        addGrade();
    }
}

