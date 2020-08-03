package me.ezerror.models._1_listener.i;

/**
 * 监听异步方法执行完成后，执行相关代码
 */
@FunctionalInterface
public interface OnCompletedListner {
    void execute();
}
