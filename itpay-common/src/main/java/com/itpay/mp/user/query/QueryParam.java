package com.itpay.mp.user.query;

import com.itpay.core.model.page.ListPage;

import java.io.Serializable;

/**
 * @author lfeng1
 * @date 2018/3/8 0008
 * 用户查询参数
 */
public class QueryParam<T> implements Serializable {
    private static final long serialVersionUID = -1660228509280812825L;
    /**
     * 分页数据
     */
    private ListPage<T> listPage = new ListPage<>();
    /**
     * 查询数据
     */
    private T queryParam;

    public ListPage<T> getListPage() {
        return listPage;
    }

    public void setListPage(ListPage<T> listPage) {
        this.listPage = listPage;
    }

    public T getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(T queryParam) {
        this.queryParam = queryParam;
    }
}
