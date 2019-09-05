package com.edu.hart.entity.exception;

/**
 * 文件异常
 * Create by 叶云轩 at 2018/1/24 19:29
 * Concat at tdg_yyx@foxmail.com
 */
public class FileException extends RuntimeException {


    private static final long serialVersionUID = -7414796038344917779L;

    public FileException(String message) {
        super(message);
    }
}

