package jp.co.matsusaka.logic.jpki;

import jp.go.jpki.appli.JPKICryptSignJNI;
import jp.go.jpki.appli.JPKICryptSignJNIException;
import jp.go.jpki.appli.JPKIUserCertService;

class CryptSign {
	
	// 利用者証明書情報
	private JPKIUserCertService signucs;

	/**
	 * 署名用電子証明書を取得する
	 * @return 署名用電子証明書
	 */
	public JPKIUserCertService getSignucs() {
		return signucs;
	}
	
	// コンストラクタ
	public CryptSign() {
		certJPKICryptSign();
	}
	
    public void certJPKICryptSign(){
        try{
            //コンストラクタ
            JPKICryptSignJNI jpkiCryptSign = new JPKICryptSignJNI();
     
            //(1)プロバイダハンドルを取得
            long hProv = jpkiCryptSign.cryptAcquireContext(0);
     
            //(2)プロバイダの秘密鍵ハンドルを取得
            long hKey = jpkiCryptSign.cryptGetUserKey(hProv);
     
            //(3)秘密鍵に対応する署名証明書を取得
            byte[] bCert = jpkiCryptSign.cryptGetCertificateValue(hKey);
     
            //(4)鍵ハンドルを解放
            jpkiCryptSign.cryptDestroyKey(hKey);
     
            //(5)プロバイダハンドルを解放
            jpkiCryptSign.cryptReleaseContext(hProv);
     
            //(6)利用者証明書情報を取得する
            this.signucs = new JPKIUserCertService(bCert);
        } catch (JPKICryptSignJNIException e) {
            // 機能特有の例外
            switch (e.getErrorCode()) {
            case JPKICryptSignJNIException.JPKI_ERR_PARAM :
                //引数エラー
                System.out.println("引数エラー：JPKICryptSignJNIException.JPKI_ERR_PARAM 発生しました");
                e.printStackTrace();
                break;
            case JPKICryptSignJNIException.JPKI_ERR_WINDOWS :
                //Windowsエラー
                switch (e.getWinErrorCode()) {
                    case JPKICryptSignJNIException.JPKI_WIN_ERR_NO_MEMORY :
                        System.out.println("Windowsエラー：JPKICryptSignJNIException.JPKI_WIN_ERR_NO_MEMORY 発生しました");
                        e.printStackTrace();
                    break;
                    // その他処理するエラーコードを記述
                    default :
                        // 予期しないWindowsエラー
                        System.out.println("予期しないWindowsエラー：JPKICryptSignJNIException 発生しました");
                        e.printStackTrace();
                        break;
                }
            } 
     
        } catch (Exception e) {
            // その他の例外
        	System.out.println("その他の例外発生");
        	System.out.println(e.getMessage());
        	System.out.println(e.getStackTrace());
        }
    }
}