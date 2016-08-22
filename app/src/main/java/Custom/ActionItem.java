package Custom;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class ActionItem {
	private Drawable icon;
	private String title;
	private int actionId = -1;

	public ActionItem(int actionId, String title, Drawable icon) {
		this.title = title;
		this.icon = icon;
		this.actionId = actionId;
	}

	public ActionItem(int actionId, String title) {
		this(actionId, title, null);
	}

	public ActionItem(int actionId, Drawable icon) {
		this(actionId, null, icon);
	}

	public ActionItem(ArrayList<String> array) {

	}

	public ActionItem(String string) {
		// TODO Auto-generated constructor stub
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public Drawable getIcon() {
		return this.icon;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public int getActionId() {
		return actionId;
	}

}