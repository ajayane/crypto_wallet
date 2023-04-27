package online.test.interfaces;

public interface IWallet {
	public String getId();
	public String getWalletAddress();
	public double getBalance() throws Exception;;
	public String transfer(String toAddress, double amountInEth) throws Exception;
}
