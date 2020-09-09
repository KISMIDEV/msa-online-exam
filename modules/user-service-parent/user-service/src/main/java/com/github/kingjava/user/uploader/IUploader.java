package com.github.kingjava.user.uploader;

import com.github.kingjava.user.api.module.Attachment;

import java.io.InputStream;

/**
 * @author kingjava
 * @date 2020/04/05 13:36
 */
public interface IUploader {

    /**
     * 上传附件
     * @param attachment attachment
     * @param bytes bytes
     * @return Attachment
     */
    Attachment upload(Attachment attachment, byte[] bytes);

    /**
     * 저장附件信息
     * @param attachment attachment
     * @return int
     */
    int save(Attachment attachment);

    /**
     * 下载附件
     * @param attachment attachment
     * @return InputStream
     */
    InputStream download(Attachment attachment);

    /**
     * 删除附件
     * @param attachment attachment
     * @return boolean
     */
    boolean delete(Attachment attachment);

    /**
     * 批量删除
     * @param attachment attachment
     * @return boolean
     */
    boolean deleteAll(Attachment attachment);
}
