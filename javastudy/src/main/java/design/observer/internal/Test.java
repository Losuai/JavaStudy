package design.observer.internal;

public class Test {
    public static void main(String[] args) {
        SubjectFor3d subjectFor3d = new SubjectFor3d();

        Observer1 observer1 = new Observer1();
        observer1.registerSubject(subjectFor3d);

        subjectFor3d.setMsg("新消息");
    }
}
