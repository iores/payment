package com.itpay.mp.user.query;

import com.itpay.core.model.page.ListPage;
import com.itpay.mp.user.dto.UserDto;

import java.io.Serializable;

/**
 * @author lfeng1
 * @date 2018/3/8 0008
 * 用户查询参数
 */
public class UserQueryParam   implements Serializable {
    private static final long serialVersionUID = -1660228509280812825L;
    /**
     * 分页数据
     */
    private ListPage<UserDto> listPage = new ListPage<>();
    /**
     * 查询数据
     */
    private UserDto queryParam;

    public ListPage<UserDto> getListPage() {
        return listPage;
    }

    public void setListPage(ListPage<UserDto> listPage) {
        this.listPage = listPage;
    }

    public UserDto getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(UserDto queryParam) {
        this.queryParam = queryParam;
    }
}
