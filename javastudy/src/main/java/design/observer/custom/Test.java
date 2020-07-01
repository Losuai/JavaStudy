package design.observer.custom;

public class Test {
    @org.junit.Test
    public void test(){
        ObjectFor3D objectFor3D = new ObjectFor3D();
        Observer observer1 = new Observer1(objectFor3D);
        Observer observer2 = new Observer2(objectFor3D);
        objectFor3D.setMsg("凉凉月色");
    }
}
