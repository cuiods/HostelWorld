package edu.nju.bl.serviceImpl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import edu.nju.bl.service.UploadService;
import edu.nju.bl.vo.OssPolicyVo;
import edu.nju.web.config.OssConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
/**
 * Oss upload policy service
 * @author cuihao
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Resource
    private OssConfig ossConfig;

    @Override
    public OssPolicyVo getUserOssPolicy() {
        return generatePolicy("user");
    }

    /**
     * Get oss policy for room picture
     *
     * @return {@link OssPolicyVo}
     */
    @Override
    public OssPolicyVo getRoomPolicy() {
        return generatePolicy("room");
    }

    private OssPolicyVo generatePolicy(String dir) {
        String endpoint = ossConfig.getEndpoint();
        String accessId = ossConfig.getAccessKeyId();
        String accessKey = ossConfig.getAccessKeySecret();
        String host = ossConfig.getHost();
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        try {
            long expireTime = 60*60*24;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            OssPolicyVo ossPolicyVo = new OssPolicyVo();
            ossPolicyVo.setAccessId(accessId);
            ossPolicyVo.setPolicy(encodedPolicy);
            ossPolicyVo.setSignature(postSignature);
            ossPolicyVo.setDir(dir);
            ossPolicyVo.setHost(host);
            ossPolicyVo.setExpire(String.valueOf(expireEndTime / 1000));
            return ossPolicyVo;
        } catch (Exception e) {
            return null;
        }
    }
}
