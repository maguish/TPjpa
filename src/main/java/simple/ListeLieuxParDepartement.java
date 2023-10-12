package simple;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ListeLieuxParDepartement {
	
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
            Query query = em.createQuery("SELECT d FROM Departement d ");
            
            //On exécute la requête et on récupére la liste des monuments
            @SuppressWarnings("unchecked")
            List<Departement> departements = (List<Departement>)query.getResultList();
            
            for(Departement d1 : departements) {
		         System.out.println("Liste des lieux dans le département de (du) " + d1.getNomDep() + " :");
		         for(int i=0; i < d1.getLieux().size(); i++) {
		        	 System.out.println(d1.getLieux().get(i).getNomCom());
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
