import java.io.*;
import java.util.ArrayList;
import java.util.List;

//StockもしくはTrade型にcsvファイルのデータを格納するところまで実行。
public class Repository{
    List<Stock> stocks = new ArrayList<>();

    //String型のrawデータをリストに格納するメソッド
    public List<Stock> setRawdataToList(String path){
        List<String> rawFilelist = new ArrayList<>();
        try {
            BufferedReader fr = new BufferedReader(new FileReader(path));
            String line;
            //rawFileListにString型で一行ずつリストに格納
            while((line = fr.readLine()) != null){
                rawFilelist.add(line);
            }

            //rawFileListから一行ずつ取り出し、Stringでそれぞれに格納
            setListToStocklist(rawFilelist);

        } catch (IOException e) {
            System.out.println("ファイルが見つかりませんでした。");
        }
        return stocks;
    }

    //rawFilelistをStock型のリストに格納する
    public List<Stock> setListToStocklist(List<String> rawFilelist){
        try {
            String header = rawFilelist.get(0);
            for (String rawline : rawFilelist){
                if(rawline.equals(header)){
                    continue;
                }
                String[] splitStock = rawline.split(",");
                String rawTicker = splitStock[0].trim();
                String rawProductName = splitStock[1].trim();
                String rawMarket = splitStock[2].trim();
                String rawSharesIssued = splitStock[3].trim();
                Market market = formatMarket(rawMarket);
                Stock stock = new Stock(rawTicker, rawProductName, market, Long.parseLong(rawSharesIssued));
                //String型で各Stock要素に不備がないかチェック
                checkAllStock(rawTicker, rawProductName, rawMarket, rawSharesIssued);
                //Stock格納チェック
                System.out.println(stock.getTicker() + stock.getProdudctName() + stock.getMarket().getLabel() + stock.getSharesIssued());
                stocks.add(stock);
            }
        }catch (Exception e) {
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
            return null;
        }
    }

    //String型データにてStockの要素をバリデーションチェック
    public void checkAllStock(String rawTicker, String rawProductName, String rawMarket, String rawSharesissued){
        Validation vali = new Validation();
        if(vali.checkRegexValidation(rawTicker, "^[0-9][ACDFGHJKLMNPRSTUWXY0-9][0-9][ACDFGHJKLMNPRSTUWXY0-9]$") && vali.checkRegexValidation(rawProductName, "^[A-Za-z0-9\\.\s]$") && vali.checkMarketValidation(rawMarket) && vali.checkRegexValidation(rawSharesissued, "^[0-9]{12}$")){
        }
        throw new IllegalArgumentException("データに不備があります。");
    }
}