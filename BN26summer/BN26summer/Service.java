import java.util.*;

public class Service {

    String stockCsvPath = "./Stockdata.csv";

    public void getStockdata(){
        Repository repo = new Repository();
        List<Stock> stocks = repo.setRawdataToList(stockCsvPath);
    }
}