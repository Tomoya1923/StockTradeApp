
import javax.management.RuntimeErrorException;

public class Validation{

    public void checkRegexValidation(String rawdata, String regex) {
        if (!rawdata.matches(regex)){
            throw new RuntimeException("データに不備が見つかりました。" + rawdata);
        }
    }

    public void checkMarketValidation(String rawmarket){
        if(!rawmarket.equals("P") && !rawmarket.equals("S") && !rawmarket.equals("G")){
            throw new RuntimeException("取引市場のフォーマットが不正です。" + rawmarket);
        }
    }

    public void checkHeaderValidationForStock(String header){
        if(!header.equals("ticker, product_name, market, shares_ issued")){
            throw new RuntimeException("不正なヘッダーです。" + header);
        }
    }
}