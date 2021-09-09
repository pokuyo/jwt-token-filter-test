package kr.co.datarse.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -1192037280500894872L;
	private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
