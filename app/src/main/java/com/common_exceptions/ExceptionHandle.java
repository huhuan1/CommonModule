package com.common_exceptions;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Created by Administrator on 2019/5/2.
 */

public class ExceptionHandle {
    /**
     * 4xx（请求错误）
     * 这些状态代码表示请求可能出错，妨碍了服务器的处理。
     */
    private static final int HTTP_400 = 400;//400 （错误请求） 服务器不理解请求的语法。
    private static final int HTTP_401 = 401;//401 （未授权） 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
    private static final int HTTP_403 = 403;//403 （禁止） 服务器拒绝请求。
    private static final int HTTP_404 = 404;//404 （未找到） 服务器找不到请求的网页。
    private static final int HTTP_405 = 405;//405 （方法禁用） 禁用请求中指定的方法。
    private static final int HTTP_406 = 406;//406 （不接受） 无法使用请求的内容特性响应请求的网页。
    private static final int HTTP_407 = 407;//407 （需要代理授权） 此状态代码与 401（未授权）类似，但指定请求者应当授权使用代理。
    private static final int HTTP_408 = 408;//408 （请求超时） 服务器等候请求时发生超时。
    private static final int HTTP_409 = 409;//409 （冲突） 服务器在完成请求时发生冲突。服务器必须在响应中包含有关冲突的信息。
    private static final int HTTP_410 = 410;//410 （已删除） 如果请求的资源已永久删除，服务器就会返回此响应。
    private static final int HTTP_411 = 411;//411 （需要有效长度） 服务器不接受不含有效内容长度标头字段的请求。
    private static final int HTTP_412 = 412;//412 （未满足前提条件） 服务器未满足请求者在请求中设置的其中一个前提条件。
    private static final int HTTP_413 = 413;//413 （请求实体过大） 服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。
    private static final int HTTP_414 = 414;//414 （请求的 URI 过长） 请求的 URI（通常为网址）过长，服务器无法处理。
    private static final int HTTP_415 = 415;//415 （不支持的媒体类型） 请求的格式不受请求页面的支持。
    private static final int HTTP_416 = 416;//416 （请求范围不符合要求） 如果页面无法提供请求的范围，则服务器会返回此状态代码。
    private static final int HTTP_417 = 417;//417 （未满足期望值） 服务器未满足"期望"请求标头字段的要求。

    /**
     * 5xx（服务器错误）
     * 这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错。
     */
    private static final int HTTP_500 = 500;//500 （服务器内部错误） 服务器遇到错误，无法完成请求。
    private static final int HTTP_501 = 501;//501 （尚未实施） 服务器不具备完成请求的功能。例如，服务器无法识别请求方法时可能会返回此代码。
    private static final int HTTP_502 = 502;//502 （错误网关） 服务器作为网关或代理，从上游服务器收到无效响应。
    private static final int HTTP_503 = 503;//503 （服务不可用） 服务器目前无法使用（由于超载或停机维护）。通常，这只是暂时状态。
    private static final int HTTP_504 = 504;//504 （网关超时） 服务器作为网关或代理，但是没有及时从上游服务器收到请求。
    private static final int HTTP_505 = 505;//505 （HTTP 版本不受支持） 服务器不支持请求中所用的 HTTP 协议版本。


    /**
     * 未知错误
     */
    public static final int HTTP_UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 超时
     */
    public static final int TIME_OUT = 1003;

