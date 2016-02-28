package cn.hotwoo.alien.servicelife.model.server;

import cn.hotwoo.alien.servicelife.util.Utils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by linlongxin on 2016/2/28.
 */
public class SchedulerTransformer<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable     //----这里这能这样写-----
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Utils.Log(throwable.getLocalizedMessage());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());

        //----不能这样写，以为这里是异步操作，当网络还在请求数据的时候tObservable就返回了，会抛出NetworkOnMainThreadException异常
//        tObservable
//                .doOnError(new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        Utils.Log(throwable.getLocalizedMessage());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io());
//        return tObservable;
    }
}
