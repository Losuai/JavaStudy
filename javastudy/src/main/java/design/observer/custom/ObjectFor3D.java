package design.observer.custom;

import java.util.ArrayList;
import java.util.List;

public class ObjectFor3D implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private String  msg;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers.indexOf(observer) >= 0){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer o: observers){
            o.update(msg);
        }
    }

    /**
     * 主题更新消息
     */
    public void setMsg(String msg){
        this.msg = msg;
        notifyObservers();
    }
}
