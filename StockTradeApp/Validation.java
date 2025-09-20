public class Validation{

    public boolean checkRegexValidation(String rawdata, String regex) {
        if(rawdata.matches(regex)){
            return true;
        }
        return false;
    }

    public boolean checkMarketValidation(String rawmarket){
        if(rawmarket.equals("Prime") || rawmarket.equals("Standard") || rawmarket.equals("Growth"));{
            return true;
        }
    }
}