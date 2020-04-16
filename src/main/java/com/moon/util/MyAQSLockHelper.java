package com.moon.util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 简单版AQS同步器 -- 独占锁
 *
 * @author macbookpro
 */
@Slf4j
public class MyAQSLockHelper {

    private static final int LOCKED = 1;
    private static final int UNLOCK = 0;
    /**
     * 记录锁状态
     */
    private volatile int state = 0;

    /**
     * 记录当前持有锁的线程
     */
    private Thread lockHolder;

    /**
     * 未获取到锁的线程的等待队列
     * 底层基于CAS实现 多线程下保证了入队与出队的安全(线程安全的)
     */
    private ConcurrentLinkedQueue<Thread> waitThreadQueue = new ConcurrentLinkedQueue<>();

    /**
     * 加锁
     */
    public void lock() {
        if (acquire()) {
            return;
        }
        /* 当前线程无法获取锁 自旋等待 */
        Thread current = Thread.currentThread();
        /* 保存对线程的引用 */
        waitThreadQueue.add(current);
        for (; ; ) {
            /* 判断即将加锁的线程是否为队列中的第一个线程 */
            if (current == waitThreadQueue.peek() && acquire()) {
                waitThreadQueue.poll();
                return;
            }
            /* 阻塞当前线程 让出CPU资源 将CPU中该线程数据移到内存中的RSS(运行时数据区) */
            LockSupport.park(current);
        }
    }

    /**
     * 解锁
     */
    public void unlock() {
        /* 判断当前线程是否是持有锁的线程 */
        if (Thread.currentThread() != lockHolder) {
            throw new RuntimeException("lockHolder is not current thread");
        }
        int state = getState();
        /* 解锁 将锁状态置位unlock */
        if (compareAndSwapState(state, UNLOCK)) {
            log.info("解锁成功");
            setLockHolder(null);
            /* 唤醒等待线程 */
            Thread first = waitThreadQueue.peek();
            if (first != null) {
                LockSupport.unpark(first);
            }
        }
    }

    /**
     * 尝试性加锁
     *
     * @return 加锁结果
     */
    private boolean acquire() {
        Thread current = Thread.currentThread();
        /* 获取线程锁的初始状态 */
        int threadLockState = getState();
        /* 利用CAS原子算法保证任意时刻state变量的原子操作 */
        /* 若当前线程锁状态为未加锁(0) 则可以获取锁 并且设置当前持有锁线程 lockHolder 为当前线程 */
        if (threadLockState == UNLOCK) {
            /* 判断队列是否有等待线程 或者 当前线程是队列中的第一位 */
            if ((waitThreadQueue.size() == 0 || current == waitThreadQueue.peek()) && compareAndSwapState(UNLOCK, LOCKED)) {
                setLockHolder(current);
                log.info("加锁成功");
                return true;
            }
        }
        return false;
    }

    private boolean compareAndSwapState(int except, int update) {
        return UNSAFE.compareAndSwapInt(this, STATE_OFFSET, except, update);
    }


    /**
     * unsafe jdk 1.7+提供的魔法类 用于实现CAS
     * 可绕过Java虚拟机直接操作内存
     * C++实现
     */
    private static final Unsafe UNSAFE = reflectGetUnsafe();

    private static final long STATE_OFFSET;

    static {
        try {
            /* 为state初始化偏移量 偏移量为对象内存结构部分知识
             * 偏移量 即 找到对象在内存中的位置起的偏移量
             * 也就是在当前AQSLockHelper对象的内存地址中state在哪里
             */
            STATE_OFFSET = UNSAFE.objectFieldOffset(MyAQSLockHelper.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error();
        }
    }

    /**
     * Unsafe类必须使用反射机制获取
     */
    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            log.info("反射获取Unsafe成功");
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("反射获取Unsafe失败");
        return Unsafe.getUnsafe();
    }

    private int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    private void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }


}
