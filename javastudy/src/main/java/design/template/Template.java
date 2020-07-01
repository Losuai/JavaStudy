package design.template;

/**
 * 模板设计模式，Thread采用此设计模式
 */
public class Template {
    public static void main(String[] args) {
        Template t = new Template(){
            @Override
            protected void warpPrint(String msg) {
                System.out.println(msg);
            }
        };
        t.print("Hello");

    }
    public final void print(String msg){
        System.out.println("********");
        warpPrint(msg);
        System.out.println("********");
    }
    protected void warpPrint(String msg){

    }
}
