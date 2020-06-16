import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.student.DateTime
import com.example.student.R

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        (context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(
            intent!!.getIntExtra("notificationId", 0),
            Notification.Builder(context).apply {
                setSmallIcon(R.mipmap.ic_launcher)
                setContentTitle(intent.getStringExtra("title"))
                setContentText(intent.getCharSequenceExtra("text"))
                setWhen(System.currentTimeMillis())
                setTicker(intent.getCharSequenceExtra("ticker"))
                setPriority(Notification.PRIORITY_HIGH)
                setAutoCancel(true)
                setDefaults(Notification.DEFAULT_SOUND)
                setContentIntent(PendingIntent.getActivity(context, 0, Intent(context, DateTime::class.java), 0))
            }.build()
        )
    }
}