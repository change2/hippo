package wsmgr.state;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ws.WebSocketCall;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey Makarov on 24.04.2016.
 */
public class WsDiconnected implements State {
    private Lock lock;

    private WsContext context;
    private WebSocketCall call;
    private OkHttpClient.Builder builder;
    private Request request;
    public WsDiconnected(WsContext context) {
        this.context = context;
        initBuilder(context.getUrl());
        lock = new ReentrantLock();
    }

    private void initBuilder(String url) {
        request = new Request.Builder()
                .url(url)
                .header("User-Agent", "android client")
                .header("Connection", "Upgrade")
                .addHeader("Origin", url)
                .build();

        builder = new OkHttpClient().newBuilder();
        builder.readTimeout(0, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
    }

    @Override
    public void connected() {

    }

    @Override
    public void sendMessage(String message) {
    }

    @Override
    public void disconnected() {

    }

    @Override
    public void connecting() {

        if (context.getWsClient() == null) {
            context.setWsClient(builder.build());
        }

        if (call != null) call.cancel();
        call = WebSocketCall.create(context.getWsClient(), request);
        try {
            lock.lockInterruptibly();
            try {
                call.enqueue(context.getWsListener());
            } finally {
                lock.unlock();
            }

        } catch (InterruptedException e) {
        }
    }

    @Override
    public void ping() {

    }

}
