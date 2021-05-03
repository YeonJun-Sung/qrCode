package qr.vo;

import lombok.Data;

@Data
public class EmailVO {
	private String subject;
	private String content;
	private String receiver;

	@Override
	public String toString() {
		return "Email [subject=" + subject + ", content=" + content + ", receiver=" + receiver + "]";
	}
}
