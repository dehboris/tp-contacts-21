package com.example.contactplus;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DirectAction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;

public class telephonie extends AppCompatActivity {

    private int i;
    private DirectAction intent;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephonie);

        String TAG = "telephonie";


        PhoneStateListener stateListener = new PhoneStateListener() {

            // Appelée quand est déclenché l'évènement LISTEN_CALL_STATE
            @Override
            public void onCallStateChanged (int state, String incomingNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE :
                        Log.d(TAG, "Pas d'appel en cours");
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK :
                        Log.d(TAG, "Il y a une communication téléphonique en cours");
                        break;
                    case TelephonyManager.CALL_STATE_RINGING :
                        Log.d(TAG, "Le téléphone sonne, l'appelant est " + incomingNumber);
                        break;
                    default :
                        Log.d(TAG, "Etat inconnu");
                }
            }

            // Appelée quand est déclenché l'évènement LISTEN_DATA_CONNECTION_STATE
            @Override
            public void onDataConnectionStateChanged (int state) {
                switch (state) {
                    case TelephonyManager.DATA_CONNECTED :
                        Log.d(TAG, "L'appareil est connecté.");
                        break;
                    case TelephonyManager.DATA_CONNECTING :
                        Log.d(TAG, "L'appareil est en train de se connecter.");
                        break;
                    case TelephonyManager.DATA_DISCONNECTED :
                        Log.d(TAG, "L'appareil est déconnecté.");
                        break;
                    case TelephonyManager.DATA_SUSPENDED :
                        Log.d(TAG, "L'appareil est suspendu de manière temporaire.");
                        break;
                }
            }

            // Appelée quand est déclenché l'évènement LISTEN_DATA_ACTIVITY
            @Override
            public void onDataActivity (int direction) {
                switch (direction) {
                    case TelephonyManager.DATA_ACTIVITY_IN :
                        Log.d(TAG, "L'appareil est en train de télécharger des données.");
                        break;
                    case TelephonyManager.DATA_ACTIVITY_OUT :
                        Log.d(TAG, "L'appareil est en train d'envoyer des données.");
                        break;
                    case TelephonyManager.DATA_ACTIVITY_INOUT :
                        Log.d(TAG, "L'appareil est en train de télécharger ET d'envoyer des données.");
                        break;
                    case TelephonyManager.DATA_ACTIVITY_NONE :
                        Log.d(TAG, "L'appareil n'envoie pas de données et n'en télécharge pas.");
                        break;
                }
            }

            // Appelée quand est déclenché l'évènement LISTEN_SERVICE_STATE
            @Override
            public void onServiceStateChanged(ServiceState serviceState) {
                // Est-ce que l'itinérance est activée ?
                Log.d(TAG, "L'itinérance est activée : " + serviceState.getRoaming());
                switch (serviceState.getState()) {
                    case ServiceState.STATE_IN_SERVICE :
                        Log.d(TAG, "Conditions normales d'appel");
                        // Pour obtenir un identifiant de l'opérateur
                        Log.d(TAG, "L'opérateur est " + serviceState.getOperatorAlphaLong());
                        break;
                    case ServiceState.STATE_EMERGENCY_ONLY :
                        Log.d(TAG, "Seuls les appels d'urgence sont autorisés.");
                        break;
                    case ServiceState.STATE_OUT_OF_SERVICE :
                        Log.d(TAG, "Ce téléphone n'est pas lié à un opérateur actuellement.");
                        break;
                    case ServiceState.STATE_POWER_OFF :
                        Log.d(TAG, "Le téléphone est en mode avion");
                        break;
                    default :
                        Log.d(TAG, "Etat inconnu");
                }
            }
        };


        Intent mms = new Intent(Intent.ACTION_SEND);
       

        mms.putExtra("sms_body", "Salut les Zéros (mais avec une image !)");
        mms.putExtra("address", "0102030405");

        startActivity(mms);






// On récupère tous les extras
        
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            // Et on récupère le tableau d'objets qui s'appelle « pdus »
            Object[] pdus = (Object[]) bundle.get("pdus");

            // On crée un tableau de SmsMessage pour chaque message
            SmsMessage[] msg = new SmsMessage[pdus.length];

            // Puis, pour chaque tableau, on crée un message qu'on insère dans le tableau
            for(Object pdu : pdus)
                msg[i] = SmsMessage.createFromPdu((byte[]) pdu);
        }










    }
}