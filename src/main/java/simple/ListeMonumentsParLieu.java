package simple;

import javax.persistence.*;
import java.util.*;

public class ListeMonumentsParLieu {

	public static void main(String[] args) {
		
		/*On crée une instance de l'EntityManagerFactory en utilisant
		 * le nom de notre unité de persistence
		 */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BdPersist");
        
        // On crée un EntityManager à partir de l'EntityManagerFactory
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Entity manager prêt");
        
        try {
        	
        	// On démarre une transaction
        	EntityTransaction tx = em.getTransaction();
            tx.begin();
            System.out.println("Début de la transaction");
            
            //On crée une requête en utilisant l'interface Query
            Query query = em.createQuery("SELECT l FROM Lieu l ");
            
            //On exécute la requête et on récupére la liste des monuments
            @SuppressWarnings("unchecked")
            List<Lieu> lieux = (List<Lieu>)query.getResultList();
            
            for(Lieu l1 : lieux) {
		         System.out.println("Liste des monuments à " + l1.getNomCom() + " :");
		         for(int i=0; i < l1.getMonuments().size(); i++) {
		        	 System.out.println(l1.getMonuments().get(i).getNom());
		         }
		         System.out.println();
            }
            
            //On committe la transaction
            tx.commit();
            System.out.println("Transaction confirmée");
            
        }catch (Exception e) {
        	
            // En cas d'erreur, annulez la transaction
            em.getTransaction().rollback();
            
            //pour afficher l'erreur
            e.printStackTrace();
            
        } finally {
        	
            // Fermez l'EntityManager et l'EntityManagerFactory
            em.close();
            emf.close();
        }
     
	}

}
