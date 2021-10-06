package com.developer.johhns.llamadaentrante;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class ReceptorLlamadas extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String estado = "" , numero = "" ;
        Bundle extras = intent.getExtras() ;
        if ( extras != null ) {
            estado = extras.getString(TelephonyManager.EXTRA_STATE) ;

            if ( estado.equals( TelephonyManager.EXTRA_STATE_RINGING ) ) {
                numero = extras.getString( TelephonyManager.EXTRA_CALL_VOICEMAIL_INTENT ) ;
                String info = estado + " " + numero ;
                Log.d("ReceptorAnuncio", info + " intent = " + intent ) ;
                NotificationCompat.Builder notificacion = new NotificationCompat.Builder( context,"Canal_Cell" )
                        .setContentTitle("Llamada entrante")
                        .setContentText(info)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(PendingIntent.getActivity( context ,0 , new Intent(context,MainActivity.class) , 0 ) ) ;
                ((NotificationManager)  context.getSystemService(Context.NOTIFICATION_SERVICE) ).notify(1,notificacion.build());
            }
        }
    }
}
