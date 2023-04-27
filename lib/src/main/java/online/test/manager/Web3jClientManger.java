package online.test.manager;

import java.util.concurrent.ConcurrentHashMap;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class Web3jClientManger {

	private static ConcurrentHashMap<String, Web3j>  web3 = new ConcurrentHashMap<>();
	
	public static  Web3j  getClient(String networkAddress) {
		return web3.computeIfAbsent(networkAddress, k->Web3j.build(new HttpService(networkAddress)));
	}
}