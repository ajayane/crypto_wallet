package online.test.wallet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import online.test.interfaces.IWallet;
import online.test.manager.Web3jClientManger;
import online.test.util.EthUtils;
import online.test.util.StringUtils;

public class EthWallet implements IWallet{
	private String id;
	private String privateKey;
	private String address;
	private String newtworkAddress;

	public EthWallet(String id,  String privateKey, String address, String newtworkAddress) {

		if(!StringUtils.isValidString(id)) {
			throw new IllegalArgumentException(
					"invalid id, make sure the id is correct");
		}
		if(!StringUtils.isValidString(newtworkAddress)) {
			throw new IllegalArgumentException(
					"Invalid network addresss, make sure that the address is correct");
		}
		if(!WalletUtils.isValidAddress(address, 40)) {
			throw new IllegalArgumentException(
					"Invalid to Address, make sure that the address is correct");
		}
		if(!WalletUtils.isValidPrivateKey(privateKey)) {
			throw new IllegalArgumentException(
					"Invalid private key. make sure the private key is correct");
		}

		this.id = id;
		this.privateKey = privateKey;
		this.address = address;
		this.newtworkAddress = newtworkAddress;
	}

	private Web3j getClient() {
		return Web3jClientManger.getClient(this.newtworkAddress);
	}

	private BigInteger _getBalance() throws Exception {
		EthGetBalance balance =  
				getClient()
				.ethGetBalance(this.address, DefaultBlockParameterName.LATEST).send();
		return balance.getBalance();
	}

	private BigInteger getGasPrice(String toAdrress, BigInteger amountInWei) throws IOException {
		BigInteger gasPrice  = new DefaultGasProvider().getGasPrice();
		EthEstimateGas egas = getClient()
				.ethEstimateGas(
						Transaction.createEtherTransaction(this.address, null, gasPrice, null, toAdrress, amountInWei))
				.send();
		BigInteger gasUsed = egas.getAmountUsed();
		return gasUsed.multiply(gasPrice);
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getWalletAddress() {
		return this.address;
	}

	@Override
	public  double getBalance() throws Exception {
		return EthUtils.weiToEth(this._getBalance()).doubleValue();
	}

	@Override
	public String transfer(String toAddress, double amountInEth) throws InterruptedException, IOException, TransactionException, Exception  {
		if(!WalletUtils.isValidAddress(toAddress, 40)) {
			throw new IllegalArgumentException(
					"Invalid to Address, make sure that the address is correct");
		}

		if(this.address.equalsIgnoreCase(toAddress)) {
			throw new IllegalArgumentException(
					"To and from address cannot be same");
		}

		if(!(amountInEth>0)) {
			throw new IllegalArgumentException(
					"amount must be greater tha zero");
		}

		BigInteger amountInWei = EthUtils.ethToWei(amountInEth);

		BigInteger currentWalletBalance = this._getBalance();

		if((amountInWei.compareTo(currentWalletBalance)==1)) {
			throw new IllegalArgumentException(
					"amount must be less than avialble wallet balance. Available wallet balance is :" + currentWalletBalance );
		}

		BigInteger gasPrice = this.getGasPrice(toAddress, amountInWei);
		BigInteger totalAmount = amountInWei.add(gasPrice);
		if(totalAmount.compareTo(currentWalletBalance)==1) {
			throw new IllegalArgumentException(
					"Insufficient gas price available for transaction. "
					+ "consider gas price required this amount.Gas price used for "
							+ amountInWei+"is " + gasPrice );
		}

		Credentials c = Credentials.create(this.privateKey);
		TransactionReceipt  tr = Transfer.
				sendFunds(getClient(), c, toAddress, new BigDecimal(amountInWei),
						Convert.Unit.WEI)
				.send();
		System.out.println("Block Number " + tr.getBlockNumber());
		System.out.println("Block hash " + tr.getBlockHash());
		System.out.println("Transaction hash " + tr.getTransactionHash());
		return tr.getTransactionHash();


	}

}
