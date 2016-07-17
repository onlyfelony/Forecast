package Utility;

/**
 * Created by Administrator on 2016/7/16.
 */
public interface HttpCallBackListener {
    void  onFinish(String response);
    void  onError(Exception e);
}
