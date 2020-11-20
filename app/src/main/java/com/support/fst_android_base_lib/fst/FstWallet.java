package com.support.fst_android_base_lib.fst;

import android.content.Context;

import com.support.fst_android_base_lib.base.WCallback;
import com.support.fst_android_base_lib.util.GsonUtil;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;


public class FstWallet implements IFst{

    private static final String MOAC_JS = "file:///android_asset/fst_storm3.html";
    private static final String TAG = "FstWallet";
    private static BridgeWebView mWebview;
    private static FstWallet instance = new FstWallet();

    public void init(Context context) {
        mWebview = new BridgeWebView(context);
        mWebview.loadUrl(MOAC_JS);
    }

    public static FstWallet getInstance() {
        return instance;
    }

    public void initStorm3Provider(String node) {
        mWebview.callHandler("initStorm3", node, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });
    }

    public void initContract(String contract,String address,String node) {
        GsonUtil params = new GsonUtil("{}");
        params.putString("node",node);
        params.putString("contract",contract);
        params.putString("address",address);
        mWebview.callHandler("initContract", params.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });
    }


    @Override
    public void createWallet(WCallback callback) {
        mWebview.callHandler("createWallet", null, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });
    }

    @Override
    public void isValidAddress(String address, WCallback callback) {
        mWebview.callHandler("isValidAddress", address, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });

    }

    @Override
    public void isValidSecret(String secret, WCallback callback) {
        mWebview.callHandler("isValidSecret", secret, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });
    }

    @Override
    public void importSecret(String secret,String password, WCallback callback) {
        GsonUtil params = new GsonUtil("{}");
        params.putString("secret",secret);
        params.putString("password",password);

        mWebview.callHandler("importSecret", params.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });

    }

    @Override
    public void importWords(String words,String password, WCallback callback) {
        GsonUtil params = new GsonUtil("{}");
        params.putString("words",words);
        params.putString("password",password);
        mWebview.callHandler("importWords", params.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });

    }

    @Override
    public void toIban(String address, WCallback callback) {
        mWebview.callHandler("toIban", address, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });

    }

    @Override
    public void fromIban(String iban, WCallback callback) {
        mWebview.callHandler("fromIban", iban, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });

    }


    @Override
    public void getBalance(String address, WCallback callback) {
        mWebview.callHandler("getBalance", address, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });

    }

    @Override
    public void sendErc20Transaction(GsonUtil data, WCallback callback) {
        mWebview.callHandler("sendErc20Transaction", data.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });

    }

    @Override
    public void sendTransaction(GsonUtil data, WCallback callback) {
        mWebview.callHandler("sendTransaction", data.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });

    }

    @Override
    public void getErc20Balance(String Contract, String address, WCallback callback) {
        GsonUtil params = new GsonUtil("{}");
        params.putString("contract",Contract);
        params.putString("address",address);
        mWebview.callHandler("getErc20Balance", params.toString(), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });

    }

    @Override
    public void getGasPrice(WCallback callback) {
        mWebview.callHandler("getGasPrice", null, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });

    }

    @Override
    public void getTransactionDetail(String hash, WCallback callback) {
        mWebview.callHandler("getTransactionDetail", hash, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });


    }

    @Override
    public void getTransactionReceipt(String hash, WCallback callback) {
        mWebview.callHandler("getTransactionReceipt", hash, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                GsonUtil json = new GsonUtil(data);
                int ret = json.getInt("ret",-1);
                GsonUtil extra = json.getObject("extra", "{}");
                callback.completion(ret,extra);
            }
        });
    }
}
