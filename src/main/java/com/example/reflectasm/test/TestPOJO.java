package com.example.reflectasm.test;

/**
 * @author <a href="mailto:zhongzhangzhi@xiniaoyun.com">zhongzhangzhi(zhongzhangzhi@xiniaoyun.com)</a>
 */
public class TestPOJO {
    public TestPOJO(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    private String name;

    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
