//package oncontract;
//
//import java.math.BigInteger;
//import java.util.Arrays;
//import java.util.Collections;
//import org.web3j.abi.TypeReference;
//import org.web3j.abi.datatypes.Function;
//import org.web3j.abi.datatypes.Type;
//import org.web3j.abi.datatypes.generated.Uint256;
//import org.web3j.crypto.Credentials;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.RemoteCall;
//import org.web3j.protocol.core.RemoteFunctionCall;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.tx.Contract;
//import org.web3j.tx.TransactionManager;
//import org.web3j.tx.gas.ContractGasProvider;
//
///**
// * <p>Auto generated code.
// * <p><strong>Do not modify!</strong>
// * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
// * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
// * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
// *
// * <p>Generated with web3j version 1.4.2.
// */
//@SuppressWarnings("rawtypes")
//public class Downloads_ethtransfer_sol_EtherTransfer extends Contract {
//    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610243806100326000396000f3fe6080604052600436106100295760003560e01c80630bbcb7881461002e578063a9059cbb1461005a575b600080fd5b34801561003a57600080fd5b506000546001600160a01b03163160405190815260200160405180910390f35b61006d6100683660046101d5565b61006f565b005b6000546001600160a01b031633146100e15760405162461bcd60e51b815260206004820152602a60248201527f4f6e6c7920636f6e7472616374206f776e65722063616e2063616c6c207468696044820152693990333ab731ba34b7b760b11b60648201526084015b60405180910390fd5b6000546001600160a01b03163181106101335760405162461bcd60e51b8152602060048201526014602482015273496e73756666696369656e742062616c616e636560601b60448201526064016100d8565b600080836001600160a01b03163460405160006040518083038185875af1925050503d8060008114610181576040519150601f19603f3d011682016040523d82523d6000602084013e610186565b606091505b5091509150816101cf5760405162461bcd60e51b81526020600482015260146024820152732330b4b632b2103a379039b2b7321022ba3432b960611b60448201526064016100d8565b50505050565b600080604083850312156101e857600080fd5b82356001600160a01b03811681146101ff57600080fd5b94602093909301359350505056fea264697066735822122033685ae0d8eb8d38e23659d347f82b343e50a195194c775d9943bcdc8820612a64736f6c63430008130033";
//
//    public static final String FUNC_PRINTBALANCE = "printBalance";
//
//    public static final String FUNC_TRANSFER = "transfer";
//
//    @Deprecated
//    protected Downloads_ethtransfer_sol_EtherTransfer(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
//    }
//
//    protected Downloads_ethtransfer_sol_EtherTransfer(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
//    }
//
//    @Deprecated
//    protected Downloads_ethtransfer_sol_EtherTransfer(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
//    }
//
//    protected Downloads_ethtransfer_sol_EtherTransfer(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
//    }
//
//    public RemoteFunctionCall<BigInteger> printBalance() {
//        final Function function = new Function(FUNC_PRINTBALANCE, 
//                Arrays.<Type>asList(), 
//                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
//        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
//    }
//
//    public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount, BigInteger weiValue) {
//        final Function function = new Function(
//                FUNC_TRANSFER, 
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
//                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function, weiValue);
//    }
//
//    @Deprecated
//    public static Downloads_ethtransfer_sol_EtherTransfer load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        return new Downloads_ethtransfer_sol_EtherTransfer(contractAddress, web3j, credentials, gasPrice, gasLimit);
//    }
//
//    @Deprecated
//    public static Downloads_ethtransfer_sol_EtherTransfer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        return new Downloads_ethtransfer_sol_EtherTransfer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
//    }
//
//    public static Downloads_ethtransfer_sol_EtherTransfer load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return new Downloads_ethtransfer_sol_EtherTransfer(contractAddress, web3j, credentials, contractGasProvider);
//    }
//
//    public static Downloads_ethtransfer_sol_EtherTransfer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return new Downloads_ethtransfer_sol_EtherTransfer(contractAddress, web3j, transactionManager, contractGasProvider);
//    }
//
//    public static RemoteCall<Downloads_ethtransfer_sol_EtherTransfer> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(Downloads_ethtransfer_sol_EtherTransfer.class, web3j, credentials, contractGasProvider, BINARY, "");
//    }
//
//    public static RemoteCall<Downloads_ethtransfer_sol_EtherTransfer> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(Downloads_ethtransfer_sol_EtherTransfer.class, web3j, transactionManager, contractGasProvider, BINARY, "");
//    }
//
//    @Deprecated
//    public static RemoteCall<Downloads_ethtransfer_sol_EtherTransfer> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        return deployRemoteCall(Downloads_ethtransfer_sol_EtherTransfer.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
//    }
//
//    @Deprecated
//    public static RemoteCall<Downloads_ethtransfer_sol_EtherTransfer> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        return deployRemoteCall(Downloads_ethtransfer_sol_EtherTransfer.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
//    }
//}
