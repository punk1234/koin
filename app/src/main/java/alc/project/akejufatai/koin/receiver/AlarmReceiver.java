package alc.project.akejufatai.koin.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import alc.project.akejufatai.koin.service.KoinService;

/**
 * Created by AKEJU  FATAI on 2017-11-04.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent kService = new Intent(context, KoinService.class);
        context.startService(kService);

        Toast toast = Toast.makeText(context,"Alarm active",Toast.LENGTH_SHORT);
        toast.show();

    }
}
