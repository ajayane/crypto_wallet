package online.test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;

import online.test.interfaces.IWallet;
import online.test.manager.Web3jClientManger;
import online.test.wallet.EthWallet;

class WalletTester {
	final String GANACHE_URL = "http://localhost:7545";
	
	private EthWallet createWalletForBob() {
		return new EthWallet("BOB", 
				"0x61e4ace6c3d54afa5bab28f1912fbd976eb06f3b5d6c9e546fc21cf41b854425",
				"0xA23cfFa6595a0e83A1F760301DF8f22AE7bC631d", 
				GANACHE_URL);
	}
	private EthWallet createWalletForALice() {
		return new EthWallet("ALICE", 
				"0xd609ac19db554a515e97bb84eed148316485f899b437fc502897eea98f88bfff",
				"0x9941bf2F1296ad6b2b19D202b1D8b04dCaeF0069", 
				GANACHE_URL);
	}
	@BeforeEach
    public void pingBlockchainNetwork() {
		Web3j web3j = Web3jClientManger.getClient(GANACHE_URL);
        try {
            EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
            if (blockNumber.hasError()) {
                // Handle the error
//                throw new Exception("Error getting block number: " + blockNumber.getError().getMessage());
                fail("Error getting block number: " + blockNumber.getError().getMessage());
            } else {
            	
                // Print the latest block number
                System.out.println("Latest block number: " + blockNumber.getBlockNumber());
            }
        } catch (Exception e) {
            // Handle the exception
//            throw new RuntimeException("Error pinging blockchain network: " + e.getMessage());
        	fail("Error pinging blockchain network: " + e.getMessage());
        }
    }
	@Test
	void testPositive()  {
		try {
			IWallet bob = createWalletForBob();
			IWallet alice = createWalletForALice();
			System.out.println("Bob balance before transfer : " + bob.getBalance());
			System.out.println("Alice balance before transfer : " + alice.getBalance());

			String hash = alice.transfer(bob.getWalletAddress(), 9.86);
			assertTrue(hash instanceof String);


			System.out.println("Bob balance after transfer : " + bob.getBalance());
			System.out.println("Alice balance after transfer : " +alice.getBalance());
		}catch (Exception e) {
			fail("positive test case failed with " + e.getMessage());
		}
	}


	@Test
	void testInvalidInitilization() {
		assertThrows(IllegalArgumentException.class, ()-> new EthWallet("", 
				"0xd609ac19db554a515e97bb84eed148316485f899b437fc502897eea98f88bfff",
				"0x9941bf2F1296ad6b2b19D202b1D8b04dCaeF0069", 
				GANACHE_URL));
		assertThrows(IllegalArgumentException.class, ()-> new EthWallet("ALICE",
				"",
				"0x9941bf2F1296ad6b2b19D202b1D8b04dCaeF0069", 
				GANACHE_URL));
		assertThrows(IllegalArgumentException.class, ()-> new EthWallet("ALICE", 
				"0xd609ac19db554a515e97bb84eed148316485f899b437fc502897eea98f88bfff",
				"", 
				GANACHE_URL));
		assertThrows(IllegalArgumentException.class, ()->  new EthWallet("ALICE", 
				"0xd609ac19db554a515e97bb84eed148316485f899b437fc502897eea98f88bfff",
				"0x9941bf2F1296ad6b2b19D202b1D8b04dCaeF0069", 
				null));
	}

	@Test
	void testInvalidAmount() {
		IWallet bob = createWalletForBob();
		IWallet alice = createWalletForALice();

		assertThrows(IllegalArgumentException.class, 
				()->  bob.transfer(alice.getWalletAddress(), -1));
		assertThrows(IllegalArgumentException.class, 
				()->  bob.transfer(alice.getWalletAddress(), 0));
	}
	@Test
	void testInsufficentFunds() {
		IWallet bob = createWalletForBob();
		IWallet alice = createWalletForALice();

		assertThrows(IllegalArgumentException.class, 
				()->  bob.transfer(alice.getWalletAddress(), bob.getBalance()));
		assertThrows(IllegalArgumentException.class, 
				()->  bob.transfer(alice.getWalletAddress(), 100));
	}
	@Test
	void testSameAddressTransfer() {
		EthWallet bob = createWalletForBob();
		assertThrows(IllegalArgumentException.class, 
				()->  bob.transfer(bob.getWalletAddress(), 1));
	}

}
