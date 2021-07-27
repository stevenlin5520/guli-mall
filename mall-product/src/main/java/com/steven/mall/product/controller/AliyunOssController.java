package com.steven.mall.product.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.steven.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author steven
 * @desc
 * @date 2021/2/6 10:05
 */
@RestController
@RequestMapping("oss")
public class AliyunOssController {

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    final private static String IMAGE_PRE = "product/";

    @PostMapping("uploadImage")
    public R uploadImage(){
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentDisposition("attachment;");
        oss.putObject(bucketName,IMAGE_PRE+"test.txt",new ByteArrayInputStream("你好".getBytes()),meta);
        URL url = oss.generatePresignedUrl(bucketName, IMAGE_PRE + "test.txt", new Date(System.currentTimeMillis()+60000));
        oss.shutdown();

        return R.ok().put("url",url);
    }

}
