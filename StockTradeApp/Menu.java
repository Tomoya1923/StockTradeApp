import java.util.Scanner;

public class Menu{

    Scanner sc = new Scanner(System.in);

    public void mainMenu(){
        while(true){
            System.out.println("操作するメニューを選んでください");
            System.out.println(" A:銘柄マスター一覧表示");
            System.out.println(" B:銘柄マスタ新規登録");
            System.out.println(" Q:アプリケーションを修了する");
            System.out.print("入力してください> ");
            String selectedMenu = sc.nextLine().toUpperCase();

            switch(selectedMenu){
                case "A" -> {
                    System.out.println("銘柄マスタ一覧表示が選択されました");
                    Repository getFiledata = new Repository();
                    //本来はサービスクラスに指示を行い、指示に沿ったcsvパスをサービスクラスからレポジトリクラスに渡す
                    getFiledata.setRawdataToList("./Stockdata.csv");
                }
                case "B" -> System.out.println("銘柄マスタ新規登録");
                case "Q" -> {
                    System.out.println("アプリケーションを終了");
                    return;
                }
                default -> System.out.println("\"" + selectedMenu + "\"" + "に対応するメニューがありません");
            }
        }
    }
}