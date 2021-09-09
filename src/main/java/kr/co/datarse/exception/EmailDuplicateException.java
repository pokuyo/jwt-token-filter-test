package kr.co.datarse.exception;

public class EmailDuplicateException extends CustomException {

	private static final long serialVersionUID = 7732394985500725386L;

	public EmailDuplicateException() {
		super(ErrorCode.EMAIL_DUPLICATION);
	}

}
