package okhttp3.logging;

import com.bumptech.glide.load.Key;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;

/* JADX INFO: loaded from: classes2.dex */
public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName(Key.STRING_CHARSET_NAME);
    private volatile Level level;
    private final Logger logger;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface Logger {
        public static final Logger DEFAULT = new Logger() { // from class: okhttp3.logging.HttpLoggingInterceptor.Logger.1
            @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
            public void log(String str) {
                Platform.get().log(4, str, null);
            }
        };

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public HttpLoggingInterceptor(Logger logger) {
        this.level = Level.NONE;
        this.logger = logger;
    }

    public HttpLoggingInterceptor setLevel(Level level) {
        if (level == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }
        this.level = level;
        return this;
    }

    public Level getLevel() {
        return this.level;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        boolean z;
        boolean z2;
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        boolean z3 = level == Level.BODY;
        boolean z4 = z3 || level == Level.HEADERS;
        RequestBody requestBodyBody = request.body();
        boolean z5 = requestBodyBody != null;
        Connection connection = chain.connection();
        String str = "--> " + request.method() + ' ' + request.url() + ' ' + (connection != null ? connection.protocol() : Protocol.HTTP_1_1);
        if (!z4 && z5) {
            str = str + " (" + requestBodyBody.contentLength() + "-byte body)";
        }
        this.logger.log(str);
        if (z4) {
            if (z5) {
                if (requestBodyBody.contentType() != null) {
                    this.logger.log("Content-Type: " + requestBodyBody.contentType());
                }
                if (requestBodyBody.contentLength() != -1) {
                    this.logger.log("Content-Length: " + requestBodyBody.contentLength());
                }
            }
            Headers headers = request.headers();
            int size = headers.size();
            int i = 0;
            while (i < size) {
                String strName = headers.name(i);
                int i2 = size;
                if ("Content-Type".equalsIgnoreCase(strName) || "Content-Length".equalsIgnoreCase(strName)) {
                    z2 = z4;
                } else {
                    z2 = z4;
                    this.logger.log(strName + ": " + headers.value(i));
                }
                i++;
                size = i2;
                z4 = z2;
            }
            z = z4;
            if (!z3 || !z5) {
                this.logger.log("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                this.logger.log("--> END " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                requestBodyBody.writeTo(buffer);
                Charset charset = UTF8;
                MediaType mediaTypeContentType = requestBodyBody.contentType();
                if (mediaTypeContentType != null) {
                    charset = mediaTypeContentType.charset(UTF8);
                }
                this.logger.log("");
                if (isPlaintext(buffer)) {
                    this.logger.log(buffer.readString(charset));
                    this.logger.log("--> END " + request.method() + " (" + requestBodyBody.contentLength() + "-byte body)");
                } else {
                    this.logger.log("--> END " + request.method() + " (binary " + requestBodyBody.contentLength() + "-byte body omitted)");
                }
            }
        } else {
            z = z4;
        }
        long jNanoTime = System.nanoTime();
        try {
            Response responseProceed = chain.proceed(request);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime);
            ResponseBody responseBodyBody = responseProceed.body();
            long jContentLength = responseBodyBody.contentLength();
            String str2 = jContentLength != -1 ? jContentLength + "-byte" : "unknown-length";
            Logger logger = this.logger;
            StringBuilder sb = new StringBuilder();
            sb.append("<-- ");
            sb.append(responseProceed.code());
            sb.append(' ');
            sb.append(responseProceed.message());
            sb.append(' ');
            sb.append(responseProceed.request().url());
            sb.append(" (");
            sb.append(millis);
            sb.append("ms");
            sb.append(z ? "" : ", " + str2 + " body");
            sb.append(')');
            logger.log(sb.toString());
            if (z) {
                Headers headers2 = responseProceed.headers();
                int size2 = headers2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    this.logger.log(headers2.name(i3) + ": " + headers2.value(i3));
                }
                if (!z3 || !HttpHeaders.hasBody(responseProceed)) {
                    this.logger.log("<-- END HTTP");
                } else if (bodyEncoded(responseProceed.headers())) {
                    this.logger.log("<-- END HTTP (encoded body omitted)");
                } else {
                    BufferedSource bufferedSourceSource = responseBodyBody.source();
                    bufferedSourceSource.request(LongCompanionObject.MAX_VALUE);
                    Buffer buffer2 = bufferedSourceSource.buffer();
                    Charset charset2 = UTF8;
                    MediaType mediaTypeContentType2 = responseBodyBody.contentType();
                    if (mediaTypeContentType2 != null) {
                        try {
                            charset2 = mediaTypeContentType2.charset(UTF8);
                        } catch (UnsupportedCharsetException unused) {
                            this.logger.log("");
                            this.logger.log("Couldn't decode the response body; charset is likely malformed.");
                            this.logger.log("<-- END HTTP");
                            return responseProceed;
                        }
                    }
                    if (!isPlaintext(buffer2)) {
                        this.logger.log("");
                        this.logger.log("<-- END HTTP (binary " + buffer2.size() + "-byte body omitted)");
                        return responseProceed;
                    }
                    if (jContentLength != 0) {
                        this.logger.log("");
                        this.logger.log(buffer2.clone().readString(charset2));
                    }
                    this.logger.log("<-- END HTTP (" + buffer2.size() + "-byte body)");
                }
            }
            return responseProceed;
        } catch (Exception e) {
            this.logger.log("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            for (int i = 0; i < 16; i++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int utf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(utf8CodePoint) && !Character.isWhitespace(utf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String str = headers.get("Content-Encoding");
        return (str == null || str.equalsIgnoreCase("identity")) ? false : true;
    }
}
