package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;


public interface MemberDao {

    /**
     * 通过id查询该订单是否属于会员
     *
     * @param id
     * @return
     */
    @Select("select * from Member where id = #{id}")
    Member findById(String id);
}
