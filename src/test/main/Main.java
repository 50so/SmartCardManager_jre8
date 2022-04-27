package test.main;

import jp.co.matsusaka.logic.driverlisence.DriverLisenceManager;
import jp.co.matsusaka.logic.jpki.MyNumberCardManager;
import jp.co.matsusaka.model.driverlisence.FetchDliverLicenseDataReturnValue;
import jp.co.matsusaka.model.jpki.FetchMyNumberCardDataReturnValue;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
        try {
            Object driverLisenceManager = Class.forName("jp.co.matsusaka.logic.driverlisence.DriverLisenceManager").newInstance();
            Class paramCls[] = {Class.forName("java.lang.String"), Class.forName("java.lang.String")};
            Method fetchBasicData = driverLisenceManager.getClass().getMethod("fetchBasicData", paramCls);
            Object fetchDliverLicenseDataReturnValue = fetchBasicData.invoke(driverLisenceManager, new String[]{"1279", "1279"});
            int errcd = Integer.parseInt(fetchDliverLicenseDataReturnValue.getClass().getMethod("getErrorCode", null).invoke(fetchDliverLicenseDataReturnValue, null).toString());
            System.out.println(errcd);
            Object dliverLicenseData = fetchDliverLicenseDataReturnValue.getClass().getMethod("getDliverLicenseData", null).invoke(fetchDliverLicenseDataReturnValue, null);
            String name = (String)dliverLicenseData.getClass().getMethod("getName", null).invoke(dliverLicenseData, null);
            String dateOfBirth = (String)dliverLicenseData.getClass().getMethod("getDateOfBirth", null).invoke(dliverLicenseData, null);
            String address = (String)dliverLicenseData.getClass().getMethod("getAddress", null).invoke(dliverLicenseData, null);
            String honseki = (String)dliverLicenseData.getClass().getMethod("getHonseki", null).invoke(dliverLicenseData, null);
            System.out.println(name);
            System.out.println(dateOfBirth);
            System.out.println(address);
            System.out.println(honseki);
            
        } catch (UnsupportedClassVersionError e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            Object myNumberCardManager = Class.forName("jp.co.matsusaka.logic.jpki.MyNumberCardManager").getDeclaredConstructor(String.class).newInstance("2");
            Method fetchBasicData = myNumberCardManager.getClass().getMethod("fetchBasicData", null);
            Object fetchMyNumberCardDataReturnValue = fetchBasicData.invoke(myNumberCardManager, null);
            int errcd = Integer.parseInt(fetchMyNumberCardDataReturnValue.getClass().getMethod("getErrorCode", null).invoke(fetchMyNumberCardDataReturnValue, null).toString());
            System.out.println(errcd);
            Object myNumberCardData = fetchMyNumberCardDataReturnValue.getClass().getMethod("getMyNumberCardData", null).invoke(fetchMyNumberCardDataReturnValue, null);
            String name = (String)myNumberCardData.getClass().getMethod("getName", null).invoke(myNumberCardData, null);
            String dateOfBirth = (String)myNumberCardData.getClass().getMethod("getDateOfBirth", null).invoke(myNumberCardData, null);
            String address = (String)myNumberCardData.getClass().getMethod("getAddress", null).invoke(myNumberCardData, null);
            String gender = (String)myNumberCardData.getClass().getMethod("getGender", null).invoke(myNumberCardData, null);
            System.out.println(name);
            System.out.println(dateOfBirth);
            System.out.println(address);
            System.out.println(gender);
            
        } catch (UnsupportedClassVersionError e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
        }
//		DriverLisenceManager dlm = new DriverLisenceManager();
//		FetchDliverLicenseDataReturnValue menkyo = dlm.fetchBasicData("1279", "1279");
//		System.out.println("免許エラーコード" + menkyo.getErrorCode());
//		
//		MyNumberCardManager mncm = new MyNumberCardManager("2");
//		mncm.showCertViewer();
//		FetchMyNumberCardDataReturnValue myna = mncm.fetchBasicData();
//		System.out.println("マイナエラーコード" + myna.getErrorCode());
		

	}

}
