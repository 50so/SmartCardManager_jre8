package jp.co.matsusaka.logic.jpki;

import jp.go.jpki.appli.JPKICryptAuthJNI;
import jp.go.jpki.appli.JPKICryptAuthJNIException;
import jp.go.jpki.appli.JPKIUserCertService;

 
class CryptAuth {
	
	// 利用者証明用電子証明書
	private JPKIUserCertService authucs;

	/**
	 * 利用者証明用電子証明書を取得する
	 * @return 利用者証明用電子証明書
	 */
	public JPKIUserCertService getAuthucs() {
		return authucs;
	}
	
	// コンストラクタ
	public CryptAuth() {
		certJPKICryptAuth();
	}
	
	/**
	 * 利用者証明用電子証明書の認証（4桁数字PW）
	 * オブジェクト生成時に実行
	 */
	private void certJPKICryptAuth() {
    	 
        try{
            //コンストラクタ
            JPKICryptAuthJNI jpkiCryptAuth = new JPKICryptAuthJNI();
     
            //(1)プロバイダハンドルを取得
            long hProv = jpkiCryptAuth.cryptAcquireContext(0);
     
            //(2)プロバイダの秘密鍵ハンドルを取得
            long hKey = jpkiCryptAuth.cryptGetUserKey(hProv);
     
            //(3)秘密鍵に対応する利用者証明書を取得
            byte[] bCert = jpkiCryptAuth.cryptGetCertificateValue(hKey);
     
            //(4)鍵ハンドルを解放
            jpkiCryptAuth.cryptDestroyKey(hKey);
     
            //(5)プロバイダハンドルを解放
            jpkiCryptAuth.cryptReleaseContext(hProv);
     
            //(6)利用者証明用電子証明書を取得する
            this.authucs = new JPKIUserCertService(bCert);
            
        } catch (JPKICryptAuthJNIException e) {
      
            // 機能特有の例外
            switch (e.getErrorCode()) {
            case JPKICryptAuthJNIException.JPKI_ERR_PARAM :
                //引数エラー
                System.out.println("引数エラー：JPKICryptAuthJNIException.JPKI_ERR_PARAM 発生しました");
                e.printStackTrace();
     
                break;
            case JPKICryptAuthJNIException.JPKI_ERR_WINDOWS :
                //Windowsエラー
                switch (e.getWinErrorCode()) {
                case JPKICryptAuthJNIException.JPKI_WIN_ERR_NO_MEMORY :
                    System.out.println("Windowsエラー：JPKICryptAuthJNIException.JPKI_WIN_ERR_NO_MEMORY 発生しました");
                    e.printStackTrace();
                break;
                // その他処理するエラーコードを記述
                default :
                    // 予期しないWindowsエラー
                    System.out.println("予期しないWindowsエラー：JPKICryptAuthJNIException 発生しました");
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