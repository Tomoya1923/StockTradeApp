import java.util.*;

public class Service {

    Repository repo = new Repository();
    String stockCsvPath = "./Stockdata.csv";

    public List<Stock> getStockdata(){
        List<String> rawFilelist = repo.setRawdataToList(stockCsvPath);
        List<Stock> stocks = repo.setListToStocklist(rawFilelist);
        return stocks;
    }

    public String getStockHeader(){
        List<String> rawFilelist = repo.setRawdataToList(stockCsvPath);
        String header = repo.getStockHeader(rawFilelist);
        return header;
    }
}