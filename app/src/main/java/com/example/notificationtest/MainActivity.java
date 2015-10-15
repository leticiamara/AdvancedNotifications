package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	public static final int NOTIFICATION_ID = 1111;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.buttonSendBigPictureNotification).setOnClickListener(this);
		findViewById(R.id.buttonSendCustomNotification).setOnClickListener(this);
	}

	private void createAndSendBigPictureNotification() {
		/* Create a notification manager to push notification */
		NotificationManager notificationManager = (NotificationManager) MainActivity.this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		/* Create a bitmap icon to display in the notification */
		Bitmap bitmapImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.cat);

		/* Create a BigPictureStyle to set in the notification.
		 * This style defines that the big picture
		 * is displayed in the notification expanded area. */
		NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
		bigPictureStyle.setBigContentTitle("Image expanded");
		bigPictureStyle.setSummaryText("Notification summary text");
		// Add the bitmap image to the bigPictureStyle
		bigPictureStyle.bigPicture(bitmapImage);

		/* Create a notification builder */
		NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
				.setContentTitle("Notification content title")
				.setContentText("Notification content text")
				.setSmallIcon(R.mipmap.ic_launcher)
				.setLargeIcon(bitmapImage)
				.setStyle(bigPictureStyle);

		/* Issue the notification */
		notificationManager.notify(NOTIFICATION_ID, builder.build());
	}

	private void createAndSendCustomNotification() {
		/* Create a notification manager to push notification */
		NotificationManager notificationManager = (NotificationManager) MainActivity.this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		/* Create a bitmap icon to display in the notification */
		Bitmap bitmapImage = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);

		RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.notification_custom);

		/* Create a notification builder */
		Notification notification = new NotificationCompat.Builder(MainActivity.this)
				.setContentTitle("Notification content title")
				.setContentText("Notification content text")
				.setSmallIcon(R.mipmap.ic_launcher)
				.setLargeIcon(bitmapImage)
				.setContent(remoteViews)
				.build();
		notification.bigContentView = remoteViews;

		/* Issue the notification */
		notificationManager.notify(NOTIFICATION_ID, notification);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.buttonSendBigPictureNotification:
				createAndSendBigPictureNotification();
				break;
			case R.id.buttonSendCustomNotification:
				createAndSendCustomNotification();
				break;
		}
	}
}
