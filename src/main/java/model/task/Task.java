package model.task;
import java.util.List;

import model.activity.Activity;
import model.product.Product;


public abstract class Task {

		protected int id;
		protected String name;
		protected String description;
		protected String frequency;
		protected boolean checked;
		protected String startDate;
		protected String endDate;
		protected Activity activity;
		protected boolean visibility;

		
		public Task() {
			super();
		}
		
		public Task(String name, Activity act) {
			this.name = name;
			this.activity = act;
		}

		public Task(String name, String description, String frequency, boolean checked, String startDate, String endDate, Activity activity, boolean visibility) {
			super();
			this.name = name;
			this.description = description;
			this.frequency =frequency;
			this.checked = checked;
			this.startDate = startDate;
			this.endDate = endDate;
			this.activity = activity;
			this.visibility = visibility;
	
		}
		
		public Task(int id, String name, String description, String frequency, boolean checked, String startDate, String endDate, Activity activity, boolean visibility) {
			super();
			this.id=id;
			this.name = name;
			this.description = description;
			this.frequency =frequency;
			this.checked = checked;
			this.startDate = startDate;
			this.endDate = endDate;
			this.activity = activity;	
			this.visibility = visibility;
		}
		
		public Task(int id, String name) {
			this.id = id;
			this.name = name;

		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getFrequency() {
			return frequency;
		}
		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}
		public boolean isChecked() {
			return checked;
		}
		public void setChecked(boolean checked) {
			this.checked = checked;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public Activity getActivity() {
			return activity;
		}
		public void setActivity(Activity activity) {
			this.activity = activity;
		}
		
		public boolean getVisibility() {
			return this.visibility;
		}
		
		public boolean getChecked() {
			return this.checked;
		}
		
		public void setVisibility(boolean visibility) {
			this.visibility = visibility;
		}
		
		public abstract Task readByName();
		public abstract boolean save();
		public abstract boolean delete();
		public abstract boolean update();
		public abstract boolean addRessource(Product p);
		public abstract boolean exist();
		public abstract List<Product> getAllRessourceTask();
}
