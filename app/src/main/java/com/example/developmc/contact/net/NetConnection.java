package com.example.developmc.contact.net;

import android.os.AsyncTask;

import com.example.developmc.contact.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.transform.Result;

/**
 * Created by developmc on 15/10/20.
 */
public class NetConnection {
    public NetConnection(final String url, final HttpMethod method, final SuccessCallback successCallback,
                         final FailCallback failCallback,final String... kvs){

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                StringBuffer paramsStr = new StringBuffer();
                for(int i=0;i<kvs.length;i+=2){
                    paramsStr.append(kvs[i]).append(kvs[i+1]).append("&") ;

                }
                //执行网络连接
                try {
                    URLConnection uc  ;
                    switch(method){
                        case POST:
                            //往服务器段输出
                            uc = new URL(url).openConnection() ;
                            uc.setDoOutput(true);
                            BufferedWriter bw = new BufferedWriter(
                                    new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                            bw.write(paramsStr.toString()) ;
                            break;
                        default:
                            uc = new URL(url+"?"+paramsStr.toString()).openConnection() ;
                            break;

                    }
                    System.out.print("Result URL:"+uc.getURL());
                    System.out.print("Request data:"+paramsStr);


                    //读取数据
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(uc.getInputStream(),Config.CHARSET)
                    ) ;
                    String line = null;
                    StringBuffer result = new StringBuffer() ;
                    while((line=br.readLine())!=null){
                        result.append(line) ;
                    }
                    System.out.print("Result:"+result);
                    return result.toString() ;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                if(result!=null){
                    if(successCallback!=null){
                        //回调，把结果传出去
                        successCallback.onSuccess(result);
                    }
                }
                else{
                    if(failCallback!=null){
                        failCallback.onFail();
                    }
                }
                super.onPostExecute(result);
            }
        } ;
    }
    public static interface SuccessCallback{
        void onSuccess(String result);
    }
    public static interface FailCallback{
        void onFail();
    }
}
