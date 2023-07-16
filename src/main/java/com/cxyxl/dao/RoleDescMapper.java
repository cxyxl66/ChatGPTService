package com.cxyxl.dao;

import com.cxyxl.bean.RoleDesc;
import com.cxyxl.model.CommonReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Mapper
@Repository
public interface RoleDescMapper {

    List<RoleDesc> queryRoleDesc(@Param("offSet") int offSet,
                                 @Param("pageSize") int pageSize,
                                 @Param("req") CommonReq req);

    Integer queryRoleDescCount(@Param("req") CommonReq req);

    @Update({"update role_desc set post_status = #{postStatus} where id = #{id}"})
    int updateStatus(@Param("id") String id, @Param("postStatus") int postStatus);
}
