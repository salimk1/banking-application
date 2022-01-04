package ca.ucalgary.services;

import ca.ucalgary.datastore.AccountRepository;
import ca.ucalgary.domain.Account;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

/**
 * RepositoryServiceTest
 * test class for the RepositoryService class
 */
public class RepositoryServiceTest {

	/**
	 * saveAccounts_toFile
	 */
    @Test
    public void saveAccounts_toFile(){
        try {
        	
            // ARRANGE
            RepositoryService repositoryService = new RepositoryService();
            File f = repositoryService.getFile("data-stores/account-repository.json");
            Files.write(f.toPath(), "".getBytes() , new OpenOption[]{StandardOpenOption.TRUNCATE_EXISTING});
            String t = new String(Files.readAllBytes(f.toPath()));
            Assert.assertFalse(t.contains("CHEQUING"));

            Account account1 = new Account("SAVINGS");
            Account account2 = new Account("CHEQUING");
            
            // ACT
            AccountRepository.addAccount(account1);
            AccountRepository.addAccount(account2);
            
            // ASSERT
            repositoryService.saveAllRepositories();
            File f2 = repositoryService.getFile("data-stores/account-repository.json");
            t = new String(Files.readAllBytes(f2.toPath()));

            Assert.assertTrue(t.contains("SAVINGS"));
            Assert.assertTrue(t.contains("CHEQUING"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * readAccounts_fromFile
     */
    @Test
    public void readAccounts_fromFile(){
    	
        // ARRANGE
        RepositoryService repositoryService = new RepositoryService();
        
        // ASSERT
        repositoryService.restoreAllRepositories();
        
    }

}
