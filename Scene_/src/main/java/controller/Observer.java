package controller;

public interface Observer<T> {
    void update(T data);
    void notify(String message);

    void update(String message);
}
