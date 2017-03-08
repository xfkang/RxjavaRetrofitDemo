# RxjavaRetrofitDemo

2017/03/07 First Commit

Demo中基于以下几点对于RxJava与Retrofit结合使用实践过程进行封装，使开发者腾出更多精力专注于上层业务开发

1.通过泛型对于相同格式的Http返回数据进行封装

2.通过Subscriber的OnError对于相同格式的Http返回数据统一进行预处理

3.通过Subscriber的unsubscribe取消一个Http请求

4.通过一个自定义的ProgressDialog与Subscriber进行结合（请求开始展示，请求结束消失）

2017/03/08 Second Commit

添加自定义日志拦截器HttpLoggingInterceptor，打印的内容有请求的url、请求方法、响应时间、响应状态、响应内容等
