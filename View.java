import java.util.ArrayList;
import java.util.List;

public class View {

    int TICKER_WIDTH = 10;
    int PRODUCTNAME_WIDTH = 40;
    int MARKET_WIDTH = 10;
    int SHARESISSUED_WIDTH = 20;

    public void printStocks(List<Stock> stocks, String header){
        System.out.println("=".repeat(TICKER_WIDTH+PRODUCTNAME_WIDTH+MARKET_WIDTH+SHARESISSUED_WIDTH+5));
        System.out.println(formatHeaderForStock(header));
        printFormattedStock(setStocklistToList(stocks));
        System.out.println("=".repeat(TICKER_WIDTH+PRODUCTNAME_WIDTH+MARKET_WIDTH+SHARESISSUED_WIDTH+5));
        System.out.println("");
    }

    //String型のリストにした「|」分割入りリストをプリント処理(Stock専用)
    public void printFormattedStock(List<String> formattedList){
        printSeparatorForStock();
        for (String formattedLine : formattedList){
            System.out.println(formattedLine);
        }
    }

    public void printSeparatorForStock(){
        System.out.print("-".repeat(TICKER_WIDTH+1));
        System.out.print("+");
        System.out.print("-".repeat(PRODUCTNAME_WIDTH));
        System.out.print("+");
        System.out.print("-".repeat(MARKET_WIDTH));
        System.out.print("+");
        System.out.print("-".repeat(SHARESISSUED_WIDTH));
        System.out.println("");
    }

    //Stockリストをフォーマット化してString型のリストにセットする
    public List<String> setStocklistToList(List<Stock> stocks){
        List<String> formattedList = new ArrayList<>();
        for (Stock stock : stocks){
            formattedList.add(formatLineForStock(stock));
        }
        return formattedList;
    }

    public String formatLineForStock(Stock stock){
        String formattedLine = String.format("|%-" + TICKER_WIDTH + "s|%-" + PRODUCTNAME_WIDTH + "s|%-" + MARKET_WIDTH + "s|%" + SHARESISSUED_WIDTH + "d|", stock.getTicker(), stock.getProductName(), stock.getMarket().getLabel(), stock.getSharesIssued());
        return formattedLine;
    }

    public String formatHeaderForStock(String header){
        String[] splitHeader = header.split(",");
        String formattedHeader = String.format("|%-" + TICKER_WIDTH + "s|%-" + PRODUCTNAME_WIDTH + "s|%-" + MARKET_WIDTH + "s|%" + SHARESISSUED_WIDTH + "s|", splitHeader[0],splitHeader[1],splitHeader[2],splitHeader[3]);
        return formattedHeader;
    }
}