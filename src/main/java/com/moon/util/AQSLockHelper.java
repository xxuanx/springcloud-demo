package com.moon.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 实现AQS
 *
 * @author macbookpro
 */
@Slf4j
public class AQSLockHelper {

    private static final int LOCKED = 1;
    private static final int UNLOCK = 0;

    /**
     *  可重入的公平锁
     */
    private AbstractQueuedSynchronizer abstractQueuedSynchronizer = new AbstractQueuedSynchronizer() {
        @Override
        protected boolean tryAcquire(int arg) {
            int state = this.getState();
            if (state == 0) {
                //先判断队列是否有元素
                if(!hasQueuedPredecessors() && compareAndSetState(0, 1)) {
                    this.setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else {
                if (this.getExclusiveOwnerThread() == Thread.currentThread()) {
                    this.setState(state + 1);
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (this.getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalStateException();
            }
            //state - 1
            this.setState(this.getState() - 1);
            boolean release = getState() == 0;
            if (release) {
                //state为0则清除掉当前线程
                this.setExclusiveOwnerThread((Thread) null);
            }
            return release;
        }
    };

    /**
     *  可重入的非公平锁
     */
    private AbstractQueuedSynchronizer abstractQueuedSynchronizer1 = new AbstractQueuedSynchronizer() {
        @Override
        protected boolean tryAcquire(int arg) {
            int state = this.getState();
            if (state == 0) {
                if(compareAndSetState(0, 1)) {
                    //第一次获取到资源
                    this.setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else {
                //已经有线程获取到资源，判断是否为当前线程
                if (this.getExclusiveOwnerThread() == Thread.currentThread()) {
                    //是当前线程则state+1
                    this.setState(state + 1);
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (this.getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalStateException();
            }
            //state - 1
            this.setState(this.getState() - 1);
            boolean release = getState() == 0;
            if (release) {
                //state为0则清除掉当前线程
                this.setExclusiveOwnerThread((Thread) null);
            }
            return release;
        }
    };

    /**
     * 不可重入的锁
     */
    private AbstractQueuedSynchronizer abstractQueuedSynchronizer2 = new AbstractQueuedSynchronizer() {
        @Override
        protected boolean tryAcquire(int arg) {
            //cas修改state为1，失败返回false
            return this.compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            //释放没有线程间的竞争，直接置0
            this.setState(0);
            return true;
        }
    };

    public void lock() {
        abstractQueuedSynchronizer.acquire(0);
    }

    public void unlock(){
        abstractQueuedSynchronizer.release(0);
    }
}
