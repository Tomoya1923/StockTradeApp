import java.io.*;
import java.util.ArrayList;
import java.util.List;

//StockもしくはTrade型にcsvファイルのデータを格納するところまで実行。
public class Repository{
    List<Stock> stocks = new ArrayList<>();
    Validation vali = new Validation();

    //Stockのヘッダーの取得とチェック
    public String getStockHeader(List<String> rawFilelist){
        try {
            String header = rawFilelist.get(0);
            vali.checkHeaderValidationForStock(header);
            return header;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //String型のrawデータをリストに格納するメソッド(これ呼び出すと今のファイルを読み込んでストックリストが返ってくる)
    public List<String> setRawdataToList(String path){
        List<String> rawFilelist = new ArrayList<>();
        try {
            BufferedReader fr = new BufferedReader(new FileReader(path));
            String line;
            //rawFileListにString型で一行ずつリストに格納
            while((line = fr.readLine()) != null){
                rawFilelist.add(line);
            }
            //rawFileListから一行ずつ取り出し、Stringでそれぞれに格納
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //この先の呼び出しをパスによって変更することで、TradeリストにもrawFilelistから格納することが可能
        return rawFilelist;
    }

    //rawFilelistをStock型のリストに格納する
    public List<Stock> setListToStocklist(List<String> rawFilelist){
        try {
            String header = getStockHeader(rawFilelist);
            for (String rawline : rawFilelist){
                if(rawline.equals(header)){
                    continue;
                }
                String[] splitStock = rawline.split(",");
                String rawTicker = splitStock[0].trim();
                String rawProductName = splitStock[1].trim();
                String rawMarket = splitStock[2].trim();
                String rawSharesIssued = splitStock[3].trim();
                //String型で各Stock要素に不備がないかチェック
                checkAllStock(rawTicker, rawProductName, rawMarket, rawSharesIssued);
                Market market = formatMarket(rawMarket);
                Stock stock = new Stock(rawTicker, rawProductName, market, Long.parseLong(rawSharesIssued));
                stocks.add(stock);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return stocks;
    }

    public Market formatMarket(String rawMarket){
        switch(rawMarket){
            case "P" :
            return Market.P;
            case "S" :
            return Market.S;
            case "G" :
            return Market.G;
            default:
            throw new RuntimeException("取引市場のフォーマットが不正です" + rawMarket);
        }
    }

    //String型データにてStockの要素をバリデーションチェック
    public void checkAllStock(String rawTicker, String rawProductName, String rawMarket, String rawSharesissued){
        //[MUST]sharesissuedの先頭が0だった場合通さない正規表現へ
        try {
            vali.checkRegexValidation(rawTicker, "^[0-9][ACDFGHJKLMNPRSTUWXY0-9][0-9][ACDFGHJKLMNPRSTUWXY0-9]$");
            vali.checkRegexValidation(rawProductName, "^[A-Za-z0-9()\\.\s]+$");
            vali.checkMarketValidation(rawMarket);
            vali.checkRegexValidation(rawSharesissued, "^[0-9]{1,12}$");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}