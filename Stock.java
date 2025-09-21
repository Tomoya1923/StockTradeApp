public class Stock{
    String ticker;
    String productName;
    Market market;
    long sharesIssued;

    public Stock(String ticker, String productName, Market market, long sharesIssued){
        this.ticker = ticker;
        this.productName = productName;
        this.market = market;
        this.sharesIssued = sharesIssued;
    }

    public String getTicker(){
        return ticker;
    }

    public String getProductName(){
        return productName;
    }

    public Market getMarket(){
        return market;
    }

    public long getSharesIssued(){
        return sharesIssued;
    }

    public void setTicker(String ticker){
        this.ticker = ticker;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public void setMarket(Market market){
        this.market = market;
    }

    public void setSharesIssued(long sharesIssued){
        this.sharesIssued = sharesIssued;
    }
}