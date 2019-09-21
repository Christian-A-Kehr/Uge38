package facades;

import entities.RenameMe;
import entities.Semester;
import entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class StudentFacade {

    private static StudentFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private StudentFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static StudentFacade getStudentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getRenameMeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }

    }

    public List<Student> GetallStudents() {
        EntityManager em = emf.createEntityManager();
        try {
//            TypedQuery<Student> query
//                    = em.createQuery("", Student.class);
//            return query.getResultList();
            return em.createNamedQuery("Student.findAll").getResultList();
        } finally {
            em.close();
        }
    }

    public List<Student> GetStudentNamedAnders() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Student.findByFirstname").setParameter("firstname", "Anders").getResultList();
        } finally {
            em.close();
        }
    }

    public void addStudent(String firstName, String lastName) {
        Student newStudent = new Student(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newStudent);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void addStudentToSemester(long studentId, long semesterID) {

        EntityManager em = emf.createEntityManager();
        try {
            Student Stu = em.find(Student.class, studentId);
            Semester sem = em.find(Semester.class, semesterID);
            Stu.setSemester(sem);

            em.getTransaction().begin();
            em.persist(Stu);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    public List<Student> FindStudentsNamedAnd() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Student.findByLastname").setParameter("lastname", "And").getResultList();
        } finally {
            em.close();
        }
    }

    public long countStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            long Amount = (long) em.createQuery("SELECT COUNT(c) FROM Student c").getSingleResult();
            return Amount;
        } finally {
            em.close();
        }
    }

    public long getNumberOfStudentsBySemester(String semName) {
        EntityManager em = emf.createEntityManager();
        try {
            Semester sem = (Semester) em.createNamedQuery("Semester.findByName").setParameter("name", semName).getSingleResult();
            long count = (long) em.createQuery("SELECT COUNT(s) FROM Student s WHERE s.semester = :semester").setParameter("semester", sem).getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

}
