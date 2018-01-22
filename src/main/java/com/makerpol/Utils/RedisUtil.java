package com.makerpol.Utils;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 操作缓存数据库
 * @author user
 *
 */
public class RedisUtil {
	ReadWriteLock rw =  new ReentrantReadWriteLock();
	
}
