package tools;

import java.io.Serializable;

public class MessageBean implements Serializable {

		/** */
		private int id;
		
		/** */
		private UserBean from;
		
		/** */
		private UserBean to;
		
		/** */
		private String subject;
		
		/** */
		private String content;
		
		/** */
		private int state;
		
		public static final int OK = 1, ERROR = -1;
		
		public MessageBean(int id, UserBean from, UserBean to, String subject, String content, int state) {
			
			this.id = id;
			this.from = from;
			this.to = to;
			this.subject = subject;
			this.content =  content;
			
			if (state == OK) {
				this.state = state;
			} else {
				this.state = ERROR;
			}
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public UserBean getFrom() {
			return from;
		}

		public void setFrom(UserBean from) {
			this.from = from;
		}

		public UserBean getTo() {
			return to;
		}

		public void setTo(UserBean to) {
			this.to = to;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			
			if (state == OK) {
				this.state = state;
			} else {
				this.state = ERROR;
			}
		}
	
		
}
