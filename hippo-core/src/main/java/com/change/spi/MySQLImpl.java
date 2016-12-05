package com.change.spi;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/4
 * Time: 下午11:13
 */
public class MySQLImpl implements Spi {
    @Override
    public boolean support(String name) {
        return "mysql".equalsIgnoreCase(name);
    }

    @Override
    public String sayHello() {
        return "mysql";
    }
}
