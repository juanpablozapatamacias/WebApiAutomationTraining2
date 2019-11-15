package training.api.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestNGRetry implements IRetryAnalyzer{
	private Map<Integer,Integer> retryCounts = new HashMap<>();
	private int retryCount = 2;
	private int count =0;
	
	@Override
	public synchronized boolean retry(ITestResult result) {
		if(!result.isSuccess() && count < retryCount) {
			System.out.println("Retry method " + result.getMethod().getMethodName() 
					+ " with parameters: "
					+ Arrays.deepToString(result.getParameters()) );
			result.setStatus(ITestResult.SKIP);
			count++;
			return true;
		}
		else {
			result.setStatus(ITestResult.SUCCESS);
		}
		
		return false;
	}

}
