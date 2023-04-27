package online.test.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

public class EthUtils {
	
	
	public static  BigDecimal weiToEth(BigDecimal val) {
		return Convert.fromWei(val, Unit.ETHER);
	}
	
	public static  BigInteger ethToWei(BigDecimal val) {
		return Convert.toWei(val, Convert.Unit.ETHER).toBigInteger();
	}
	
	
	
	public static  BigDecimal weiToEth(BigInteger val) {
		return weiToEth(new BigDecimal(val));
	}

	
	public static  BigInteger ethToWei(double val) {
		return ethToWei(new BigDecimal(val));
	}

}
