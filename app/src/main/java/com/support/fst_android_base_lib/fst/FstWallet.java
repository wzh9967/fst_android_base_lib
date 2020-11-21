package com.support.fst_android_base_lib.fst;

import android.content.Context;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.support.fst_android_base_lib.base.WCallback;
import com.support.fst_android_base_lib.util.GsonUtil;


public class FstWallet implements IFst {

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

    /**
     * 初始化Storm3 节点
     * @param node  节点
     */
    public void initStorm3Provider(String node) {
        mWebview.callHandler("initStorm3", node, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });
    }

    /**
     * 初始化合约
     * @param contract 合约地址
     * @param address 钱包地址
     * @param node 节点
     */
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


    /**
     * 创建一个钱包，返回密钥，地址，助记词
     * @param callback {"secret":"","address":"","words":""}
     */
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

    /**
     * 确认地址是否可用
     * @param address 地址
     * @param callback {"isAddress":""}
     */
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

    /**
     * 确认密钥是否可用
     * @param secret 密钥
     * @param callback {"isSecret":""}
     */
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

    /**
     * 导入密钥
     * @param secret 密钥
     * @param password 密码
     * @param callback {"secret":"","address":""}
     */
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

    /**
     * 导入助记词
     * @param words 密钥
     * @param password 密码
     * @param callback {"secret":"","address":""}
     */
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

    /**
     * 将地址转换为Iban
     * @param address 地址
     * @param callback {"Iban":""}
     */
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

    /**
     * 将Iban转换为地址
     * @param iban Iban地址
     * @param callback {"address":""}
     */
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


    /**
     * 获取余额
     * @param address 地址
     * @param callback {"balance":""}
     */
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

    /**
     * 发送erc20交易
     * @param data {"address":"","to":"","secret":"","value":"","gasLimit":“”,"gasPrice":"","data":"","contract":""}
     * @param callback {"hash":""}
     */
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

    /**
     * 发送erc20交易
     * @param data {"address":"","to":"","secret":"","value":"","gasLimit":“”,"gasPrice":"","data":""}
     * @param callback {"hash":""}
     */
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

    /**
     * 获取对应Erc20币的余额
     * @param Contract Erc20地址
     * @param address 钱包地址
     * @param callback {"balance":""}
     */
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

    /**
     * 获取链上的GasPrice
     * @param callback {"GasPrice":""}
     */
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

    /**
     * 获取交易详情
     * @param hash 交易hash
     * @param callback {"data":"{}"}
     */
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

    /**
     * 获取交易详情
     * @param hash 交易hash
     * @param callback {"data":"{}"}
     */
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
