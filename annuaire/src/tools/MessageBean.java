package tools;

import java.io.Serializable;

public class MessageBean implements Serializable {

		/** */
		private int id;
		
		/** */
		private String from;
		
		/** */
		private String to;
		
		/** */
		private String subject;
		
		/** */
		private String content;
		
		/** */
		private int state;
		
		public static final int RECEIVE = 1, SEND = 2, ERROR = 3;
		
		public MessageBean(int id, String from, String to, String subject, String content, int state) {
			
			this.id = id;
			this.from = from;
			this.to = to;
			this.subject = subject;
			this.content =  content;
			
			if (state == RECEIVE || state == SEND) {
				this.state = state;
			} else {
				this.state = ERROR;
			}
			
		}
	
}
