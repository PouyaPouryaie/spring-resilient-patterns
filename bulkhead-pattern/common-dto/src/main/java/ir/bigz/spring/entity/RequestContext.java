package ir.bigz.spring.entity;

public class RequestContext {

    private static final ThreadLocal<String> CURRENT_REQUEST_CONTEXT = new ThreadLocal<>();

    public static void setRequestId(String requestContext) {
        CURRENT_REQUEST_CONTEXT.set(requestContext);
    }

    public static String getRequestId() {
        return CURRENT_REQUEST_CONTEXT.get();
    }

    public static void clearRequestId() {
        CURRENT_REQUEST_CONTEXT.remove();
    }
}
