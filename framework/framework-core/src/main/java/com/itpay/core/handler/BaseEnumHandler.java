package com.itpay.core.handler;

import com.itpay.core.base.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author feng
 * @date 2017/8/20 0020
 * 枚举处理类
 */
public class BaseEnumHandler<E extends BaseEnum> extends BaseTypeHandler<E> {

    
    private Class<E> type;

    private final E[] enums;

    /**
     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现 
     * @param type 配置文件中设置的转换类 
     */
    public BaseEnumHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String  i = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String  i = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String  i = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        if(parameter != null) {
            if(jdbcType == null) {
                ps.setString(i, (String)parameter.getValue());
            } else {
                ps.setObject(i,parameter.getValue(), jdbcType.TYPE_CODE);
            }
        }

    }

    /**
     * 枚举类型转换，由于构造函数获取了枚举的子类enums，让遍历更加高效快捷 
     * @param code 数据库中存储的自定义code属性 
     * @return code对应的枚举类
     */
    private E locateEnumStatus(String  code) {
        for(E status : enums) {
            if(status.getValue().equals(code)) {
                return status;
            }
        }
       
        return null;
    }

}
