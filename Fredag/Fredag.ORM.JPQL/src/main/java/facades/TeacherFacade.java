/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Christian
 */
public class TeacherFacade {
     private static TeacherFacade instance;
    private static EntityManagerFactory emf;
    
     public static TeacherFacade getTeacherFacade(EntityManagerFactory _emf) {
         if (instance == null) {
            emf = _emf;
            instance = new TeacherFacade();
        }
        return instance;
    }
     
      private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
}
