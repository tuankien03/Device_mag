package Constant;

public class CodeStatus {

    // Informational Responses
    public static final int CONTINUE = 100;
    public static final int SWITCHING_PROTOCOLS = 101;
    public static final int PROCESSING = 102;

    // Success Responses
    public static final int SUCCESS = 200;
    public static final int CREATED = 201;
    public static final int ACCEPTED = 202;
    public static final int NON_AUTHORITATIVE_INFORMATION = 203;
    public static final int NO_CONTENT = 204;
    public static final int RESET_CONTENT = 205;
    public static final int PARTIAL_CONTENT = 206;

    // Redirection Messages
    public static final int MULTIPLE_CHOICES = 300;
    public static final int MOVED_PERMANENTLY = 301;
    public static final int FOUND = 302;
    public static final int SEE_OTHER = 303;
    public static final int NOT_MODIFIED = 304;
    public static final int TEMPORARY_REDIRECT = 307;
    public static final int PERMANENT_REDIRECT = 308;

    // Client Error Responses
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int PAYMENT_REQUIRED = 402;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int NOT_ACCEPTABLE = 406;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int CONFLICT = 409;
    public static final int GONE = 410;
    public static final int LENGTH_REQUIRED = 411;

    // Server Error Responses
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int NOT_IMPLEMENTED = 501;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;

    // Text Status
    public static final String CONTINUE_TEXT = "Continue";
    public static final String SWITCHING_PROTOCOLS_TEXT = "Switching Protocols";
    public static final String PROCESSING_TEXT = "Processing";

    public static final String SUCCESS_TEXT = "Success";
    public static final String CREATED_TEXT = "Created";
    public static final String ACCEPTED_TEXT = "Accepted";
    public static final String NON_AUTHORITATIVE_INFORMATION_TEXT = "Non-Authoritative Information";
    public static final String NO_CONTENT_TEXT = "No Content";
    public static final String RESET_CONTENT_TEXT = "Reset Content";
    public static final String PARTIAL_CONTENT_TEXT = "Partial Content";

    public static final String MULTIPLE_CHOICES_TEXT = "Multiple Choices";
    public static final String MOVED_PERMANENTLY_TEXT = "Moved Permanently";
    public static final String FOUND_TEXT = "Found";
    public static final String SEE_OTHER_TEXT = "See Other";
    public static final String NOT_MODIFIED_TEXT = "Not Modified";
    public static final String TEMPORARY_REDIRECT_TEXT = "Temporary Redirect";
    public static final String PERMANENT_REDIRECT_TEXT = "Permanent Redirect";

    public static final String BAD_REQUEST_TEXT = "Bad Request";
    public static final String UNAUTHORIZED_TEXT = "Unauthorized";
    public static final String PAYMENT_REQUIRED_TEXT = "Payment Required";
    public static final String FORBIDDEN_TEXT = "Forbidden";
    public static final String NOT_FOUND_TEXT = "Not Found";
    public static final String METHOD_NOT_ALLOWED_TEXT = "Method Not Allowed";
    public static final String NOT_ACCEPTABLE_TEXT = "Not Acceptable";
    public static final String REQUEST_TIMEOUT_TEXT = "Request Timeout";
    public static final String CONFLICT_TEXT = "Conflict";
    public static final String GONE_TEXT = "Gone";
    public static final String LENGTH_REQUIRED_TEXT = "Length Required";

    public static final String INTERNAL_SERVER_ERROR_TEXT = "Internal Server Error";
    public static final String NOT_IMPLEMENTED_TEXT = "Not Implemented";
    public static final String BAD_GATEWAY_TEXT = "Bad Gateway";
    public static final String SERVICE_UNAVAILABLE_TEXT = "Service Unavailable";
    public static final String GATEWAY_TIMEOUT_TEXT = "Gateway Timeout";

}
