package com.yanbin.stock.stocksecurityutils.exception;

import com.emotibot.gemini.geminiutils.exception.GeminiException;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminResponseInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午8:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockAdminException extends GeminiException {

    int code;
    String message;

    public StockAdminException(StockAdminResponseInfo stockAdminResponseInfo) {
        this.code = stockAdminResponseInfo.getCode();
        this.message = stockAdminResponseInfo.getMessage();
    }

    public StockAdminException(String message){
        this.code = 500;
        this.message = message;
    }
}
