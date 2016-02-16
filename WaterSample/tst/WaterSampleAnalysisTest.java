import static org.junit.Assert.*;

import org.junit.Test;

import com.waterSample.MainExecution.WaterSampleAnalysis;
import com.waterSample.fetchTableData.FactorWeights;
import com.waterSample.fetchTableData.WaterSample;

/*
 * @author: Louis Arokiaraj Gilbert
 * Test Class for WaterSampleAnalysis
 */

public class WaterSampleAnalysisTest {
	
	/*
	 * Test Function for CalculateFactor
	 */
	
	@Test
	public void testCalculateFactor() {
		WaterSampleAnalysis obj = new WaterSampleAnalysis();
		// Checking for NULL values
		float value = obj.calculateFactor(null, null);
		assertNotEquals(value, -1);
		
		// Checking 1 Positive Case
		WaterSample water_obj = new WaterSample();
		FactorWeights factor_obj = new FactorWeights();
		try {
			water_obj.find(1);
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			System.exit(0);
		}
		try {
			factor_obj.factorFind(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		float value_1 = obj.calculateFactor(water_obj, factor_obj);
		assertNotNull(value_1);
	}

	/*
	 * Test Function for toHash
	 */
	
	@Test
	public void testToHash() {
		WaterSampleAnalysis obj = new WaterSampleAnalysis();
		
		// Checking for NULL values
		assertEquals(obj.toHash(null, true), -1);
		
		// Checking 1 positive case
		WaterSample water_obj = new WaterSample();
		try {
			water_obj.find(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		assertEquals(obj.toHash(water_obj,true), 1);
		try {
			water_obj.find(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		assertEquals(obj.toHash(water_obj,false), 1);
	}
}
