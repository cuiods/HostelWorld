package edu.nju.bl.service;

import edu.nju.bl.vo.OssPolicyVo;

/**
 * Oss file upload service
 * @author cuihao
 */
public interface UploadService {

    /**
     * Get oss policy for user picture
     * @return {@link OssPolicyVo}
     */
    OssPolicyVo getUserOssPolicy();

    /**
     * Get oss policy for room picture
     * @return {@link OssPolicyVo}
     */
    OssPolicyVo getRoomPolicy();
}
