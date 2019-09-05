package com.edu.hart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author wangpeng
 * @Date 2018/12/25 10:13
 * @Description TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastDfsFile {

    /**
     * 文件原始名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String relativeFilepath;
}
