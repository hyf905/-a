package com.ateam05.hotel.mapper;

import com.ateam05.hotel.model.domain.Checkinfo;
import com.ateam05.hotel.model.dto.CheckinfoDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hyf
 * @since 2021-06-17
 */
public interface CheckinfoMapper extends BaseMapper<Checkinfo> {

    /**
     * 查询多条数据
     *
     * @param userId 用户ID
     * @return 对象列表
     */
    List<CheckinfoDTO> querybyuserId(@Param("userId")Long userId);
}
