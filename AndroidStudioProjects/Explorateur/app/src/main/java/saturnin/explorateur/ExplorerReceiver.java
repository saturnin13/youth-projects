package saturnin.explorateur;

		import java.io.File;

		import android.content.BroadcastReceiver;
		import android.content.Context;
		import android.content.Intent;

public class ExplorerReceiver extends BroadcastReceiver {
	private MainActivity mActivity = null;

	public ExplorerReceiver(MainActivity mActivity) {
		super();
		this.mActivity = mActivity;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(Intent.ACTION_MEDIA_REMOVED))
			mActivity.setEmpty();
		else if(intent.getAction().equals(Intent.ACTION_MEDIA_MOUNTED))
			mActivity.updateDirectory(new File(intent.getData().toString()));
	}
}
