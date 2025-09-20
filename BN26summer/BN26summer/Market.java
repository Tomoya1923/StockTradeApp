public enum Market {
    P("Prime", 1),
    S("Standard", 2),
    G("Growth", 3);

    private String label;
    private int id;

    private Market(String label, int id){
        this.label = label;
        this.id = id;
    }

    public String getLabel(){
        return label;
    }

    public int getId(){
        return id;
    }
}
