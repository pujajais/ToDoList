package HomeWork2;

import java.util.Date;

import lab5.State;

public class ToDo {
	
		public String item;
		public String description;
		public State state;
		public Date timeStamp;

		public ToDo(String item, String description, State state, Date timeStamp) {
			this.item = item;
			this.description = description;
			this.state = state;
			this.timeStamp = timeStamp;

		}

}

