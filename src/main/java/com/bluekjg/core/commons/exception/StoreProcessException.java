package com.bluekjg.core.commons.exception;

/**
 * 门店小程序相关的所有业务异常
 */
@SuppressWarnings("serial")
public class StoreProcessException extends RuntimeException {
	
    public StoreProcessException(String message) {
        super(message);
    }

    public StoreProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
