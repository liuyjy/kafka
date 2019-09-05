package com.edu.hart.util;

import com.edu.hart.constant.Constant;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * fast文件上传工具类
 * <p>
 *
 * @author yongzhen
 * @date 2018/12/20-11:05
 */
public class FastDFSUtil {

    private static Logger logger = LoggerFactory.getLogger(FastDFSUtil.class);

    /**
     * 上传文件的方法
     *
     * @param byteFile   文件字节流
     * @param expandName 文件拓展名
     *
     * @return
     */
    public static String uploadFile(byte[] byteFile, String expandName) {
        String filePath = null;
        try {
            // 初始化客户端配置信息
            ClientGlobal.initByProperties(Constant.CONFIG_PATH);
            // 创建tracker客户端
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            String storageServerIp = getStorageServerIp(trackerClient, trackerServer);
            logger.info("获取文件服务器IP:" + storageServerIp);
            StorageServer storageServer = null;
            // 创建storage客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            // 文件元信息
            // NameValuePair[] metaList = new NameValuePair[2];
            // metaList[0] = new NameValuePair("source", "OA");
            // metaList[1] = new NameValuePair("author", "yangyongzhen");
            //利用字节流上传文件
            String fileId = client.upload_file1(byteFile, expandName, null);
            logger.info("upload success. file id is: " + fileId);
            filePath = storageServerIp + File.separator + fileId;
            return filePath;
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /**
     * 获得可用的storage IP
     *
     * @param trackerClient
     * @param trackerServer
     *
     * @return 返回storage IP
     */
    public static String getStorageServerIp(TrackerClient trackerClient, TrackerServer trackerServer) {
        String storageIp = null;
        if (trackerClient != null && trackerServer != null) {
            try {
                StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, "group1");
                storageIp = storageServer.getSocket().getInetAddress().getHostAddress();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return storageIp;
    }
}
