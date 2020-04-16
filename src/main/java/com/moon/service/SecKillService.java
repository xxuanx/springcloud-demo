package com.moon.service;

import com.moon.mapper.BooksMapper;
import com.moon.model.Books;
import com.moon.util.AQSLockHelper;
import com.moon.util.MyAQSLockHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 秒杀service
 * @author macbookpro
 */
@Service
@Slf4j
public class SecKillService {

    @Resource
    private BooksMapper booksMapper;

    private MyAQSLockHelper lockHelper = new MyAQSLockHelper();

    private AQSLockHelper aqsLockHelper = new AQSLockHelper();

    public void seckill(int bookId) {
        /* 查询商品数量 */
        Books books = booksMapper.selectByPrimaryKey(bookId);
        if (books.getNum() == 0){
            /* 无库存 下单失败 */
            log.info(books.getName() + " 没有库存了!下单失败!");
            return;
        }
        /* 有库存 开始下单 并更新库存 */
        Integer booksNum = books.getNum();
        books.setNum(--booksNum);
        booksMapper.updateByPrimaryKey(books);
        log.info(books.getName() + " 下单成功，商品剩余数量：" + books.getNum());
    }

    public void seckillWithHandLock(int bookId) {
        lockHelper.lock();
        /* 查询商品数量 */
        Books books = booksMapper.selectByPrimaryKey(bookId);
        if (books.getNum() == 0){
            /* 无库存 下单失败 */
            log.info(books.getName() + " 没有库存了!下单失败!");
            lockHelper.unlock();
            return;
        }
        /* 有库存 开始下单 并更新库存 */
        Integer booksNum = books.getNum();
        books.setNum(--booksNum);
        booksMapper.updateByPrimaryKey(books);
        log.info(books.getName() + " 下单成功，商品剩余数量：" + books.getNum());
        lockHelper.unlock();

    }

    public void seckillWithAQSLock(int bookId) {
        aqsLockHelper.lock();
        /* 查询商品数量 */
        Books books = booksMapper.selectByPrimaryKey(bookId);
        if (books.getNum() == 0){
            /* 无库存 下单失败 */
            log.info(books.getName() + " 没有库存了!下单失败!");
            aqsLockHelper.unlock();
            return;
        }
        /* 有库存 开始下单 并更新库存 */
        Integer booksNum = books.getNum();
        books.setNum(--booksNum);
        booksMapper.updateByPrimaryKey(books);
        log.info(books.getName() + " 下单成功，商品剩余数量：" + books.getNum());
        aqsLockHelper.unlock();
    }


}
