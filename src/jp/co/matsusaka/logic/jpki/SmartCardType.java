package jp.co.matsusaka.logic.jpki;

import jp.go.jpki.appli.JPKICardType;
import jp.go.jpki.appli.JPKIUserCertService;
import jp.go.jpki.appli.JPKIUserCertException;
 
 
 /**
  * スマートカードの種別を判別するクラス
  * @author 101031
  *
  */
class SmartCardType {
	
	// 利用者証明書情報
	private JPKIUserCertService ucs;
	
	/**
	 * 利用者証明用電子証明書を設定し、メソッドから参照できるようにする。
	 */
	public SmartCardType() {
		// 利用者証明用電子証明書
		CryptAuth ca = new CryptAuth();
		this.ucs = ca.getAuthucs();
	}
	
	/**
	 * スマートカードの種別を返す
	 * @return スマートカード区分（0:不明 1:住基カード 2:マイナンバーカード 9:エラー）
	 */
    public String fetchCardType(){
        String result = "9";
        try{
            // ICカード種別取得
            JPKICardType cardType = this.ucs.getCardType();
            // 結果の取得
            int id = cardType.getID();
            result = String.valueOf(id);
        }
        catch(JPKIUserCertException e){
            // JPKIUserCertException 発生時の処理
            System.out.println("JPKIUserCertException 発生しました");
            System.out.println(e.getErrorCode());
            e.printStackTrace();
        }
        catch(Exception e){
            // その他の例外
        	System.out.println("その他の例外発生");
        	System.out.println(e.getMessage());
        	System.out.println(e.getStackTrace());
        }
        return result;
    }
}