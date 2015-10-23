package com.example.developmc.contact.net;

import com.example.developmc.contact.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by developmc on 15/10/23.
 */
public class GetCode {
    public GetCode(String phone, final SuccessCallback successCallback,final FailCallback failCallback){

        //执行网络通信操作
        final NetConnection netConnection = new NetConnection(Config.SERVER_URL, HttpMethod.POST,
                new NetConnection.SuccessCallback() {
                    @Override
                public void onSuccess(String result){
                        //对服务器端传回的数据解析
                        try {
                            JSONObject jsonObj = new JSONObject(result) ;
                            switch (jsonObj.getInt(Config.KEY_STATUS)){
                                case Config.RESULT_STATUS_SUCCESS:
                                    if(successCallback!=null){
                                        successCallback.onSuccess();
                                    }
                                    break;
                                default:
                                    if(failCallback!=null){
                                        failCallback.onFail();
                                    }
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if(failCallback!=null){
                                failCallback.onFail();
                            }
                        }
                    }
                },
                new NetConnection.FailCallback(){
                @Override
                public void onFail() {
                    if(failCallback!=null){
                        failCallback.onFail();
                    }
                }
        },Config.KEY_ACTION,Config.ACTION_GET_CODE,Config.KEY_PHONE_NUM,phone);
    }
    public static interface SuccessCallback{
        void onSuccess() ;
    }
    public static interface FailCallback{
        void onFail() ;
    }
}