    /**
     * 证书出错
     */
    public static final int SSL_ERROR = 1004;
    public static final int UNKNOWN = 1005;


    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable responeThrowable;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case HTTP_400:
                    responeThrowable = new ResponeThrowable(e, HTTP_400);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "错误请求";
                    break;
                case HTTP_401:
                    responeThrowable = new ResponeThrowable(e, HTTP_401);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "未授权";
                    break;
                case HTTP_403:
                    responeThrowable = new ResponeThrowable(e, HTTP_403);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "禁止";
                    break;
                case HTTP_404:
                    responeThrowable = new ResponeThrowable(e, HTTP_404);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "未找到";
                    break;
                case HTTP_405:
                    responeThrowable = new ResponeThrowable(e, HTTP_405);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "方法禁用";
                    break;
                case HTTP_406:
                    responeThrowable = new ResponeThrowable(e, HTTP_406);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "不接受";
                    break;
                case HTTP_407:
                    responeThrowable = new ResponeThrowable(e, HTTP_407);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "需要代理授权";
                    break;
                case HTTP_408:
                    responeThrowable = new ResponeThrowable(e, HTTP_408);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "请求超时";
                    break;
                case HTTP_409:
                    responeThrowable = new ResponeThrowable(e, HTTP_409);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "冲突";
                    break;
                case HTTP_410:
                    responeThrowable = new ResponeThrowable(e, HTTP_410);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "已删除";
                    break;
                case HTTP_411:
                    responeThrowable = new ResponeThrowable(e, HTTP_411);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "需要有效长度";
                    break;
                case HTTP_412:
                    responeThrowable = new ResponeThrowable(e, HTTP_412);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "未满足前提条件";
                    break;
                case HTTP_413:
                    responeThrowable = new ResponeThrowable(e, HTTP_413);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "请求实体过大";
                    break;
                case HTTP_414:
                    responeThrowable = new ResponeThrowable(e, HTTP_414);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "请求的 URI 过长";
                    break;
                case HTTP_415:
                    responeThrowable = new ResponeThrowable(e, HTTP_415);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "不支持的媒体类型";
                    break;
                case HTTP_416:
                    responeThrowable = new ResponeThrowable(e, HTTP_416);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "请求范围不符合要求";
                    break;
                case HTTP_417:
                    responeThrowable = new ResponeThrowable(e, HTTP_417);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "未满足期望值";
                    break;
                case HTTP_500:
                    responeThrowable = new ResponeThrowable(e, HTTP_500);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "服务器内部错误";
                    break;
                case HTTP_501:
                    responeThrowable = new ResponeThrowable(e, HTTP_501);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "服务器不具备完成请求的功能";
                    break;
                case HTTP_502:
                    responeThrowable = new ResponeThrowable(e, HTTP_502);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "错误网关";
                    break;
                case HTTP_503:
                    responeThrowable = new ResponeThrowable(e, HTTP_503);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "服务不可用";
                    break;
                case HTTP_504:
                    responeThrowable = new ResponeThrowable(e, HTTP_504);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "网关超时";
                    break;
                case HTTP_505:
                    responeThrowable = new ResponeThrowable(e, HTTP_505);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "HTTP 版本不受支持";
                    break;
                default:
                    responeThrowable = new ResponeThrowable(e, HTTP_UNKNOWN);
                    responeThrowable.code = httpException.code();
                    responeThrowable.message = "未知错误1:" + httpException.code() + "," + httpException.getMessage();
                    break;
            }
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            responeThrowable = new ResponeThrowable(resultException, resultException.code);
            responeThrowable.message = resultException.message;
        } else if (e instanceof JsonParseException || e instanceof JSONException) {
            responeThrowable = new ResponeThrowable(e, PARSE_ERROR);
            responeThrowable.message = "解析错误";
        } else if (e instanceof ConnectException) {
            responeThrowable = new ResponeThrowable(e, NETWORD_ERROR);
            responeThrowable.message = "连接失败";
        } else if (e instanceof SocketTimeoutException) {
            responeThrowable = new ResponeThrowable(e, TIME_OUT);
            responeThrowable.message = "请求超时";
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            responeThrowable = new ResponeThrowable(e, SSL_ERROR);
            responeThrowable.message = "证书验证失败";
        } else {
            responeThrowable = new ResponeThrowable(e, UNKNOWN);
            responeThrowable.message = "未知错误2:" + e.getMessage();
        }
        return responeThrowable;
    }

    public static class ResponeThrowable extends Exception {
        public int code;
        public String message;

        public ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    /**
     * ServerException发生后，将自动转换为ResponeThrowable返回
     */
    class ServerException extends RuntimeException {
        int code;
        String message;
    }
}
